/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author PHONG VU
 */
public class ProductList implements Serializable {

    public static final long serialVersionUID = 1L;
   static TreeList treeList = new TreeList();

    public Product input() {
        Product product = new Product();
        String code;
        do {
            System.out.println("Enter product code:");
            code = CheckInput.checkString();
            if (!checkPcode(code)) {
                System.out.println("Product code is exist!");
            }
        } while (!checkPcode(code));
        product.setPcode(code);
        System.out.println("Enter product name:");
        String name = CheckInput.checkString();
        System.out.println("Enter quantity of product:");
        int quantity = CheckInput.checkPositive();
        System.out.println("Enter saled of product:");
        int saled = CheckInput.checkPositive();
        System.out.println("Enter price of product:");
        double price = CheckInput.checkDouble();
        treeList.addNode(new Product(code, name, quantity, saled, price));
        return product;
    }

    public void addTest() {
        Product p2 = new Product("P2", "heo", 20, 9, 23);
        Product p1 = new Product("P1", "Ga", 12, 5, 10);
        Product p3 = new Product("P3", "tom", 21, 12, 10);
        Product p4 = new Product("P4", "ca", 25, 16, 8);
        Product p5 = new Product("P5", "bo", 22, 14, 10);
        treeList.addNode(p1);
        treeList.addNode(p2);
        treeList.addNode(p3);
        treeList.addNode(p4);
        treeList.addNode(p5);
    }

    public void display() {
        System.out.println(" ");
        System.out.printf("%10s|%11s|%11s|%11s|%11s|%10s", "code", "Pro_name", "Quantity", "Saled",
                "Price", "Value");
        System.out.println("");
        System.out.println("-------------------------------------------------------------");
        treeList.printLevelOder();
    }

    public void saveFile() {
        try {
            FileOutputStream out = new FileOutputStream("product.txt");
            ObjectOutputStream output = new ObjectOutputStream(out);
            output.writeObject(treeList);
            output.close();
        } catch (IOException e) {
            System.err.println("Error!");
        }
    }

    public void loadFile() {
        try {
            FileInputStream in = new FileInputStream("product.txt");
            ObjectInputStream inputStream = new ObjectInputStream(in);
            treeList = (TreeList) inputStream.readObject();
            inputStream.close();
        } catch (IOException e) {
            System.err.println("Error!");
        } catch (ClassNotFoundException ex) {
            System.err.println("Not have this file");
        }
    }

    public void searchByPCode() {
        String pcode;
        System.out.print("Enter pcode want to search: ");
        pcode = CheckInput.checkString();
        if (treeList.search(pcode) == null) {
            System.out.println("Cant find " + pcode + "in data");
        } else {
            System.out.println(treeList.search(pcode).data.toString());
        }
    }

    public static TreeNode searchPcode(String pcode) {
        return treeList.search(pcode);
    }

    public static boolean checkPcode(String pcode) {
        if (searchPcode(pcode) == null) {
            return true;
        }
        return false;
    }

    public void delete() {
        System.out.println("Enter product code you want to delete: ");
        String code = CheckInput.checkString();
        if (treeList.delete(code)) {
            System.out.println("Delete successfull!");
        } else {
            System.out.println("Error!");
        }
    }

    public void count() {
        System.out.println("Number of products: " + treeList.getCount());
    }

    public void simplyBalance() {
        treeList.useSimplyBalance();
    }

    public void breath_first() {
        treeList.breadthTraverse();
    }
}

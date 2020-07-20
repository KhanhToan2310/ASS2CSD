/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

/**
 *
 * @author PHONG VU
 */
public class CustomerList {

    static FirstLinkedList<Customer> customers = new FirstLinkedList<>();

    public Customer input() {
        Customer cts = new Customer();
        String ccode;
        do {
            System.out.println("Please enter customer code: ");
            ccode = CheckInput.checkString();
            if (!CheckInput.checkcCodeExist(customers, ccode)) {
                System.err.println("Customer code is exist!");
            }
        } while (!CheckInput.checkcCodeExist(customers, ccode));
        cts.setCcode(ccode);
        System.out.println("Please enter customer name: ");
        cts.setCus_name(CheckInput.checkString());
        System.out.println("Please enter phone number: ");
        cts.setPhone(CheckInput.checkString());
        return cts;
    }

    public void addCustomer() {
        customers.addLast(input());
    }

    public void display() {
        System.out.printf("%10s| %11s| %10s\n", "ccode", "Customer_name", "Phone");
        System.out.println("-----------------------------------");
        for (Customer cts : customers) {
            System.out.println(cts.toString());
        }
    }

    public void addTest() {
        customers.addLast(new Customer("C03", "Hoa", "1902"));
        customers.addLast(new Customer("C01", "La", "1901"));
        customers.addLast(new Customer("C02","Canh","1903"));
        customers.addLast(new Customer("C05","Cay","1910"));
    }
    public void deleteByCCode() {
        System.out.print("Enter customer code want to delete: ");
        String tcode = CheckInput.checkString();
        for (Customer c : customers) {
            if (tcode.equalsIgnoreCase(c.getCcode())) {
                customers.deleteNode(c);
                System.out.println("Success!");
                return;
            }
        }
        System.out.println("Don't have this customer!");
    }

    public void searchByCCode() {
        System.out.print("Enter customer code want to search: ");
        String tcode = CheckInput.checkString();
        for (Customer c : customers) {
            if (tcode.equalsIgnoreCase(c.getCcode())) {
                System.out.println(c.toString());
                return;
            }
        }
        System.out.println("Don't have this customer!");
    }

    public static boolean checkCCode(String cCode) {
        for (Customer ctm : customers) {
            if (cCode.equalsIgnoreCase(ctm.getCcode())) {
                return false;
            }
        }
        return true;
    }

    public void saveToFile() throws FileNotFoundException {
        try {
            FileOutputStream out = new FileOutputStream("customer.dat");
            ObjectOutputStream output = new ObjectOutputStream(out);
            output.writeObject(customers);
            output.close();
        } catch (Exception e) {
            System.out.println("Error!");
        }
        PrintWriter pwr = new PrintWriter(new FileOutputStream("customer.txt"));
        for (Customer cst : customers) {
            String temp = cst.toString();
            pwr.write(temp);
        }
        pwr.close();
    }

    public void loadFile() {
        String file = "customer.dat";
        try {
            FileInputStream in = new FileInputStream(file);
            ObjectInputStream inputStream = new ObjectInputStream(in);
            customers = (FirstLinkedList) inputStream.readObject();
        } catch (IOException e) {
            System.out.println("Error!");
        } catch (ClassNotFoundException ex) {
            System.out.println("Not have this file");
        }
    }

}

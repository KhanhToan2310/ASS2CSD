/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment1;


/**
 *
 * @author PHONG VU
 */
public class OderingList {

    FirstLinkedList<Odering> oderings = new FirstLinkedList<>();

    public Odering input() {
        Odering odr = new Odering();
        CustomerList ctl = new CustomerList();
        Product prd = new Product();
        String ccode = "", pcode = "";
        int quantity = 0;
        double k;
        do {
            do {
                System.out.print("Enter product code: ");
                pcode = CheckInput.checkString();
                if (ProductList.checkPcode(pcode)) {
                    System.err.println("Product code is not found ");
                }
            } while (ProductList.checkPcode(pcode));
            prd = ProductList.searchPcode(pcode).data;
            if (prd.getSaled() == prd.getQuantity()) {
                System.err.println("Product is exhausted");
                continue;
            }
            do {
                System.out.print("Enter customer code: ");
                ccode = CheckInput.checkString();
                if (CustomerList.checkCCode(ccode)) {
                    System.err.println("Customer code is not found ");
                }
            } while (CustomerList.checkCCode(ccode));
            if (!CheckOdering(pcode, ccode)) {
                System.out.println("This odering is exist ");
                continue;
            }
            do {
                System.out.print("Enter  the entered quantity: ");
                quantity = CheckInput.checkInt();
                if (quantity > prd.getQuantity() - prd.getSaled()) {
                    System.err.println("Number of quantity is " + (prd.getQuantity() - prd.getSaled()));
                }
            } while (quantity > prd.getQuantity() - prd.getSaled());
            prd.setSaled(prd.getSaled() + quantity);
        } while (!CheckOdering(pcode, ccode) && ProductList.checkPcode(pcode) && CustomerList.checkCCode(ccode) && prd.getSaled() == prd.getQuantity()
                && quantity > prd.getQuantity() - prd.getSaled());
        odr.setPcode(pcode);
        odr.setCcode(ccode);
        odr.setQuantity(quantity);
        return odr;
    }

    public boolean CheckOdering(String pCode, String cCode) {
        for (Odering oder : oderings) {
            if (pCode.equalsIgnoreCase(oder.getPcode()) && cCode.equalsIgnoreCase(cCode)) {
                return false;
            }
        }
        return true;
    }

    public void display() {
        System.out.printf("%10s|%10s|%10s", "pcode", "ccode", "quantity");
        System.out.println("");
        for (Odering odering : oderings) {
            System.out.println(odering.toString());
        }
    }
}

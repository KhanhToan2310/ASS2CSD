/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment1;

import java.io.Serializable;

/**
 *
 * @author PHONG VU
 */
public class Node<T> implements Serializable{
    private static final long serialversionUID = 1L;
    T data;
    Node next;

    public Node() {
    }

    public Node(T data) {
        this.data = data;
        this.next = null;
    }

    public Node(T data, Node next) {
        this.data = data;
        this.next = next;
    }
}

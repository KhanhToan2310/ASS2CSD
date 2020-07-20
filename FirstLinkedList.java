/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;

/**
 *
 * @author PHONG VU
 */
public class FirstLinkedList<T> implements Serializable, Iterable<T> {

    private static final long serialversionUID = 1L;
    Node head = null;
    Node tail = null;

    public FirstLinkedList() {
        head = null;
        tail = null;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    public FirstLinkedList(Node head, Node tail) {
        this.head = head;
        this.tail = tail;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addFirst(T value) {
        Node p = new Node(value);
        if (isEmpty()) {
            head = p;
            tail = p;
        } else {
            p.next = head;
            head = p;
        }
    }

    public void addLast(T x) {
        Node p = new Node(x);
        if (isEmpty()) {
            head = tail = p;
        } else {
            tail.next = p;
            tail = p;
        }
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("List is empty");
        } else {
            Node p = new Node();
            p = head;
            while (p.next != null) {
                p = p.next;
            }

        }
    }

    public int size() {
        Node p = head;
        int c = 0;
        while (p != null) {
            c++;
            p = p.next;
        }
        return c;
    }

    public void deleteNode(T item) {
        Node temp = head;
        Node prev = null;
        if (temp != null && temp.data.equals(item)) {
            //remove head
            head = temp.next;
            return;
        }
        while (temp != null) {
            if (temp.data.equals(item)) {
                break;
            } else {
                prev = temp;
                temp = temp.next;
            }
        }
        if (temp == null) {
            return;
        }
        prev.next = temp.next;
    }

    public void insertAfterPosition(T value, int position) {
        Node q = new Node(value);
        if (position == -1) {
            addFirst(value);
        } else {
            int count = 0;
            Node p = head;
            while (p != null && count < position) {
                p = p.next;
                count++;
            }
            if (p.next == null && count == position) {
                addLast(value);
                return;
            }
            q.next = p.next;
            p.next = q;
        }
    }

    public void deleteBefore(T item) {
        Node temp = head;
        Node prev1 = null;
        Node prev2 = null;
        if (temp != null && temp.data.equals(item)) {
            head = temp.next;
            return;
        }
        while (temp != null) {
            if (temp.data.equals(item)) {
                break;
            } else {
                prev2 = prev1;
                prev1 = temp;
                temp = temp.next;
            }
        }
        if (temp == null) {
            return;
        }
        prev2.next = temp;
    }

    public void sort() {
        if (size() > 1) {
            for (int i = 0; i < size(); i++) {
                Node next = head.next;
                Node c = head;
                for (int j = 0; j < size() - 1; j++) {
                    if (c.data.toString().compareTo(next.data.toString()) > 0) {
                        Node temp = c;
                        c = next;
                        next = temp;
                    }
                    c = next;
                    next = next.next;
                }
            }
        }
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    T value = (T) node.data;
                    node = node.next;
                    return value;
                }
                return null;
            }
        };
    }

}

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
public class TreeList implements Serializable {

    public static final long serialVersionUID = 1L;
    public TreeNode root;

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public TreeNode insert(TreeNode root, Product pro) {
        TreeNode node = new TreeNode(pro);
        if (root == null) {
            root = node;
            return root;
        }
        if (node.data.getPcode().compareTo(root.data.getPcode()) < 0) {
            root.left = insert(root.left, pro);
        } else {
            root.right = insert(root.right, pro);
        }
        return root;
    }

    public void addNode(Product pro) {
        if (root == null) {
            root = insert(root, pro);
        } else {
            insert(root, pro);
        }
    }

    public void visitNode(TreeNode node) {
        System.out.println(" " + node.data);
    }

    public void inOder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOder(root.left);
        visitNode(root);
        inOder(root.right);
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int lheight = height(root.left);
            int rheight = height(root.right);
            if (lheight > rheight) {
                return (lheight + 1);
            } else {
                return (rheight + 1);
            }
        }
    }

    public void printGivenLevel(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        if (level == 1) {
            System.out.println(root.data + " ");
        } else if (level > 1) {
            printGivenLevel(root.left, level - 1);
            printGivenLevel(root.right, level - 1);
        }
    }

    public void printLevelOder() {
        int h = height(root);
        for (int i = 0; i <= h; i++) {
            printGivenLevel(root, i);
            System.out.println("");
        }

    }

    public TreeNode search(TreeNode root, String pcode) {
        if (root == null || root.data.getPcode().equals(pcode)) {
            return root;
        }
        if (root.data.getPcode().compareTo(pcode) > 0) {
            return search(root.left, pcode);
        }
        
        return search(root.right, pcode);
    }

    public TreeNode search(String infor) {
        return search(root, infor);
    }

    public boolean delete(String code) {
        try {
            this.root = deleteRec(root, code);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    private TreeNode deleteRec(TreeNode root, String x) {
        if (root == null) {
            return root;
        }

        if (x.compareTo(root.data.getPcode()) < 0) {
            root.left = deleteRec(root.left, x);
        } else if (x.compareTo(root.data.getPcode()) > 0) {
            root.right = deleteRec(root.right, x);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.data = min(root.right);
            root.right = deleteRec(root.right, root.data.getPcode());
        }
        return root;

    }

    private Product min(TreeNode root) {
        Product min = root.data;
        while (root.left != null) {
            min = root.left.data;
            root = root.left;
        }
        return min;
    }

    public int count(TreeNode p) {
        if (p == null) {
            return 0;
        }
        int k, h, r;
        k = count(p.left);
        h = count(p.right);
        r = h + k + 1;
        return r;
    }

    public int getCount() {
        return count(root);
    }

    public Product[] sort() {
        Product[] product = new Product[count(root)];
        int i = 0;
        MyQueue queue = new MyQueue(200);
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            TreeNode node = (TreeNode) queue.dequeue();
            if (node.left != null) {
                queue.enqueue(node.left);
            }
            if (node.right != null) {
                queue.enqueue(node.right);
            }
            product[i++] = node.data;
        }
        for (int j = 0; j < product.length; j++) {
            for (int k = product.length - 1; k > j; k--) {
                if (product[k - 1].getPcode().compareToIgnoreCase(product[k].getPcode()) > 0) {
                    Product t = product[k - 1];
                    product[k - 1] = product[k];
                    product[k] = t;
                }
            }
        }
        return product;
    }

    public TreeNode simplyBalance(Product[] product, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(product[mid]);
        addNode(root.data);
        root.left = simplyBalance(product, start, mid - 1);
        root.right = simplyBalance(product, mid + 1, end);
        return root;
    }

    public void useSimplyBalance() {
        Product[] products = sort();
        root = simplyBalance(products, 0, products.length - 1);
    }

    public void breadthTraverse() {
        if (root == null) {
            return;
        }
        MyQueue queue = new MyQueue(100);
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            TreeNode node = (TreeNode) queue.dequeue();
            if (node.left != null) {
                queue.enqueue(node.left);
            }
            if (node.right != null) {
                queue.enqueue(node.right);
            }
            view(node);
        }
    }

    public void view(TreeNode node) {
        System.out.println(node.data.toString());
    }
}

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
public class TreeNode implements Serializable {

    private static final long serialversionUID = 1L;
    Product data;
    TreeNode left, right;

    public TreeNode(Product data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public TreeNode(TreeNode left, TreeNode right) {
        this.left = left;
        this.right = right;
    }

}

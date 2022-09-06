package com.amz;

import com.amz.leet.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/binary-tree-pruning/"
 */
public class BinaryTreePruning {

    public TreeNode pruneTree(TreeNode root) {
        if (root == null) return null;
        boolean leftHas = hasOne(root.left);
        boolean rightHas = hasOne(root.right);
        if (!leftHas) root.left = null;
        if (!rightHas) root.right = null;
        return root.val == 0 && !leftHas && !rightHas ? null : root;
    }

    private boolean hasOne(TreeNode node) {
        boolean leftHas, rightHas;
        if (node == null) return false;
        if (node.left == null) {
            leftHas = false;
        } else {
            leftHas = hasOne(node.left);
            if (!leftHas) node.left = null;
        }
        if (node.right == null) {
            rightHas = false;
        } else {
            rightHas = hasOne(node.right);
            if (!rightHas) node.right = null;
        }
        return node.val == 1 || leftHas || rightHas;
    }

    @Test
    public void check1() {
        TreeNode input = new TreeNode(1);
        input.right = new TreeNode(0);
        input.right.left = new TreeNode(0);
        input.right.right = new TreeNode(1);
        TreeNode expected = new TreeNode(1);
        expected.right = new TreeNode(0);
        expected.right.right = new TreeNode(1);
        verify(input, expected);
    }

    @Test
    public void check2() {
        TreeNode input = new TreeNode(1);
        input.left = new TreeNode(0);
        input.right = new TreeNode(1);
        input.left.left = new TreeNode(0);
        input.left.right = new TreeNode(0);
        input.right.left = new TreeNode(0);
        input.right.right = new TreeNode(1);
        TreeNode expected = new TreeNode(1);
        expected.right = new TreeNode(1);
        expected.right.right = new TreeNode(1);
        verify(input, expected);
    }

    @Test
    public void check3() {
        TreeNode input = new TreeNode(1);
        input.left = new TreeNode(1);
        input.right = new TreeNode(0);
        input.left.left = new TreeNode(1);
        input.left.right = new TreeNode(1);
        input.right.left = new TreeNode(0);
        input.right.right = new TreeNode(1);
        input.left.left.left = new TreeNode(0);
        TreeNode expected = new TreeNode(1);
        expected.left = new TreeNode(1);
        expected.right = new TreeNode(0);
        expected.left.left = new TreeNode(1);
        expected.left.right = new TreeNode(1);
        expected.right.right = new TreeNode(1);
        verify(input, expected);
    }

    private void verify(TreeNode input, TreeNode expected) {
        System.out.println("Input:\n" + input.toString());
        System.out.println("Expected:\n" + expected.toString());
        TreeNode actual = pruneTree(input);
        System.out.println("Actual:\n" + actual.toString());
        Assertions.assertEquals(expected.toString(), actual.toString());
    }

}

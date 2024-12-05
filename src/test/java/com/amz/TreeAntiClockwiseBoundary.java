package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @see "https://leetcode.com/problems/boundary-of-binary-tree/"
 */
public class TreeAntiClockwiseBoundary {

    @Test
    void t1() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(4);

        List<Integer> expected = Arrays.asList(1, 3, 4, 2);
        Assertions.assertEquals(expected, boundaryOfBinaryTree(root));
    }

    @Test
    void t2() {
        List<Integer> expected = Arrays.asList();
        Assertions.assertEquals(expected, boundaryOfBinaryTree(null));
    }

    @Test
    void t3() {
        Integer[] input = new Integer[] {1,2,3,4,5,6,null,null,null,7,8,9,10};
        TreeNode root = prep(input, null, 0);
        inOrder(root);

        List<Integer> expected = Arrays.asList(1,2,4,7,8,9,10,6,3);
        Assertions.assertEquals(expected, boundaryOfBinaryTree(root));
    }

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> output = new ArrayList<>();
        if (root != null) {
            Set<TreeNode> visited = new HashSet<>();
            output.add(root.val);
            visited.add(root);
            leftBoundary(root.left, output, visited);
            leaves(root, output, visited);
            rightBoundary(root.right, output, visited);
        }
        return output;
    }

    private void leftBoundary(final TreeNode root, final List<Integer> output, final Set<TreeNode> visited) {
        if (root == null) return;
        if (!visited.contains(root)) {
            output.add(root.val);
            visited.add(root);
        }
        if (root.left == null) {
            if (root.right != null) {
                leftBoundary(root.right, output, visited);
            }
        } else {
            leftBoundary(root.left, output, visited);
        }
    }

    private void rightBoundary(final TreeNode root, final List<Integer> output, final Set<TreeNode> visited) {
        if (root == null) return;
        if (root.right == null) {
            if (root.left != null) {
                rightBoundary(root.left, output, visited);
            }
        } else {
            rightBoundary(root.right, output, visited);
        }
        if (!visited.contains(root)) {
            output.add(root.val);
            visited.add(root);
        }
    }

    private void leaves(final TreeNode root, final List<Integer> output, final Set<TreeNode> visited) {
        if (root.left == null && root.right == null && !visited.contains(root)) {
            output.add(root.val);
            visited.add(root);
        } else {
            if (root.left != null) {
                leaves(root.left, output, visited);
            }
            if (root.right != null) {
                leaves(root.right, output, visited);
            }
        }
    }

    private TreeNode prep(Integer[] arr, TreeNode root, int i) {
        if (i < arr.length && arr[i] != null) {
            TreeNode temp = new TreeNode(arr[i]);
            root = temp;
            root.left = prep(arr, root.left, 2 * i + 1);
            root.right = prep(arr, root.right, 2 * i + 2);
        }
        return root;
    }

    private void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            System.out.printf("%05d", root.val);
            inOrder(root.right);
        }
    }
}

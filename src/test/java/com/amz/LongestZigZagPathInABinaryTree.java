package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/description/?envType=study-plan-v2&envId=leetcode-75"
 */
public class LongestZigZagPathInABinaryTree {

    public int longestZigZag(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxZigZagLength(root.left, 'L', 0), maxZigZagLength(root.right, 'R', 0));
    }

    private int maxZigZagLength(final TreeNode root, final char dir, final int len) {
        if (root == null) return len;
        return Math.max(
            maxZigZagLength(root.left, 'L', dir == 'L' ? 0 : len + 1),
            maxZigZagLength(root.right, 'R', dir == 'R' ? 0 : len + 1)
        );
    }

    @Test
    void t1() {
        final TreeNode root = new TreeNode(1);
        final TreeNode node1 = new TreeNode(1);
        final TreeNode node2 = new TreeNode(1);
        final TreeNode node3 = new TreeNode(1);
        final TreeNode node4 = new TreeNode(1);
        final TreeNode node5 = new TreeNode(1);
        final TreeNode node6 = new TreeNode(1);
        final TreeNode node7 = new TreeNode(1);
        root.right = node1;
        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;
        node4.right = node6;
        node6.right = node7;
        Assertions.assertEquals(3, longestZigZag(root));
    }
}

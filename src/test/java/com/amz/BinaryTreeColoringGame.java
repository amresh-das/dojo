package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/binary-tree-coloring-game/"
 */
public class BinaryTreeColoringGame {

    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        TreeNode nodeX = find(root, x);
        int leftSubTreeCount = count(nodeX.left);
        int rightSubTreeCount = count(nodeX.right);

        int player2Count = n - leftSubTreeCount - rightSubTreeCount - 1;
        return player2Count > leftSubTreeCount + rightSubTreeCount + 1;
    }

    private int count(final TreeNode root) {
        if (root == null) return 0;
        return 1 + count(root.left) + count(root.right);
    }

    private TreeNode find(final TreeNode root, final int x) {
        if (root.val == x) return root;
        TreeNode node = null;
        if (root.left != null) {
            node = find(root.left, x);
        }
        if (node == null && root.right != null) {
            node = find(root.right, x);
        }
        return node;
    }

    @Test
    public void check1() {
        Assertions.assertTrue(btreeGameWinningMove(TreeNode.create(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11), 11, 3));
    }

    @Test
    public void check2() {
        Assertions.assertFalse(btreeGameWinningMove(TreeNode.create(1, 2, 3), 3, 1));
    }
}

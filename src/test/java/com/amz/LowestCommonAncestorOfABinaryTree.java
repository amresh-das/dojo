package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/?envType=study-plan-v2&envId=leetcode-75"
 */
public class LowestCommonAncestorOfABinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return (TreeNode) traverse(root, p, q)[0];
    }

    private Object[] traverse(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return new Object[] {null, 0};
        int rootCount = root.val == p.val || root.val == q.val ? 1 : 0;

        Object[] left = traverse(root.left, p, q);
        int leftCount = (int) left[1];
        if (leftCount == 2) return left;
        Object[] right = traverse(root.right, p, q);
        int rightCount = (int) right[1];
        if (rightCount == 2) return right;

        int matchCount = leftCount + rightCount + rootCount;
        return matchCount == 2 ? new Object[] {root, 2} : new Object[] {null, matchCount};
    }

    @Test
    void t1() {
        TreeNode c0 = new TreeNode(0);
        TreeNode c1 = new TreeNode(1);
        TreeNode c2 = new TreeNode(2);
        TreeNode c3 = new TreeNode(3);
        TreeNode c4 = new TreeNode(4);
        TreeNode c5 = new TreeNode(5);
        TreeNode c6 = new TreeNode(6);
        TreeNode c7 = new TreeNode(7);
        TreeNode c8 = new TreeNode(8);
        c3.left = c5; c3.right = c1;
        c5.left = c6; c5.right = c2;
        c2.left = c7; c2.right = c4;
        c1.left = c0; c1.right = c8;
        Assertions.assertEquals(3, lowestCommonAncestor(c3, c5, c1).val);
    }

    @Test
    void t2() {
        TreeNode c0 = new TreeNode(0);
        TreeNode c1 = new TreeNode(1);
        TreeNode c2 = new TreeNode(2);
        TreeNode c3 = new TreeNode(3);
        TreeNode c4 = new TreeNode(4);
        TreeNode c5 = new TreeNode(5);
        TreeNode c6 = new TreeNode(6);
        TreeNode c7 = new TreeNode(7);
        TreeNode c8 = new TreeNode(8);
        c3.left = c5; c3.right = c1;
        c5.left = c6; c5.right = c2;
        c2.left = c7; c2.right = c4;
        c1.left = c0; c1.right = c8;
        Assertions.assertEquals(5, lowestCommonAncestor(c3, c5, c4).val);
    }

    @Test
    void t3() {
        TreeNode c1 = new TreeNode(1);
        TreeNode c2 = new TreeNode(2);
        c1.left = c2;
        Assertions.assertEquals(1, lowestCommonAncestor(c1, c1, c2).val);
    }
}

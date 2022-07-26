package com.amz;

import com.amz.leet.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/"
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
    public void check1() {
        TreeNode root = TreeNode.create(3,5,1,6,2,0,8,null,null,7,4);
        TreeNode p = TreeNode.create(5);
        TreeNode q = TreeNode.create(1);
        Assertions.assertEquals(root, lowestCommonAncestor(root, p, q));
    }

    @Test
    public void check2() {
        TreeNode root = TreeNode.create(3,5,1,6,2,0,8,null,null,7,4);
        TreeNode p = TreeNode.create(5);
        TreeNode q = TreeNode.create(4);
        Assertions.assertEquals(root.left, lowestCommonAncestor(root, p, q));
    }

    @Test
    public void check3() {
        TreeNode root = TreeNode.create(1,2);
        TreeNode p = TreeNode.create(1);
        TreeNode q = TreeNode.create(2);
        Assertions.assertEquals(root, lowestCommonAncestor(root, p, q));
    }

}

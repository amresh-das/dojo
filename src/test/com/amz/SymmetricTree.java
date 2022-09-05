package com.amz;

import com.amz.leet.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/symmetric-tree/"
 */
public class SymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    private boolean isMirror(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        if (a.val != b.val) return false;
        return isMirror(a.left, b.right) && isMirror(a.right, b.left);
    }

    @Test
    public void check1() {
        Assertions.assertTrue(isSymmetric(TreeNode.create(1,2,2,3,4,4,3)));
    }

    @Test
    public void check2() {
        Assertions.assertFalse(isSymmetric(TreeNode.create(1,2,2,null,3,null,3)));
    }

}

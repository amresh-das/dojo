package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/recover-binary-search-tree/"
 */
public class RecoverBinarySearchTree {

    @Test
    void t1() {
        TreeNode in = TreeNode.create(1,3,null,null,2);
        recoverTree(in);
        Assertions.assertEquals(TreeNode.create(3,1,null,null,2), in);
    }

    public void recoverTree(TreeNode root) {
        TreeNode[] toSwap = new TreeNode[2];
    }
}

package com.amz;

import com.amz.leet.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BSTChecker {

    @Test
    void t1() {
        Assertions.assertTrue(isValidBST(TreeNode.create(2, 1, 3)));
    }

    @Test
    void t2() {
        Assertions.assertFalse(isValidBST(TreeNode.create(5,1,4,null,null,3,6)));
    }

    @Test
    void t3() {
        TreeNode root = TreeNode.create(10, 5, 15, null, null, 6, 20);
        Assertions.assertFalse(isValidBST(root));
    }

    @Test
    void t4() {
        TreeNode root = TreeNode.create(3,1,5,0,2,4,6,null,null,null,3);
        System.out.println(root.toString());
        Assertions.assertFalse(isValidBST(root));
    }

    @Test
    void t5() {
        TreeNode root = TreeNode.create(2147483647);
        System.out.println(root.toString());
        Assertions.assertTrue(isValidBST(root));
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        return  traverse(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean traverse(final TreeNode root, final long minValue, final long maxValue) {
        if (root == null) return true;
        if (minValue >= root.val || maxValue <= root.val) return false;
        boolean left = traverse(root.left, minValue, root.val);
        boolean right = traverse(root.right, root.val, maxValue);
        return left && right;
    }

}

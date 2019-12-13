package com.amz;

import com.amz.leet.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InsufficientTreePathDelete2 {

    @Test
    void t1() {
        TreeNode root = TreeNode.create(1,2,3,4,-99,-99,7,8,9,-99,-99,12,13,-99,14);
        TreeNode expected = TreeNode.create(1,2,3,4,null,null,7,8,9,null,14);
        Assertions.assertEquals(expected, sufficientSubset(root, 1));
    }

    @Test
    void t2() {
        TreeNode root = TreeNode.create(5,4,8,11,null,17,4,7,1,null,null,5,3);
        TreeNode expected = TreeNode.create(5,4,8,11,null,17,4,7,null,null,null,5);
        Assertions.assertEquals(expected, sufficientSubset(root, 22));
    }

    @Test
    void t3() {
        TreeNode root = TreeNode.create(1,2,-3,-5,null,4,null);
        TreeNode expected = TreeNode.create(1,null,-3,4, null);
        Assertions.assertEquals(expected, sufficientSubset(root, -1));
    }

    public TreeNode sufficientSubset(TreeNode root, int limit) {
        if (root == null) return null;
        boolean shouldDelete = traverse(null, null, root, root.val, limit);
        return shouldDelete ? null : root;
    }

    private boolean traverse(final TreeNode parent, final String direction, final TreeNode root, final int sum, final int limit) {
        if (root.left == null && root.right == null) {
            return sum < limit;
        }
        boolean deleteLeft = root.left == null || traverse(root, "L", root.left, sum + root.left.val, limit);
        boolean deleteRight = root.right == null || traverse(root, "R", root.right, sum + root.right.val, limit);
        if (deleteLeft) root.left = null;
        if (deleteRight) root.right = null;
        if (deleteLeft && deleteRight && parent != null) {
            if ("L".equals(direction)) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
        return deleteLeft && deleteRight;
    }
}

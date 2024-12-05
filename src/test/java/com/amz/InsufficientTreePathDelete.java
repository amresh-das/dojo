package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InsufficientTreePathDelete {

    @Test
    void t1() {
        TreeNode in = TreeNode.create(1,2,3,4,-99,-99,7,8,9,-99,-99,12,13,-99,14);
        TreeNode expected = TreeNode.create(1,2,3,4,null,null,7,8,9,null,14);
        Assertions.assertEquals(
                expected,
                sufficientSubset(in, 1)
        );
    }

    @Test
    void t2() {
        TreeNode in = TreeNode.create(5,4,8,11,null,17,4,7,1,null,null,5,3);
        TreeNode expected = TreeNode.create(5,4,8,11,null,17,4,7,null,null,null,5);
        Assertions.assertEquals(
                expected,
                sufficientSubset(in, 22)
        );
    }

    @Test
    void t3() {
        TreeNode in = TreeNode.create(1,2,-3,-5,null,4,null);
        TreeNode expected = TreeNode.create(1,null,-3,4);
        Assertions.assertEquals(
                expected,
                sufficientSubset(in, -1)
        );
    }

    public TreeNode sufficientSubset(TreeNode root, int limit) {
        if (shouldDelete(root, root.val, limit)) return null;
        return root;
    }

    private boolean shouldDelete(final TreeNode root, final int scoreIn, final int limit) {
        if (root.left == null && root.right == null) {
            return limit > scoreIn;
        }
        boolean leftInsufficient = root.left == null || shouldDelete(root.left, scoreIn + root.left.val, limit);
        if (leftInsufficient) {
            root.left = null;
        }
        boolean rightInsufficient = root.right == null || shouldDelete(root.right, scoreIn + root.right.val, limit);
        if (rightInsufficient) {
            root.right = null;
        }
        return leftInsufficient && rightInsufficient;
    }
}

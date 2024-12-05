package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/maximum-depth-of-binary-tree/description/?envType=study-plan-v2&envId=leetcode-75"
 */
public class MaximumDepthOfBinaryTreeTest {

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    @Test
    void check1() {
        Assertions.assertEquals(3, maxDepth(TreeNode.create(3,9,20,null,null,15,7)));
    }

    @Test
    void check2() {
        Assertions.assertEquals(2, maxDepth(TreeNode.create(1,null,2)));
    }
}

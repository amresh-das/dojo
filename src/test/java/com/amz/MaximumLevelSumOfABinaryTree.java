package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @see "https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/description/?envType=study-plan-v2&envId=leetcode-75"
 */
public class MaximumLevelSumOfABinaryTree {

    public int maxLevelSum(TreeNode root) {
        int[] maxLevelSum = new int[] {root.val, 1};
        List<TreeNode> children = new ArrayList<>();
        if (root.left != null) children.add(root.left);
        if (root.right != null) children.add(root.right);
        int level = 1;
        while (true) {
            if (children.isEmpty()) break;
            ++level;
            Object[] res = levelSum(children);
            int sum = (int) res[0];
            children = (List<TreeNode>) res[1];
            if (sum > maxLevelSum[0]) {
                maxLevelSum[0] = sum;
                maxLevelSum[1] = level;
            }
        }
        return maxLevelSum[1];
    }

    private Object[] levelSum(final List<TreeNode> nodes) {
        List<TreeNode> children = new ArrayList<>();
        int sum = 0;
        for (TreeNode n : nodes) {
            sum += n.val;
            if (n.left != null) children.add(n.left);
            if (n.right != null) children.add(n.right);
        }
        return new Object[] {sum, children};
    }

    @Test
    void t1() {
        TreeNode c1 = new TreeNode(1);
        TreeNode c2 = new TreeNode(7);
        TreeNode c3 = new TreeNode(0);
        TreeNode c4 = new TreeNode(7);
        TreeNode c5 = new TreeNode(-8);
        c1.left = c2; c1.right = c3;
        c2.left = c4; c2.right = c5;
        Assertions.assertEquals(2, maxLevelSum(c1));
    }
}

package com.amz;

import com.amz.leet.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/"
 */
public class BinaryTreeLongestConsecutiveSequence {

    public int longestConsecutive(TreeNode root) {
        return root == null ? 0 : Math.max(traverse(root.left, 1, root.val), traverse(root.right, 1, root.val));
    }

    private int traverse(TreeNode node, int streak, int parentVal) {
        if (node == null) return streak;
        int currentStreak = (node.val == parentVal + 1 ? streak : 0) + 1;
        int leftStreak = traverse(node.left, currentStreak, node.val);
        int rightStreak = traverse(node.right, currentStreak, node.val);
        return Math.max(currentStreak, Math.max(leftStreak, rightStreak));
    }

    @Test
    public void check1() {
        TreeNode input = TreeNode.create(1);
        input.right = TreeNode.create(3);
        input.right.left = TreeNode.create(2);
        input.right.right = TreeNode.create(4);
        input.right.right.right = TreeNode.create(5);
        Assertions.assertEquals(3, longestConsecutive(input));
    }

    @Test
    public void check2() {
        TreeNode input = TreeNode.create(2);
        input.right = TreeNode.create(3);
        input.right.left = TreeNode.create(2);
        input.right.left.left = TreeNode.create(1);
        Assertions.assertEquals(2, longestConsecutive(input));
    }

    @Test
    public void check3() {
        TreeNode input = TreeNode.create(1,2,3,4,5);
        Assertions.assertEquals(2, longestConsecutive(input));
    }

}

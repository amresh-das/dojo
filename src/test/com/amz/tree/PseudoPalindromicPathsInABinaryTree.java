package com.amz.tree;

import com.amz.leet.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @see "https://leetcode.com/problems/pseudo-palindromic-paths-in-a-binary-tree/"
 */
public class PseudoPalindromicPathsInABinaryTree {
    public int pseudoPalindromicPaths(TreeNode root) {
        return traverse(root, new HashMap<>());
    }

    private int traverse(TreeNode node, Map<Integer, Integer> nums) {
        if (node == null) return 0;
        int numCount = nums.compute(node.val, (k, v) -> v == null ? 1 : v + 1);
        int count = 0;
        if (node.left == null && node.right == null) {
            count += nums.values().stream().filter(n -> n % 2 == 1).count() <= 1 ? 1 : 0;
        }
        if (node.left != null) {
            count += traverse(node.left, nums);
        }
        if (node.right != null) {
            count += traverse(node.right, nums);
        }
        nums.put(node.val, numCount - 1);
        return count;
    }

    @Test
    public void check1() {
        TreeNode input = TreeNode.create(2);
        input.left = TreeNode.create(3);
        input.right = TreeNode.create(1);
        input.left.left = TreeNode.create(3);
        input.left.right = TreeNode.create(1);
        input.right.right = TreeNode.create(1);
        Assertions.assertEquals(2, pseudoPalindromicPaths(input));
    }

    @Test
    public void check2() {
        TreeNode input = TreeNode.create(2);
        input.left = TreeNode.create(1);
        input.right = TreeNode.create(1);
        input.left.left = TreeNode.create(1);
        input.left.right = TreeNode.create(3);
        input.left.right.right = TreeNode.create(1);
        Assertions.assertEquals(1, pseudoPalindromicPaths(input));
    }

    @Test
    public void check3() {
        TreeNode input = TreeNode.create(9);
        Assertions.assertEquals(1, pseudoPalindromicPaths(input));
    }

    @Test
    public void check4() {
        TreeNode input = TreeNode.create(8);
        input.left = TreeNode.create(8);
        input.left.left = TreeNode.create(7);
        input.left.right = TreeNode.create(7);
        input.left.right.left = TreeNode.create(2);
        input.left.right.left.right = TreeNode.create(8);
        input.left.right.left.right.right = TreeNode.create(1);
        input.left.right.right = TreeNode.create(4);
        input.left.right.right.right = TreeNode.create(7);
        System.out.println(input);
        Assertions.assertEquals(2, pseudoPalindromicPaths(input));
    }
}

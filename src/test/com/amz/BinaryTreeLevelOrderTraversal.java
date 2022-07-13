package com.amz;

import com.amz.leet.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @see "https://leetcode.com/problems/binary-tree-level-order-traversal/"
 */
public class BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {
        Map<Integer, List<Integer>> levelMap = new HashMap<>();
        traverse(root, levelMap, 0);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < levelMap.size(); i++) {
            result.add(levelMap.get(i));
        }
        return result;
    }

    private void traverse(final TreeNode root, final Map<Integer, List<Integer>> levelMap, final int level) {
        if (root == null) return;
        levelMap.compute(level, (k, v) -> {
            List<Integer> list = v == null ? new ArrayList<>() : v;
            list.add(root.val);
            return list;
        });
        traverse(root.left, levelMap, level + 1);
        traverse(root.right, levelMap, level + 1);
    }

    @Test
    public void check1() {
        verify(TreeNode.create(3, 9, 20, null, null, 15, 7), "[[3],[9,20],[15,7]]");
    }

    @Test
    public void check2() {
        verify(TreeNode.create(1), "[[1]]");
    }

    @Test
    public void check3() {
        verify(TreeNode.create(), "[]");
    }

    private void verify(final TreeNode input, final String expected) {
        Assertions.assertEquals(expected, "[" + levelOrder(input).stream().map(l -> "["+ l.stream().map(i -> i + "").collect(Collectors.joining(",")) + "]").collect(Collectors.joining(",")) +"]");
    }
}

package com.amz;

import com.amz.leet.TreeNode;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @see "https://leetcode.com/problems/binary-tree-level-order-traversal/"
 */
public class LevelOrderBinaryTreeTraversal {

    @Test
    void t1() {
        final TreeNode root = TreeNode.create(3,9,20,null,null,15,7);
        List<List<Integer>> expected = Lists.newArrayList(
          Lists.newArrayList(3),
          Lists.newArrayList(9,20),
          Lists.newArrayList(15,7)
        );
        List<List<Integer>> actual = levelOrder(root);
        Assertions.assertEquals(expected, actual);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        final List<List<Integer>> results = new ArrayList<>();
        traverse(root, results, 0);
        return results;
    }

    private void traverse(final TreeNode root, final List<List<Integer>> results, final int level) {
        if (root == null) return;
        if (level >= results.size()) {
            results.add(new ArrayList<>());
        }
        results.get(level).add(root.val);
        traverse(root.left, results, level + 1);
        traverse(root.right, results, level + 1);
    }
}

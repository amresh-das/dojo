package com.amz;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

/**
 * @see "https://leetcode.com/problems/binary-tree-level-order-traversal-ii/"
 */
public class BinaryTreeLevelOrderTraversalII {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        TreeMap<Integer, List<Integer>> map = new TreeMap<>(Comparator.reverseOrder());
        traverse(root, map, 0);
        return new ArrayList<>(map.values());
    }

    private void traverse(TreeNode node, TreeMap<Integer, List<Integer>> map, int level) {
        if (node == null) return;
        map.computeIfAbsent(level, k -> new ArrayList<>()).add(node.val);
        traverse(node.left, map, level + 1);
        traverse(node.right, map, level + 1);
    }

    @Test
    public void check1() {
        Assertions.assertEquals(Lists.newArrayList(Lists.newArrayList(15,7), Lists.newArrayList(9,20), Lists.newArrayList(3)),
                levelOrderBottom(TreeNode.create(3,9,20,null,null,15,7)));
    }

}

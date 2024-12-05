package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @see "https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/"
 */
public class VerticalOrderTraversalOfABinaryTree {

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        final TreeMap<Integer, TreeMap<Integer, List<Integer>>> memo = new TreeMap<>();
        traverse(root, memo, 0, 0);
        return memo.values().stream()
                .map(rowMap -> rowMap.values().stream()
                        .flatMap(l -> l.stream().sorted())
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    private void traverse(TreeNode node, TreeMap<Integer, TreeMap<Integer, List<Integer>>> memo, int row, int col) {
        if (node == null) return;
        memo.computeIfAbsent(col, c -> new TreeMap<>()).computeIfAbsent(row, r -> new ArrayList<>()).add(node.val);
        traverse(node.left, memo, row + 1, col - 1);
        traverse(node.right, memo, row + 1, col + 1);
    }

    @Test
    public void check1() {
        Assertions.assertEquals("[[9],[3,15],[20],[7]]", Utils.toString(verticalTraversal(TreeNode.create(3, 9, 20, null, null, 15, 7))));
    }

    @Test
    public void check2() {
        Assertions.assertEquals("[[4],[2],[1,5,6],[3],[7]]", Utils.toString(verticalTraversal(TreeNode.create(1,2,3,4,5,6,7))));
    }

    @Test
    public void check3() {
        Assertions.assertEquals("[[4],[2],[1,5,6],[3],[7]]", Utils.toString(verticalTraversal(TreeNode.create(1,2,3,4,6,5,7))));
    }

}

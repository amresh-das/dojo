package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @see "https://leetcode.com/problems/correct-a-binary-tree/"
 */
public class CorrectABinaryTree {

    public TreeNode correctBinaryTree(TreeNode root) {
        Map<Integer, List<TreeNode>> levelMap = new HashMap<>();
        Map<Integer, TreeNode> valParent = new HashMap<>();

        levelMap.put(0, new ArrayList<>());
        levelMap.get(0).add(root);
        int level = 0;
        valParent.put(root.val, null);
        while (true) {
            List<TreeNode> nodes = levelMap.get(level);
            if (nodes == null || nodes.isEmpty()) break;
            for (TreeNode n : nodes) {
                levelMap.putIfAbsent(level + 1, new ArrayList<>());
                if (n.left != null) {
                    valParent.put(n.left.val, n);
                    levelMap.get(level + 1).add(n.left);
                }
                if (n.right != null) {
                    if (valParent.containsKey(n.right.val)) {
                        TreeNode parent = valParent.get(n.val);
                        if (parent.left != null && parent.left.val == n.val) parent.left = null;
                        if (parent.right != null && parent.right.val == n.val) parent.right = null;
                        break;
                    }
                    valParent.put(n.right.val, n);
                    levelMap.get(level + 1).add(n.right);
                }
            }
            level++;
        }
        return root;
    }

    @Test
    public void check1() {
        TreeNode root = TreeNode.create(1,2,3);
        root.left.right = root.right;
        Assertions.assertEquals("[1,null,3]", toString(correctBinaryTree(root)));
    }

    @Test
    public void check2() {
        TreeNode root = TreeNode.create(8,3,1,7,null,9,4,2,null,null,null,5,6);
        root.left.left.right = root.right.right;
        Assertions.assertEquals("[8,3,1,null,null,9,4,null,null,5,6]", toString(correctBinaryTree(root)));
    }

    private String toString(final TreeNode tree) {
        List<Integer> list= new ArrayList<>();
        TreeNode node = tree;
        traverse(node, list);
        return "[" + list.stream().map(i -> i + "").collect(Collectors.joining(",")) + "]";
    }

    private void traverse(final TreeNode node, final List<Integer> list) {
        if (node == null) {
            list.add(null);
            return;
        }
        list.add(node.val);
        if (node.left == null && node.right == null) return;
        traverse(node.left, list);
        traverse(node.right, list);
    }

}

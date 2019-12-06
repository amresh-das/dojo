package com.amz.leet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    public static TreeNode create(Integer... input) {
        return prep(input, null, 0);
    }

    private static TreeNode prep(Integer[] arr, TreeNode root, int i) {
        if (i < arr.length && arr[i] != null) {
            TreeNode temp = new TreeNode(arr[i]);
            root = temp;
            root.left = prep(arr, root.left, 2 * i + 1);
            root.right = prep(arr, root.right, 2 * i + 2);
        }
        return root;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final TreeNode treeNode = (TreeNode) o;
        return val == treeNode.val &&
                Objects.equals(left, treeNode.left) &&
                Objects.equals(right, treeNode.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, left, right);
    }

    @Override
    public String toString() {
        final List<List<String>> result = new ArrayList<>();
        extractValues(this, 0, result);
        return result.stream().map(row -> String.join("", row)).collect(Collectors.joining("\n"));
    }


    private static int extractValues(final TreeNode node, int level, final List<List<String>> result) {
        final List<String> levelList = getResultForLevel(level, result);
        final List<String> nodeList = new ArrayList<>();
        if (node != null) {
            int leftIndent = 0;
            int rightIndent = 0;
            if (node.left != null) {
                leftIndent = 1 + extractValues(node.left, level + 1, result);
                nodeList.add("    ");
                nodeList.add("╔═══");
            } else {
                nodeList.add("    ");
            }
            nodeList.add(String.format("%+03d", node.val));
            if (node.right != null) {
                rightIndent = extractValues(node.right, level + 1, result) - 2;
                nodeList.add("═══╗");
            }
            int indent = Math.max(leftIndent, rightIndent);
            for (int i = 0; i < indent; i++) {
                nodeList.add(0, "    ");
            }
            levelList.addAll(nodeList);
            return indent;
        }
        return 0;
    }

    private static List<String> getResultForLevel(final int level, final List<List<String>> result) {
        final List<String> levelStr;
        if (result.size() <= level) {
            levelStr = new ArrayList<>();
            result.add(levelStr);
        } else {
            levelStr = result.get(level);
        }
        return levelStr;
    }

    public List<Integer> toArray() {
        int maxLevel = findTreeLevel(this, 0);
        final List<Integer> result = new ArrayList<>((int) Math.pow(2, maxLevel));
        result.add(this.val);
        traverse(this, 0, maxLevel, result);
        return result;
    }

    private void traverse(final TreeNode node, final int level, final int maxLevel, final List<Integer> result) {
        if (level <= maxLevel && node != null && (node.left != null || node.right != null)) {
            result.add(node.left == null ? null : node.left.val);
            result.add(node.right == null ? null : node.right.val);
            traverse(node.left, level + 1, maxLevel, result);
            traverse(node.right, level + 1, maxLevel, result);
        }
    }

    private int findTreeLevel(final TreeNode node, final int level) {
        if (node == null) return level;
        return Math.max(findTreeLevel(node.left, level + 1), findTreeLevel(node.right, level + 1));
    }
}
 
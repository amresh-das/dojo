package com.amz;

import com.amz.leet.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @see "https://leetcode.com/problems/binary-tree-inorder-traversal/"
 */
public class BinaryTreeInorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        final List<Integer> ans = new ArrayList<>();
        traverse(root, ans);
        return ans;
    }

    public void traverse(final TreeNode node, final List<Integer> ans) {
        if (node == null) return;
        traverse(node.left, ans);
        ans.add(node.val);
        traverse(node.right, ans);
    }
}

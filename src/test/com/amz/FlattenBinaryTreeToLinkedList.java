package com.amz;

import com.amz.leet.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/flatten-binary-tree-to-linked-list/"
 */
public class FlattenBinaryTreeToLinkedList {

    public void flatten(TreeNode root) {
        if (root == null) return;
        if (root.left != null) {
            flatten(root.left);
            TreeNode right = root.right;
            root.right = root.left;
            root.left = null;
            if (right != null) {
                TreeNode node = root.right;
                while(node.right != null) node = node.right;
                node.right = right;
            }
        }
        flatten(root.right);
    }

    @Test
    public void check1() {
        TreeNode input = TreeNode.create(1,2,5,3,4,null,6);
        flatten(input);
        Assertions.assertEquals(TreeNode.create(1,null,2,null,3,null,4,null,5,null,6), input);
    }

}

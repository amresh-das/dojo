package com.amz;

import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/delete-node-in-a-bst/description/?envType=study-plan-v2&envId=leetcode-75"
 */
public class DeleteNodeInBSTTest {

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            TreeNode successor = findSuccessor(root);
            root.val = successor.val;
            root.right = deleteNode(root.right, root.val);
        }
        return root;
    }

    private TreeNode findSuccessor(final TreeNode root) {
        TreeNode node = root.right;
        while (node != null && node.left != null) {
            node = node.left;
        }
        return node;
    }

    @Test
    void t1() {
        TreeNode n5 = new TreeNode(5);
        TreeNode n3 = new TreeNode(3);
        TreeNode n6 = new TreeNode(6);
        TreeNode n2 = new TreeNode(2);
        TreeNode n4 = new TreeNode(4);
        TreeNode n7 = new TreeNode(7);
        n5.left = n3; n5.right = n6;
        n3.left = n2; n3.right = n4;
        n6.right = n7;

        final TreeNode out = deleteNode(n5, 3);
        System.out.println(out);
    }
}


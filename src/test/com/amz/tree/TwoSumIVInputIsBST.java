package com.amz.tree;

import com.amz.leet.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @see "https://leetcode.com/problems/two-sum-iv-input-is-a-bst/"
 */
public class TwoSumIVInputIsBST {

    public boolean findTarget(TreeNode root, int k) {
        final List<Integer> list = new ArrayList<>();
        inorder(root, list);
        int l = 0, r = list.size() - 1;
        while (l < r) {
            int sum = list.get(l) + list.get(r);
            if (sum == k) return true;
            if (sum < k) {
                l++;
            } else {
                r--;
            }
        }
        return false;
    }

    private void inorder(final TreeNode node, final List<Integer> list) {
        if (node != null) {
            inorder(node.left, list);
            list.add(node.val);
            inorder(node.right, list);
        }
    }

    @Test
    public void check1() {
        TreeNode root = TreeNode.create(5);
        root.left = TreeNode.create(3);
        root.left.left = TreeNode.create(2);
        root.left.right = TreeNode.create(4);
        root.right = TreeNode.create(6);
        root.right.right = TreeNode.create(7);

        Assertions.assertTrue(findTarget(root, 9));
    }

    @Test
    public void check2() {
        TreeNode root = TreeNode.create(5);
        root.left = TreeNode.create(3);
        root.left.left = TreeNode.create(2);
        root.left.right = TreeNode.create(4);
        root.right = TreeNode.create(6);
        root.right.right = TreeNode.create(7);

        Assertions.assertFalse(findTarget(root, 28));
    }
}

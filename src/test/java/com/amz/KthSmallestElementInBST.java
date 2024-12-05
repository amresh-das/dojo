package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * @see "https://leetcode.com/problems/kth-smallest-element-in-a-bst/"
 */
public class KthSmallestElementInBST {

    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (true) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            if (--k == 0) return node.val;
            node = node.right;
        }
    }

    @Test
    public void check1() {
        Assertions.assertEquals(1, kthSmallest(TreeNode.create(3,1,4,null,2), 1));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(3, kthSmallest(TreeNode.create(5,3,6,2,4,null,null,1), 3));
    }

}

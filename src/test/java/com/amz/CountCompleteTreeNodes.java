package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * @see "https://leetcode.com/problems/count-complete-tree-nodes/"
 */
public class CountCompleteTreeNodes {

    public int countNodes(TreeNode root) {
        int count = 0;
        final Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode n = stack.pop();
            if (n != null) {
                count++;
                stack.push(n.left);
                stack.push(n.right);
            }
        }
        return count;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(6, countNodes(TreeNode.create(1,2,3,4,5,6)));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(4, countNodes(TreeNode.create(1,2,3,4)));
    }

}

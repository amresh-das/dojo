package com.amz;

import com.amz.leet.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/construct-string-from-binary-tree/"
 */
public class ConstructStringFromBinaryTree {

    public String tree2str(TreeNode root) {
        if (root == null) return "";
        final StringBuilder output = new StringBuilder();
        traverse(root, output);
        return output.toString();
    }

    private void traverse(TreeNode node, StringBuilder output) {
        if (node == null) return;
        output.append(node.val);
        if (node.left == null) {
            if (node.right != null) {
                output.append("()");
            }
        } else {
            output.append('(');
            traverse(node.left, output);
            output.append(')');
        }
        if (node.right != null) {
            output.append('(');
            traverse(node.right, output);
            output.append(')');
        }
    }

    @Test
    public void check1() {
        TreeNode input = TreeNode.create(1);
        input.left = TreeNode.create(2);
        input.right = TreeNode.create(3);
        input.left.left = TreeNode.create(4);
        Assertions.assertEquals("1(2(4))(3)", tree2str(input));
    }

    @Test
    public void check2() {
        TreeNode input = TreeNode.create(1);
        input.left = TreeNode.create(2);
        input.right = TreeNode.create(3);
        input.left.right = TreeNode.create(4);
        Assertions.assertEquals("1(2()(4))(3)", tree2str(input));
    }

}

package com.amz.tree;

import com.amz.Utils;
import com.amz.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @see "https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/"
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (postorder.length == 0 || inorder.length == 0) return null;
        int rootVal = postorder[postorder.length - 1];
        TreeNode root = new TreeNode(rootVal);
        int index = find(inorder, rootVal);
        int[] leftInorder = Arrays.copyOfRange(inorder, 0, index);
        int[] leftPostorder = Arrays.copyOfRange(postorder, 0, index);
        int[] rightInorder = Arrays.copyOfRange(inorder, index + 1, inorder.length);
        int[] rightPostorder = Arrays.copyOfRange(postorder, postorder.length - rightInorder.length - 1, postorder.length - 1);
        root.right = buildTree(rightInorder, rightPostorder);
        root.left = buildTree(leftInorder, leftPostorder);
        return root;
    }

    private int find(int[] array, int val) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == val) return i;
        }
        return -1;
    }

    @Test
    public void check1() {
        System.out.println(buildTree(Utils.stringToIntArray("[9,3,15,20,7]"), Utils.stringToIntArray("[9,15,7,20,3]")));
    }

}

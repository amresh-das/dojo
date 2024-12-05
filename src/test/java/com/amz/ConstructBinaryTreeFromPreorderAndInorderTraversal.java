package com.amz;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @see "https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/"
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    private Map<Integer, Integer> inOrderIndexes;
    private int preOrderIndex;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preOrderIndex = 0;
        inOrderIndexes = new HashMap<>();
        for (int i = 0; i < preorder.length; i++) {
            inOrderIndexes.put(inorder[i], i);
        }
        return buildTreeFromArray(preorder, 0, preorder.length - 1);
    }

    private TreeNode buildTreeFromArray(final int[] preorder, final int left, final int right) {
        if (left > right) return null;
        int val = preorder[preOrderIndex++];
        TreeNode node = new TreeNode(val);
        node.left = buildTreeFromArray(preorder, left, inOrderIndexes.get(val) - 1);
        node.right = buildTreeFromArray(preorder, inOrderIndexes.get(val) + 1, right);
        return node;
    }

    @Test
    public void check1() {
        System.out.println(buildTree(Utils.stringToIntArray("[3,9,20,15,7]"), Utils.stringToIntArray("[9,3,15,20,7]")));
    }

    @Test
    public void check2() {
        System.out.println(buildTree(Utils.stringToIntArray("[-1]"), Utils.stringToIntArray("[-1]")));
    }

}

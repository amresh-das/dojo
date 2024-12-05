package com.amz;

/**
 * @see "https://leetcode.com/problems/count-good-nodes-in-binary-tree/description/?envType=study-plan-v2&envId=leetcode-75"
 */
public class CountGoodNodesInBinaryTreeTest {

    public int goodNodes(TreeNode root) {
        if (root == null) return 0;
        return 1 + countGoodNodes(root.left, root.val) + countGoodNodes(root.right, root.val);
    }

    private int countGoodNodes(final TreeNode root, final int maxSoFar) {
        if (root == null) return 0;
        int count = root.val < maxSoFar ? 0 : 1;
        int max = root.val < maxSoFar ? maxSoFar : root.val;
        return count + countGoodNodes(root.left, max) + countGoodNodes(root.right, max);
    }

}

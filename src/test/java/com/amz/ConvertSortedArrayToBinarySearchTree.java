package com.amz;

/**
 * @see "https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/submissions/"
 */
public class ConvertSortedArrayToBinarySearchTree {

    public TreeNode sortedArrayToBST(int[] nums) {
        return toBST(nums, 0, nums.length - 1);
    }

    private TreeNode toBST(int[] nums, int lo, int hi) {
        if (lo > hi) return null;
        int mid = (lo + hi) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = toBST(nums, lo, mid - 1);
        node.right = toBST(nums, mid + 1, hi);
        return node;
    }

}

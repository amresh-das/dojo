package com.amz;

import java.util.ArrayList;
import java.util.List;

/**
 * @see "https://leetcode.com/problems/leaf-similar-trees/description/?envType=study-plan-v2&envId=leetcode-75"
 */
public class LeafSimilarTreesTest {

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        List<Integer> leaves1 = collectLeaves(root1, new ArrayList<>());
        List<Integer> leaves2 = collectLeaves(root2, new ArrayList<>());
        return leaves1.equals(leaves2);
    }

    private List<Integer> collectLeaves(final TreeNode root, final List<Integer> leaves) {
        if (root == null) return leaves;
        if (root.left == null && root.right == null) {
            leaves.add(root.val);
        } else {
            collectLeaves(root.left, leaves);
            collectLeaves(root.right, leaves);
        }
        return leaves;
    }

}

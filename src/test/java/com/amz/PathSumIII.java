package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @see "https://leetcode.com/problems/path-sum-iii/"
 */
public class PathSumIII {

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) return 0;
        List<Long> prefixSums = new ArrayList<>();
        prefixSums.add(0L);
        return pathSum(root, targetSum, prefixSums);
    }

    private int pathSum(TreeNode node, int targetSum, List<Long> prefixSums) {
        int count = 0;
        if (node != null) {
            int size = prefixSums.size();
            long sum = prefixSums.get(size - 1) + node.val;
            prefixSums.add(sum);
            for (int i = size - 1; i >= 0; i--) {
                if (sum - prefixSums.get(i) == targetSum) {
                    count++;
                }
            }
            count += pathSum(node.left, targetSum, prefixSums);
            count += pathSum(node.right, targetSum, prefixSums);
            prefixSums.remove(size);
        }
        return count;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(3, pathSum(TreeNode.create(10,5,-3,3,2,null,11,3,-2,null,1), 8));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(3, pathSum(TreeNode.create(5,4,8,11,null,13,4,7,2,null,null,5,1), 22));
    }

    @Test
    public void check3() {
        TreeNode n1 = TreeNode.create(1);
        TreeNode n2 = TreeNode.create(2);
        TreeNode n3 = TreeNode.create(3);
        TreeNode n4 = TreeNode.create(4);
        TreeNode n5 = TreeNode.create(5);
        n1.right = n2;
        n2.right = n3;
        n3.right = n4;
        n4.right = n5;
        Assertions.assertEquals(2, pathSum(n1, 3));
    }

    @Test
    public void check4() {
        TreeNode n1 = TreeNode.create(1);
        Assertions.assertEquals(0, pathSum(n1, 0));
    }
}

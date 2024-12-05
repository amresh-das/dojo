package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @see "https://leetcode.com/problems/binary-tree-maximum-path-sum/"
 */
public class BinaryTreeMaximumPathSum {

    public int maxPathSum(TreeNode root) {
        if (root.left == null && root.right == null) return root.val;
        int[] left = sum(root.left);
        int[] right = sum(root.right);
        final List<Integer> options = new ArrayList<>();
        options.add(root.val);
        if (left != null) {
            options.add(left[0]);
            options.add(left[1]);
            options.add(left[1] + root.val);
        }
        if (right != null) {
            options.add(right[0]);
            options.add(right[1]);
            options.add(root.val + right[1]);
        }
        if (left != null && right != null) {
            options.add(left[1] + root.val + right[1]);
        }
        return options.stream().max(Integer::compareTo).orElse(0);
    }

    private int[] sum(TreeNode node) {
        if (node == null) return null;
        int[] left = sum(node.left);
        int[] right = sum(node.right);
        if (left == null && right == null) {
            return new int[] {node.val, node.val};
        } else if (left == null) {
            return new int[] {
                Stream.of(right[0], right[1]).max(Integer::compareTo).get(),
                Stream.of(node.val, node.val + right[1]).max(Integer::compareTo).get()
            };
        } else if (right == null) {
            return new int[] {
                Stream.of(left[0], left[1]).max(Integer::compareTo).get(),
                Stream.of(node.val, left[1] + node.val).max(Integer::compareTo).get()
            };
        } else {
            return new int[] {
                Stream.of(left[0], right[0], left[1], right[1], left[1] + node.val + right[1]).max(Integer::compareTo).get(),
                Stream.of(node.val, left[1] + node.val, node.val + right[1]).max(Integer::compareTo).get()
            };
        }
    }

    @Test
    public void check1() {
        Assertions.assertEquals(6, maxPathSum(TreeNode.create(1,2,3)));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(42, maxPathSum(TreeNode.create(-10,9,20,null,null,15,7)));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(2, maxPathSum(TreeNode.create(2,-1,-2)));
    }

    @Test
    public void check4() {
        Assertions.assertEquals(-3, maxPathSum(TreeNode.create(-3)));
    }

    @Test
    public void check5() {
        Assertions.assertEquals(10, maxPathSum(TreeNode.create(-1,-2,10,-6,null,-3,-6)));
    }

    @Test
    public void check6() {
        Assertions.assertEquals(3, maxPathSum(TreeNode.create(1,-2,-3,1,3,-2,null,-1)));
    }

}

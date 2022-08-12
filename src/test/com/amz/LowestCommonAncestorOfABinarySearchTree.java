package com.amz;

import com.amz.leet.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @see "https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/"
 */
public class LowestCommonAncestorOfABinarySearchTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int a = Math.min(p.val, q.val);
        int b = Math.max(p.val, q.val);
        if (root.val == a || root.val == b) {
            return root;
        } else if (a < root.val && b > root.val) {
            return root;
        } else {
            if (root.val > b) {
                return lowestCommonAncestor(root.left, p, q);
            } else {
                return lowestCommonAncestor(root.right, p, q);
            }
        }
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        return (TreeNode) find(root, Stream.of(p.val, q.val).collect(Collectors.toSet()))[0];
    }

    private Object[] find(final TreeNode root, final Set<Integer> target) {
        if (root == null) return new Object[] {null, false};
        final Object[] left = find(root.left, target);
        if (left[0] != null) {
            return left;
        }
        final Object[] right = find(root.right, target);
        if (right[0] != null) {
            return right;
        }
        final boolean isInRoot = target.contains(root.val);
        final long count = Stream.of((boolean) left[1], (boolean) right[1], isInRoot).filter(x -> x).count();
        return new Object[] {count == 2 ? root : null, count > 0};
    }

    @Test
    public void check1() {
        Assertions.assertEquals(6, lowestCommonAncestor(TreeNode.create(6,2,8,0,4,7,9,null,null,3,5), TreeNode.create(2), TreeNode.create(8)).val);
    }

    @Test
    public void check2() {
        Assertions.assertEquals(2, lowestCommonAncestor(TreeNode.create(6,2,8,0,4,7,9,null,null,3,5), TreeNode.create(2), TreeNode.create(4)).val);
    }
}

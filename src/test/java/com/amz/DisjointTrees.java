package com.amz;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class DisjointTrees {

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        final List<TreeNode> roots = new ArrayList<>();
        roots.add(root);
        traverseAndDelete(root, null, Arrays.stream(to_delete).boxed().collect(Collectors.toList()), roots);
        return roots;
    }

    private void traverseAndDelete(final TreeNode node, final TreeNode parent, final List<Integer> toDelete, final List<TreeNode> roots) {
        if (node == null || toDelete.isEmpty()) return;
        traverseAndDelete(node.left, node, toDelete, roots);
        traverseAndDelete(node.right, node, toDelete, roots);
        if (toDelete.contains(node.val)) {
            if (parent != null) {
                if (parent.left != null && parent.left.val == node.val) parent.left = null;
                if (parent.right != null && parent.right.val == node.val) parent.right = null;
            } else {
                roots.removeIf(r -> r.val == node.val);
            }
            if (node.left != null) roots.add(node.left);
            if (node.right != null) roots.add(node.right);
            toDelete.remove(Integer.valueOf(node.val));
        }
    }

    @Test
    public void check1() {
        Set<TreeNode> roots = new HashSet<>(delNodes(TreeNode.create(1,2,3,4,5,6,7), new int[] {3,5}));
        Assertions.assertEquals(Sets.newHashSet(TreeNode.create(1,2,null,4), TreeNode.create(6), TreeNode.create(7)), roots);
    }

    @Test
    public void check2() {
        Set<TreeNode> roots = new HashSet<>(delNodes(TreeNode.create(1,2,4,null,3), new int[] {3}));
        Assertions.assertEquals(Sets.newHashSet(TreeNode.create(1,2,4)), roots);
    }

}

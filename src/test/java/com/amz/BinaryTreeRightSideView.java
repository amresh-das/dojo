package com.amz;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @see "https://leetcode.com/problems/binary-tree-right-side-view/"
 */
public class BinaryTreeRightSideView {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traverse(root, 0, result);
        return result;
    }

    private void traverse(final TreeNode node, final int height, final List<Integer> result) {
        if (node != null) {
            if (result.size() > height) {
                result.set(height, node.val);
            } else {
                result.add(node.val);
            }
            traverse(node.left, height + 1, result);
            traverse(node.right, height + 1, result);
        }
    }

    @Test
    public void check1() {
        Assertions.assertEquals(Lists.newArrayList(1,3,4), rightSideView(TreeNode.create(1,2,3,null,5,null,4)));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(Lists.newArrayList(1,3), rightSideView(TreeNode.create(1, null, 3)));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(Lists.newArrayList(), rightSideView(null));
    }

    @Test
    public void check4() {
        Assertions.assertEquals(Lists.newArrayList(1, 2), rightSideView(TreeNode.create(1, 2)));
    }


}

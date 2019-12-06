package com.amz.leet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class TreeNodeTest {

    @Test
    public void shouldInitializeFromArrayAndBack() {
        Integer[] n1 = {1, 2, -3, -5, null, 4, null};
        TreeNode node = TreeNode.create(n1);
        Assertions.assertEquals(Arrays.asList(n1), node.toArray());

        Integer[] n2 = {1, 2, 3, 4, -99, -99, 7, 8, 9, -99, -99, 12, 13, -99, 14};
        node = TreeNode.create(n2);
        Assertions.assertEquals(Arrays.asList(n2), node.toArray());
    }

    @Test
    public void checkToString() {
        TreeNode node = TreeNode.create(1,2,-3,-5,null,4,null);
        System.out.println(node.toString());

        node = TreeNode.create(1,2,3,4,-99,-99,7,8,9,-99,-99,12,13,-99,14);
        System.out.println(node.toString());
    }
}
package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @see "https://leetcode.com/problems/longest-path-with-different-adjacent-characters/"
 */
public class LongestPathWithDifferentAdjacentCharacters {

    class Node {
        Character val;
        List<Node> children = new ArrayList<>();

        Node(Character val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", children=" + children +
                    '}';
        }
    }

    public int longestPath(int[] parent, String s) {
        final Node[] nodes = new Node[s.length()];
        for (int i = 0; i < s.length(); i++) {
            nodes[i] = new Node(s.charAt(i));
            if (parent[i] >= 0) {
                nodes[parent[i]].children.add(nodes[i]);
            }
        }
        System.out.println(nodes[0]);
        return 0;
    }

    @Test
    public void check1() {
        verify("[-1,0,0,1,1,2]", "abacbe", 3);
    }

    @Test
    public void check2() {
        verify("[-1,0,0,0]", "aabc", 3);
    }

    private void verify(final String parent, final String s, final int expected) {
        Assertions.assertEquals(expected, longestPath(Utils.stringToIntArray(parent), s));
    }
}

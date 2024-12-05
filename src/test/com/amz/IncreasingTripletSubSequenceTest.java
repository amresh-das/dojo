package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @see "https://leetcode.com/problems/increasing-triplet-subsequence/description/?envType=study-plan-v2&envId=leetcode-75"
 */
public class IncreasingTripletSubSequenceTest {

    @Test
    void check1() {
        Assertions.assertTrue(increasingTriplet(new int[] {1,2,3,4,5}));
    }

    @Test
    void check2() {
        Assertions.assertFalse(increasingTriplet(new int[] {5,4,3,2,1}));
    }

    @Test
    void check3() {
        Assertions.assertFalse(increasingTriplet(new int[] {0,4,2,1,0,-1,-3}));
    }

    @Test
    void check4() {
        Assertions.assertTrue(increasingTriplet(new int[] {20,100,10,12,5,13}));
    }

    @Test
    void check5() {
        Assertions.assertFalse(increasingTriplet(new int[] {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}));
    }

    @Test
    void check6() {
        Assertions.assertTrue(increasingTriplet(new int[] {1,5,0,4,1,3}));
    }

    @Test
    void check7() {
        Assertions.assertFalse(increasingTriplet(new int[] {1,1,2,1,0,1}));
    }

    public boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) return false;
        int selCount = 3;
        int[] sel = new int[selCount - 1];
        Arrays.fill(sel, Integer.MAX_VALUE);

        for (int n : nums) {
            for (int x = 0; x < selCount; x++) {
                if (x == selCount - 1) return true;
                if (n <= sel[x]) {
                    sel[x] = n;
                    break;
                }
            }
        }
        return false;
    }

    public boolean increasingTripletTree(int[] nums) {
        final List<Tree> trees = new ArrayList<>();
        for (final int num : nums) {
            int level = trees.stream().map(tree -> tree.addValue(num)).max(Comparator.comparingInt(t -> t)).orElse(-1);
            if (level == 2) return true;
            if (level == -1) trees.add(new Tree(num, 0));
        }
        return false;
    }

    static class Tree {
        int value;
        int level = 0;
        List<Tree> children = new ArrayList<>();

        public Tree(final int value, final int level) {
            this.value = value;
            this.level = level;
        }

        int addValue(final int n) {
            if (n == value) return level;
            if (n < value) return -1;
            int childLevel = children.stream().map(t -> t.addValue(n)).max(Comparator.comparingInt(t -> t)).orElse(-1);
            if (childLevel == -1) {
                children.add(new Tree(n, level + 1));
                return level + 1;
            }
            return childLevel;
        }

        @Override
        public String toString() {
            return String.format("{%d-%d: [%s]}", value, level, children);
        }
    }

}

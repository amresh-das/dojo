package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @see "https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/"
 */
public class MinimumDominoRotationsForEqualRow {

    public int minDominoRotations(int[] tops, int[] bottoms) {
        final Map<Integer, Set<Integer>> indices = new HashMap<>();
        final Map<Integer, Integer> topCount = new HashMap<>();
        final Map<Integer, Integer> bottomCount = new HashMap<>();
        int numberToRotateTo = -1;
        for (int i = 0; i < tops.length; i++) {
            final int top = tops[i];
            final int bottom = bottoms[i];
            addToIndice(indices, top, i);
            addToIndice(indices, bottom, i);
            topCount.compute(top, (k, v) -> v == null ? 1 : v + 1);
            bottomCount.compute(bottom, (k, v) -> v == null ? 1 : v + 1);
            if (indices.get(top).size() == tops.length) numberToRotateTo = top;
            if (indices.get(bottom).size() == tops.length) numberToRotateTo = bottom;
        }
        return numberToRotateTo == -1 ? -1 : Math.min(tops.length - topCount.get(numberToRotateTo), tops.length - bottomCount.get(numberToRotateTo));
    }

    private void addToIndice(final Map<Integer, Set<Integer>> indices, int num, int index) {
        indices.compute(num, (k, v) -> {
            Set<Integer> set = v == null ? new HashSet<>() : v;
            set.add(index);
            return set;
        });
    }

    @Test
    public void check1() {
        Assertions.assertEquals(2, minDominoRotations(Utils.stringToIntArray("[2,1,2,4,2,2]"), Utils.stringToIntArray("[5,2,6,2,3,2]")));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(-1, minDominoRotations(Utils.stringToIntArray("[3,5,1,2,3]"), Utils.stringToIntArray("[3,6,3,3,4]")));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(1, minDominoRotations(Utils.stringToIntArray("[1,2,1,1,1,2,2,2]"), Utils.stringToIntArray("[2,1,2,2,2,2,2,2]")));
    }
}

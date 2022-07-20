package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @see "https://leetcode.com/problems/largest-values-from-labels/"
 */
public class LargestValuesFromLabels {

    public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
        final Map<Integer, List<Integer>> labelValues = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            final List<Integer> vals = labelValues.getOrDefault(labels[i], new ArrayList<>());
            vals.add(values[i]);
            labelValues.put(labels[i], vals);
        }
        return labelValues
                .values()
                .stream()
                .flatMap(v -> v.stream()
                                .sorted(Comparator.reverseOrder())
                                .limit(useLimit))
                .sorted(Comparator.reverseOrder())
                .limit(numWanted)
                .reduce(0, Integer::sum);
    }

    @Test
    public void check1() {
        String values = "[5,4,3,2,1]";
        String labels = "[1,1,2,2,3]";
        int numWanted = 3;
        int useLimit = 1;
        int expected = 9;
        verify(values, labels, numWanted, useLimit, expected);
    }

    @Test
    public void check2() {
        String values = "[5,4,3,2,1]";
        String labels = "[1,3,3,3,2]";
        int numWanted = 3;
        int useLimit = 2;
        int expected = 12;
        verify(values, labels, numWanted, useLimit, expected);
    }

    @Test
    public void check3() {
        String values = "[9,8,8,7,6]";
        String labels = "[0,0,0,1,1]";
        int numWanted = 3;
        int useLimit = 1;
        int expected = 16;
        verify(values, labels, numWanted, useLimit, expected);
    }

    @Test
    public void check4() {
        String values = "[2,6,1,2,6]";
        String labels = "[2,2,2,1,1]";
        int numWanted = 1;
        int useLimit = 1;
        int expected = 6;
        verify(values, labels, numWanted, useLimit, expected);
    }

    @Test
    public void check5() {
        String values = "[3,7,2,7,2]";
        String labels = "[2,2,2,2,1]";
        int numWanted = 2;
        int useLimit = 5;
        int expected = 14;
        verify(values, labels, numWanted, useLimit, expected);
    }

    private void verify(final String values, final String labels, final int numWanted, final int useLimit, final int expected) {
        Assertions.assertEquals(expected, largestValsFromLabels(Utils.stringToIntArray(values), Utils.stringToIntArray(labels), numWanted, useLimit));
    }

}

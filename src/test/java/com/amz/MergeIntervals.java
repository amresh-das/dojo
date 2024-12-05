package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @see "https://leetcode.com/problems/merge-intervals/"
 */
public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        final List<int[]> solution = new ArrayList<>();
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int[] prevInterval = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (prevInterval[1] < intervals[i][0]) {
                solution.add(prevInterval);
                prevInterval = intervals[i];
            } else {
                prevInterval[0] = Math.min(prevInterval[0], intervals[i][0]);
                prevInterval[1] = Math.max(prevInterval[1], intervals[i][1]);
            }
        }
        solution.add(prevInterval);
        return solution.toArray(new int[solution.size()][2]);
    }

    @Test
    void t1() {
        verify("[[1,3],[2,6],[8,10],[15,18]]", "[[1,6],[8,10],[15,18]]");
    }

    @Test
    void t2() {
        verify("[[1,4],[4,5]]", "[[1,5]]");
    }

    @Test
    void t3() {
        verify("[[1,3],[4,5]]", "[[1,3],[4,5]]");
    }

    @Test
    void t4() {
        verify("[[1,4],[0,4]]", "[[0,4]]");
    }

    private void verify(final String input, final String expected) {
        final int[][] actual = merge(Utils.to2dIntMatrix(input));
        Assertions.assertEquals(expected, Utils.from2dIntMatrix(actual));
    }
}

package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @see "https://leetcode.com/problems/remove-covered-intervals/"
 */
public class RemoveCoveredIntervals {

    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int count = 0, prevEnd = -1;
        for (int[] interval : intervals) {
            int end = interval[1];
            if (prevEnd < end) {
                count++;
                prevEnd = end;
            }
        }
        return count;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(2, removeCoveredIntervals(Utils.to2dIntMatrix("[[1,4],[3,6],[2,8]]")));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(1, removeCoveredIntervals(Utils.to2dIntMatrix("[[1,4],[2,3]]")));
    }

}

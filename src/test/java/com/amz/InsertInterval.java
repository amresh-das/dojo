package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @see "https://leetcode.com/problems/insert-interval/"
 */
public class InsertInterval {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) return new int[][] {newInterval};
        final List<int[]> solution = new ArrayList<>();
        boolean isMerged = false;
        final int[] toBeAdded = new int[] {newInterval[0], newInterval[1]};
        for (int i = 0; i < intervals.length; i++) {
            final int[] current = intervals[i];
            if (!isMerged && toBeAdded[1] < current[0]) {
                solution.add(toBeAdded);
                solution.add(current);
                isMerged = true;
            } else {
                if (toBeAdded[0] > current[0] && toBeAdded[1] < current[1]) {
                    isMerged = true;
                }
                if ((toBeAdded[0] <= current[0] && toBeAdded[1] >= current[0]) || toBeAdded[0] <= current[1] && toBeAdded[1] >= current[1]) {
                    toBeAdded[0] = Math.min(toBeAdded[0], current[0]);
                    toBeAdded[1] = Math.max(toBeAdded[1], current[1]);
                } else {
                    solution.add(current);
                }
            }
        }
        if (!isMerged) {
            solution.add(toBeAdded);
        }
        return solution.toArray(new int[solution.size()][2]);
    }

    @Test
    void t1() {
        verify("[[1,3],[6,9]]", "[2,5]", "[[1,5],[6,9]]");
    }

    @Test
    void t2() {
        verify("[[1,2],[3,5],[6,7],[8,10],[12,16]]", "[4,8]", "[[1,2],[3,10],[12,16]]");
    }

    @Test
    void t3() {
        verify("[]", "[5,7]", "[[5,7]]");
    }

    @Test
    void t4() {
        verify("[[1,5]]", "[2,3]", "[[1,5]]");
    }

    @Test
    void t5() {
        verify("[[1,5]]", "[2,7]", "[[1,7]]");
    }

    @Test
    void t6() {
        verify("[[1,5]]", "[1,5]", "[[1,5]]");
    }

    @Test
    void t7() {
        verify("[[2,5],[6,7],[8,9]]", "[0,1]", "[[0,1],[2,5],[6,7],[8,9]]");
    }

    private void verify(final String intervals, final String newInterval, final String expected) {
        final int[][] actual = insert(Utils.to2dIntMatrix(intervals), Utils.stringToIntArray(newInterval));
        Assertions.assertEquals(expected, Utils.from2dIntMatrix(actual));
    }
}

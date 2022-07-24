package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @see "https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/"
 */
public class MinimumNumberOfArrowsToBurstBalloons {

    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;
        Arrays.sort(points, Comparator.comparingInt(a -> a[0]));
        int arrows = 1;
        int start = points[0][0], end = points[0][1];
        for (int[] point : points) {
            start = Math.max(start, point[0]);
            end = Math.min(end, point[1]);
            if (start > end) {
                arrows++;
                start = point[0];
                end = point[1];
            }
        }
        return arrows;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(2, findMinArrowShots(Utils.to2dIntMatrix("[[10,16],[2,8],[1,6],[7,12]]")));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(4, findMinArrowShots(Utils.to2dIntMatrix("[[1,2],[3,4],[5,6],[7,8]]")));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(2, findMinArrowShots(Utils.to2dIntMatrix("[[1,2],[2,3],[3,4],[4,5]]")));
    }

    @Test
    public void check4() {
        Assertions.assertEquals(2, findMinArrowShots(Utils.to2dIntMatrix("[[3,9],[7,12],[3,8],[6,8],[9,10],[2,9],[0,9],[3,9],[0,6],[2,8]]")));
    }

    @Test
    public void check5() {
        Assertions.assertEquals(2, findMinArrowShots(Utils.to2dIntMatrix("[[9,12],[1,10],[4,11],[8,12],[3,9],[6,9],[6,7]]")));
    }

}

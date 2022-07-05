package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @see "https://leetcode.com/problems/maximum-area-of-a-piece-of-cake-after-horizontal-and-vertical-cuts/"
 */
public class MaximumAreaOfAPieceOfCakeAfterHorizontalAndVerticalCuts {
    private final int MODULO = ((int) Math.pow(10, 9)) + 7;

    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        long yBlock = computeMaxBlock(horizontalCuts, h);
        long xBlock = computeMaxBlock(verticalCuts, w);
        return (int) (xBlock * yBlock % MODULO);
    }

    private long computeMaxBlock(final int[] cuts, final int total) {
        long max = 0;
        int prev = 0;
        Arrays.sort(cuts);
        for (int n: cuts) {
            max = Math.max(max, n - prev);
            prev = n;
        }
        max = Math.max(max, total - prev);
        return max;
    }

    public int maxArea2(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        List<Long> yCuts = Arrays.stream(horizontalCuts).boxed().map(Long::valueOf).sorted().collect(Collectors.toList());
        yCuts.add(0, 0L);
        yCuts.add((long) h);
        List<Long> xCuts = Arrays.stream(verticalCuts).boxed().map(Long::valueOf).sorted().collect(Collectors.toList());
        xCuts.add(0, 0L);
        xCuts.add((long) w);

        long maxArea = 0;
        for (int i = 1; i < yCuts.size(); i++) {
            long y = yCuts.get(i);
            for (int j = 1; j < xCuts.size(); j++) {
                long x = xCuts.get(j);
                long area = ((y - yCuts.get(i - 1)) * (x - xCuts.get(j - 1)));
                maxArea = Math.max(maxArea, area);
            }
        }
        return (int) (maxArea % MODULO);
    }

    @Test
    public void check1() {
        verify(5, 4, "[1,2,4]", "[1,3]", 4);
    }

    @Test
    public void check2() {
        verify(5, 4, "[3,1]", "[1]", 6);
    }

    @Test
    public void check3() {
        verify(5, 4, "[3]", "[3]", 9);
    }

    @Test
    public void check4() {
        verify(1000000000, 1000000000, "[2]", "[2]", 81);
    }

    private void verify(final int h, final int w, final String horizontalCuts, final String verticalCuts, final int expected) {
        System.out.printf("%d x %d horizontal: %s vertical: %s", h, w, horizontalCuts, verticalCuts);
        Assertions.assertEquals(expected, maxArea(h, w, Utils.stringToIntArray(horizontalCuts), Utils.stringToIntArray(verticalCuts)));
    }

}

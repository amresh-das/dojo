package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @see "https://leetcode.com/problems/trapping-rain-water"
 */
public class TrappingRainWater {

    @Test
    void t0() {
        Assertions.assertEquals(1, apply(1, 0, 1));
    }

    @Test
    void t1() {
        Assertions.assertEquals(2, apply(1, 0, 0, 1));
    }

    @Test
    void t2() {
        Assertions.assertEquals(1, apply(1, 0, 1, 1));
    }

    @Test
    void t3() {
        Assertions.assertEquals(6, apply(0,1,0,2,1,0,1,3,2,1,2,1));
    }

    private int apply(final int... values) {
        System.out.println(Arrays.stream(values).mapToObj(i -> i + "").collect(Collectors.joining(",")));
        return trap(values);
    }

    public int trap(int[] height) {
        final AtomicInteger water = new AtomicInteger();
        int start = 0;
        while ((start = nextTrough(start, height, water)) != -1);
        return water.get();
    }

    private int nextTrough(int offset, int[] height, final AtomicInteger water) {
        if (offset >= height.length - 1) return -1;
        int start = offset;
        while (start < height.length - 1 && height[start] <= height[start + 1]) start++;
        int end = start + 1;
        int maxSoFar = -1;
        int maxSoFarIndex = -1;
        while(++end < height.length) {
            if (maxSoFar <= height[end]) {
                maxSoFar = height[end];
                maxSoFarIndex = end;
            }
            if (height[end] >= height[start]) {
                break;
            }
        }
        if (maxSoFarIndex == -1) return -1;
        int minHeight = Math.min(height[start], maxSoFar);
        int area = minHeight * (maxSoFarIndex - start + 1);
        for (int i = start; i <= maxSoFarIndex; i++) {
            area -= Math.min(height[i], minHeight);
        }
        water.addAndGet(area);
        return maxSoFarIndex;
    }

}

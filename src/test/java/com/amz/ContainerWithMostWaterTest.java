package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/container-with-most-water/description/?envType=study-plan-v2&envId=leetcode-75"
 */
public class ContainerWithMostWaterTest {

    public int maxArea(int[] height) {
        if (height.length < 2) return 0;
        int maxArea = Integer.MIN_VALUE;
        int left = 0, right = height.length - 1;
        while (left < right) {
            maxArea = Math.max(maxArea, Math.min(height[left], height[right]) * (right - left));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

    @Test
    void check1() {
        Assertions.assertEquals(49, maxArea(new int[] {1,8,6,2,5,4,8,3,7}));
    }

    @Test
    void check2() {
        Assertions.assertEquals(1, maxArea(new int[] {1,1}));
    }
}

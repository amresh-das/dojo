package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/maximum-subarray/"
 */
public class MaximumSubarray {

    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int current = 0;
        for (int i = 0; i < nums.length; i++) {
            current = nums[i] + Math.max(current, 0);
            maxSum = Math.max(maxSum, current);
        }
        return maxSum;
    }

    @Test
    void t1() {
        verify("[-2,1,-3,4,-1,2,1,-5,4]", 6);
    }

    @Test
    void t2() {
        verify("[1]", 1);
    }

    @Test
    void t3() {
        verify("[5,4,-1,7,8]", 23);
    }

    private void verify(final String input, final int expected) {
        Assertions.assertEquals(expected, maxSubArray(Utils.stringToIntArray(input)));
    }
}

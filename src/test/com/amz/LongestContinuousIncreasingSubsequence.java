package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/longest-continuous-increasing-subsequence/"
 */
public class LongestContinuousIncreasingSubsequence {

    public int findLengthOfLCIS(int[] nums) {
        int maxLength = 0;
        int length = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] < nums[i]) {
                length++;
            } else {
                maxLength = Math.max(length, maxLength);
                length = 1;
            }
        }
        return Math.max(maxLength, length);
    }

    @Test
    public void check1() {
        Assertions.assertEquals(3, findLengthOfLCIS(new int[] {1,3,5,4,7}));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(4, findLengthOfLCIS(new int[] {1,3,5,7}));
    }


}

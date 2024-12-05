package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/"
 */
public class LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {

    public int longestSubarray(int[] nums, int limit) {
        if (nums.length == 0) return 0;
        int maxLen = 1;
        for (int left = 0; left < nums.length; left++) {
            int max = nums[left], min = nums[left];
            for (int right = left + 1; right < nums.length; right++) {
                max = Math.max(max, nums[right]);
                min = Math.min(min, nums[right]);
                if (maxLen > right - left) continue;
                if (Math.abs(max - min) <= limit) {
                    maxLen = Math.max(maxLen, right - left + 1);
                    if (maxLen == nums.length - left) return maxLen;
                }
            }
        }
        return maxLen;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(2, longestSubarray(Utils.stringToIntArray("[8,2,4,7]"), 4));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(4, longestSubarray(Utils.stringToIntArray("[10,1,2,4,7,2]"), 5));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(3, longestSubarray(Utils.stringToIntArray("[4,2,2,2,4,4,2,2]"), 0));
    }
}

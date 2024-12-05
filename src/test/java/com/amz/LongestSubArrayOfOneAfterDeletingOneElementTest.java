package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/?envType=study-plan-v2&envId=leetcode-75"
 */
public class LongestSubArrayOfOneAfterDeletingOneElementTest {

    public int longestSubarray(int[] nums) {
        int result = 0;
        int prefix = 0, suffix = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                suffix++;
            } else {
                prefix = suffix;
                suffix = 0;
            }
            result = Math.max(result, prefix + suffix);
        }
        return result == nums.length ? result - 1 : result;
    }

    @Test
    void check1() {
        Assertions.assertEquals(3, longestSubarray(new int[] {1,1,0,1}));
    }

    @Test
    void check2() {
        Assertions.assertEquals(5, longestSubarray(new int[] {0,1,1,1,0,1,1,0,1}));
    }

    @Test
    void check3() {
        Assertions.assertEquals(2, longestSubarray(new int[] {1,1,1}));
    }

    @Test
    void check4() {
        Assertions.assertEquals(3, longestSubarray(new int[] {1,1,0,1}));
    }
}

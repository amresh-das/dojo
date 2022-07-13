package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @see "https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/"
 */
public class MaximumSizeSubarraySumEqualsK {

    public int maxSubArrayLen(int[] nums, int k) {
        final Map<Integer, Integer> indices = new HashMap<>();
        int prefixSum = 0;
        int maxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            if (prefixSum == k) {
                maxLen = Math.max(maxLen, i + 1);
            }
            if (indices.containsKey(prefixSum - k)) {
                maxLen = Math.max(maxLen, i - indices.get(prefixSum - k));
            }
            indices.putIfAbsent(prefixSum, i);
        }
        return maxLen;
    }

    public int maxSubArrayLen2(int[] nums, int k) {
        if (nums.length == 0) return 0;
        int maxLen = -1;
        for (int left = 0; left < nums.length; left++) {
            int sum = 0;
            for (int right = left; right < nums.length; right++) {
                sum += nums[right];
                if (sum == k) {
                    maxLen = Math.max(maxLen, right - left + 1);
                }
            }
        }
        return maxLen == -1 ? 0 : maxLen;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(4, maxSubArrayLen(Utils.stringToIntArray("[1,-1,5,-2,3]"), 3));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(2, maxSubArrayLen(Utils.stringToIntArray("[-2,-1,2,1]"), 1));
    }
}

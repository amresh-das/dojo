package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @see "https://leetcode.com/problems/max-number-of-k-sum-pairs/?envType=study-plan-v2&envId=leetcode-75"
 */
public class MaxNumberOfKSumPairsTest {

    public int maxOperations(int[] nums, int k) {
        int count = 0;
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1;
        if (right >= 0 && nums[right] > k) right--;
        while (left < right) {
            final int sum = nums[left] + nums[right];
            if (sum == k) {
                count++;
                left++;
                right--;
            } else if (sum > k) {
                right--;
            } else {
                left++;
            }
        }
        return count;
    }

    public int maxOperations2(int[] nums, int k) {
        int count = 0;
        final Map<Integer, Integer> freq = new HashMap<>();
        for (final int num : nums) {
            final int comp = k - num;
            if (freq.containsKey(comp)) {
                count++;
                if (freq.compute(comp, (key, val) -> val == null ? 0 : val - 1) == 0) {
                    freq.remove(comp);
                }
            } else {
                freq.compute(num, (key, val) -> val == null ? 1 : val + 1);
            }
        }
        return count;
    }

    @Test
    void check1() {
        Assertions.assertEquals(1, maxOperations(new int[] {3,1,3,4,3}, 6));
    }

    @Test
    void check2() {
        Assertions.assertEquals(2, maxOperations(new int[] {1,2,3,4}, 5));
    }
}

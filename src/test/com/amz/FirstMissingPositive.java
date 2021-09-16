package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/first-missing-positive/"
 */
public class FirstMissingPositive {

    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        int i = 0;
        while (i < n) {
            if (nums[i] >= 0 && nums[i] < n && nums[nums[i]] != nums[i]) {
                swap(nums, nums[i], i);
            } else {
                i++;
            }
        }
        i = 1;
        while (i < n && i == nums[i]) {
            i++;
        }
        if (n == 0 || i < n) {
            return i;
        }
        return nums[0] == i ? i + 1 : i;
    }

    private void swap(final int[] nums, final int i, final int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    @Test
    void t1() {
        Assertions.assertEquals(3, firstMissingPositive(new int[] {1,2,0}));
    }

    @Test
    void t2() {
        Assertions.assertEquals(2, firstMissingPositive(new int[] {3,4,-1,1}));
    }

    @Test
    void t3() {
        Assertions.assertEquals(1, firstMissingPositive(new int[] {7,8,9,11,12}));
    }
}

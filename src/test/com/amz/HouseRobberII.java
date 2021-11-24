package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/house-robber-ii/"
 */
public class HouseRobberII {

    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[1];

        return Math.max(robRange(nums, 0, nums.length - 2), robRange(nums, 1, nums.length - 1));
    }

    private int robRange(int[] nums, int low, int high) {
        int t1 = 0, t2 = 0;
        for (int i = low; i <= high; i++) {
            int temp = t1;
            int current = nums[i];
            t1 = Math.max(current + t2, t1);
            t2 = temp;
        }
        return t1;
    }

    @Test
    public void t1() {
        check("[2,3,2]", 3);
    }

    @Test
    public void t2() {
        check("[1,2,3,1]", 4);
    }

    @Test
    public void t3() {
        check("[1,2,3]", 3);
    }

    private void check(String input, int expected) {
        Assertions.assertEquals(expected, rob(Utils.stringToIntArray(input)), input);
    }

}

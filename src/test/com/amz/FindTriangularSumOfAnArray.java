package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/find-triangular-sum-of-an-array/"
 */
public class FindTriangularSumOfAnArray {

    public int triangularSum(int[] nums) {
        if (nums.length == 1) return nums[0];
        int[] next = new int[nums.length - 1];
        for (int i = 0; i < nums.length - 1; i++) {
            next[i] = (nums[i] + nums[i + 1]) % 10;
        }
        return triangularSum(next);
    }

    @Test
    public void check1() {
        Assertions.assertEquals(8, triangularSum(Utils.stringToIntArray("[1,2,3,4,5]")));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(5, triangularSum(Utils.stringToIntArray("[5]")));
    }
}

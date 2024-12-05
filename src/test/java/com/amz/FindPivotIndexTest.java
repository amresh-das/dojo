package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @see "https://leetcode.com/problems/find-pivot-index/description/?envType=study-plan-v2&envId=leetcode-75"
 */
public class FindPivotIndexTest {

    public int pivotIndex(int[] nums) {
        int total = Arrays.stream(nums).sum();
        int leftSum = 0;
        int rightSum = total;
        for (int i = 0; i < nums.length; i++) {
            final int cur = nums[i];
            if (leftSum == rightSum - cur) return i;
            leftSum += cur;
            rightSum -= cur;
        }
        return -1;
    }

    @Test
    void check1() {
        Assertions.assertEquals(3, pivotIndex(new int[] {1,7,3,6,5,6}));
    }

    @Test
    void check2() {
        Assertions.assertEquals(-1, pivotIndex(new int[] {1,2,3}));
    }

    @Test
    void check3() {
        Assertions.assertEquals(0, pivotIndex(new int[] {2,1,-1}));
    }
}

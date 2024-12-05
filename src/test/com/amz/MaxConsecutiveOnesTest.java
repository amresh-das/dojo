package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/max-consecutive-ones-iii/description/?envType=study-plan-v2&envId=leetcode-75"
 */
public class MaxConsecutiveOnesTest {

    public int longestOnes(int[] nums, int k) {
        int result = 0;
        int start = 0, end = 0;
        int z = 0;
        while (end < nums.length) {
            if (z > k) {
                while (start < nums.length && nums[start] == 1) start++;
                if (start < nums.length && nums[start] == 0) {
                    start++;
                    z--;
                }
            }
            if (nums[end++] == 0) z++;
            if (z <= k) {
                result = Math.max(result, end - start);
            }
        }
        return result;
    }

    @Test
    void check1() {
        Assertions.assertEquals(6, longestOnes(new int[] {1,1,1,0,0,0,1,1,1,1,0}, 2));
    }

    @Test
    void check2() {
        Assertions.assertEquals(10, longestOnes(new int[] {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1}, 3));
    }

    @Test
    void check3() {
        Assertions.assertEquals(3, longestOnes(new int[] {1,1,1}, 2));
    }
}

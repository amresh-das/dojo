package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/find-peak-element/?envType=study-plan-v2&envId=leetcode-75"
 */
public class FindPeakElementTest {

    public int findPeakElement(int[] nums) {
        if (nums.length == 1) return 0;
        int l = 0, r = nums.length - 1;
        while (true) {
            int m = l + ((r - l) / 2);
            if (nums[m] > nums[m + 1]) {
                r = m;
            } else {
                l = m + 1;
            }
            if (l == r) return l;
        }
    }

    @Test
    void t1() {
        Assertions.assertEquals(2, findPeakElement(new int[] {1,2,3,1}));
    }

    @Test
    void t2() {
        Assertions.assertEquals(5, findPeakElement(new int[] {1,2,1,3,5,6,4}));
    }
}

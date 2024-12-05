package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/"
 */
public class FindMinimumInRotatedSortedArrayII {

    public int findMin(int... nums) {
        int lo = 0, hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] < nums[hi]) {
                hi = mid;
            } else if (nums[mid] > nums[hi]) {
                lo = mid + 1;
            } else {
                hi--;
            }
        }
        return nums[lo];
    }

    @Test
    public void check1() {
        Assertions.assertEquals(1, findMin(1,3,5));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(0, findMin(2,2,2,0,1));
    }

}

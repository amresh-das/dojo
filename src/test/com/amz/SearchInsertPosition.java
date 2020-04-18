package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/search-insert-position/"
 */
public class SearchInsertPosition {
    private Solution solution = new Solution();

    @Test
    void t1() {
        verify(5, 2, 1,3,5,6);
    }

    @Test
    void t2() {
        verify(2, 1, 1,3,5,6);
    }

    @Test
    void t3() {
        verify(7, 4, 1,3,5,6);
    }

    @Test
    void t4() {
        verify(0, 0, 1,3,5,6);
    }

    @Test
    void t5() {
        verify(1, 0);
    }

    @Test
    void t6() {
        verify(2, 1, 1, 3);
    }

    private void verify(final int target, final int result, final int... input) {
        Assertions.assertEquals(result, solution.searchInsert(input, target));
    }

    class Solution {
        public int searchInsert(int[] nums, int target) {
            if (nums.length == 0) return 0;
            if (nums[0] > target) return 0;
            if (nums[nums.length - 1] < target) return nums.length;
            int left = 0, right = nums.length - 1, pivot;
            while (left <= right) {
                pivot = left + (right - left) / 2;
                if (nums[pivot] == target) return pivot;
                if (target < nums[pivot]) {
                    right = pivot - 1;
                } else {
                    left = pivot + 1;
                }
            }
            return left;
        }
    }

}

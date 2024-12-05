package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/"
 */
public class FirstAndLastIndexInSortedArray {
    private Solution solution = new Solution();

    @Test
    public void t1() {
        verify(8, 3, 4, 5,7,7,8,8,10);
    }

    @Test
    public void t2() {
        verify(6, -1, -1, 5,7,7,8,8,10);
    }

    @Test
    public void t3() {
        verify(6, -1, -1);
    }

    private void verify(final int target, final int first, final int last, final int... input) {
        final int[] actual = solution.searchRange(input, target);
        Assertions.assertArrayEquals(actual, new int[] {first, last});
    }

    class Solution {
        public int[] searchRange(int[] nums, int target) {
            int index = (nums.length == 0) ? -1 :binarySearch(nums, target, 0, nums.length - 1);
            if (index == -1) return new int[] { -1, -1 };
            int minIndex = index, maxIndex = index;
            while (minIndex > 0 && nums[minIndex - 1] == target) minIndex--;
            while (maxIndex < nums.length - 1 && nums[maxIndex + 1] == target) maxIndex++;
            return new int[] {minIndex, maxIndex};
        }

        private int binarySearch(final int[] nums, final int target, final int start, final int end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (start >= end) {
                return -1;
            } else {
                if (nums[mid] > target) {
                    return binarySearch(nums, target, start, mid - 1);
                } else {
                    return binarySearch(nums, target, mid + 1, end);
                }
            }
        }
    }
}

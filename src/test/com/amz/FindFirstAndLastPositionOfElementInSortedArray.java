package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @see "https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/"
 */
public class FindFirstAndLastPositionOfElementInSortedArray {

    public int[] searchRange(int[] nums, int target) {
        int[] range = new int[2];
        int mid = binarySearch(nums, target, 0, nums.length - 1);
        if (mid == -1) {
            Arrays.fill(range, -1);
        } else {
            range[0] = mid;
            range[1] = mid;
            int index = mid;
            while (true) {
                index = binarySearch(nums, target, 0, index - 1);
                if (index == -1) break;
                range[0] = index;
            }
            index = mid;
            while (true) {
                index = binarySearch(nums, target, index + 1, nums.length - 1);
                if (index == -1) break;
                range[1] = index;
            }
        }
        return range;
    }

    private int binarySearch(int[] nums, int target, int lo, int hi) {
        while (lo <= hi) {
            int mid = (hi + lo) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] > target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return -1;
    }

    @Test
    public void check1() {
        verify("[5,7,7,8,8,10]", 8, "[3,4]");
    }

    @Test
    public void check2() {
        verify("[5,7,7,8,8,10]", 6, "[-1,-1]");
    }

    @Test
    public void check3() {
        verify("[]", 0, "[-1,-1]");
    }

    private void verify(String input, int target, String expected) {
        Assertions.assertEquals(expected, Utils.toString(searchRange(Utils.stringToIntArray(input), target)));
    }

}

package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/binary-search"
 */
public class BinarySearch {

    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        do {
            int mid = low + ((high - low) / 2);
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        } while (low <= high);
        return -1;
    }

    @Test
    public void t1() {
        verify(9, "[-1,0,3,5,9,12]", 4);
    }

    @Test
    public void t2() {
        verify(2, "[-1,0,3,5,9,12]", -1);
    }

    @Test
    public void t3() {
        verify(5, "[5]", 0);
    }

    @Test
    public void t4() {
        verify(5, "[2,5]", 1);
    }

    private void verify(int target, String input, int expected) {
        Assertions.assertEquals(expected, search(Utils.stringToIntArray(input), target));
    }
}

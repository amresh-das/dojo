package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RotatedSortedArraySearch {

    @Test
    void t1() {
        int[] input = new int[] {4,5,6,7,0,1,2};
        int target = 0;
        Assertions.assertEquals(4, search(input, target));
    }

    @Test
    void t2() {
        int[] input = new int[] {4,5,6,7,0,1,2};
        int target = 3;
        Assertions.assertEquals(-1, search(input, target));
    }

    @Test
    void t3() {
        int[] input = new int[] {3,1};
        int target = 3;
        Assertions.assertEquals(0, search(input, target));
    }

    public int search(int[] nums, int target) {
        int offset = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                offset = i;
                break;
            }
        }
        return binarySearch(nums, offset, 0, nums.length, target);
    }

    private int binarySearch(final int[] nums, final int offset, final int start, final int end, final int target) {
        if (end <= start) return -1;
        int index = (start + end) / 2;
        int offsetIndex = (index + offset) % nums.length;
        int pivotElement = nums[offsetIndex];
        if (target == pivotElement) return (index + offset) % nums.length;
        if (target < pivotElement) {
            return binarySearch(nums, offset, start, index, target);
        } else {
            return binarySearch(nums, offset, index + 1, end, target);
        }
    }
}

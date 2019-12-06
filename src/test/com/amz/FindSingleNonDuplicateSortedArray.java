package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FindSingleNonDuplicateSortedArray {

    @Test
    void t1() {
        int[] nums = new int[] {1,1,2,3,3,4,4,8,8};
        Assertions.assertEquals(2, singleNonDuplicate(nums));
    }

    @Test
    void t2() {
        int[] nums = new int[] {3,3,7,7,10,11,11};
        Assertions.assertEquals(10, singleNonDuplicate(nums));
    }

    @Test
    void t3() {
        int[] nums = new int[] {1,1,2,3,3,4,4,8,8};
        Assertions.assertEquals(2, singleNonDuplicate(nums));
    }

    @Test
    void t4() {
        int[] nums = new int[] {1};
        Assertions.assertEquals(1, singleNonDuplicate(nums));
    }

    @Test
    void t5() {
        int[] nums = new int[] {1,1,2};
        Assertions.assertEquals(2, singleNonDuplicate(nums));
    }

    public int singleNonDuplicate(int[] nums) {
        return nums.length == 1 ? nums[0] : binarySearch(nums, 0, nums.length);
    }

    private int binarySearch(final int[] nums, final int start, final int end) {
        if (start >= end) return -1;
        int mid = (start + end) / 2;
        int curr = nums[mid];
        int next = mid < (nums.length - 1) ? nums[mid + 1] : nums[mid - 1];
        int prev = mid > 0 ? nums[mid - 1] : nums[mid + 1];
        if (curr != prev && curr != next) {
            return curr;
        }
        if ((mid % 2 == 0 && nums[mid] == nums[mid + 1]) || (mid % 2 != 0 && nums[mid - 1] == nums[mid])) {
            return binarySearch(nums, mid + 1, end);
        } else {
            return binarySearch(nums, start, mid);
        }
    }
}

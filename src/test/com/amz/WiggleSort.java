package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/wiggle-sort/"
 */
public class WiggleSort {
    public void wiggleSort(int[] nums) {
        boolean direction = true;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] != nums[i + 1]) {
                if (direction && nums[i] > nums[i + 1]) {
                    swap(nums, i, i + 1);
                } else if (!direction && nums[i] < nums[i + 1]) {
                    swap(nums, i, i + 1);
                }
            }
            direction = !direction;
        }
    }

    private void swap(final int[] nums, final int i, final int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    @Test
    public void check1() {
        int[] input = Utils.stringToIntArray("[3,5,2,1,6,4]");
        wiggleSort(input);
        Assertions.assertEquals("[3,5,1,6,2,4]", Utils.toString(input));
    }

    @Test
    public void check2() {
        int[] input = Utils.stringToIntArray("[6,6,5,6,3,8]");
        wiggleSort(input);
        Assertions.assertEquals("[6,6,5,6,3,8]", Utils.toString(input));
    }

}

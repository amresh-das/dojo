package com.amz;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/remove-element/"
 */
public class RemoveArrayElement {

    @Test
    void t1() {
        int[] nums = new int[] {3,2,2,3};
        Assertions.assertThat(removeElement(nums, 3)).isEqualTo(2);
        Assertions.assertThat(nums[0]).isEqualTo(2);
        Assertions.assertThat(nums[1]).isEqualTo(2);
    }

    @Test
    void t2() {
        int[] nums = new int[] {0,1,2,2,3,0,4,2};
        Assertions.assertThat(removeElement(nums, 2)).isEqualTo(5);
        Assertions.assertThat(nums[0]).isEqualTo(0);
        Assertions.assertThat(nums[1]).isEqualTo(1);
        Assertions.assertThat(nums[2]).isEqualTo(3);
        Assertions.assertThat(nums[3]).isEqualTo(0);
        Assertions.assertThat(nums[4]).isEqualTo(4);
    }

    public int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] == val) {
                continue;
            }
            nums[i++] = nums[j];
        }
        return i;
    }
}

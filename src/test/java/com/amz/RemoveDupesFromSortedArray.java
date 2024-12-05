package com.amz;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/remove-duplicates-from-sorted-array/"
 */
public class RemoveDupesFromSortedArray {

    @Test
    void t1() {
        int[] input = new int[] {1,1,2};
        Assertions.assertThat(removeDuplicates(input)).isEqualTo(2);
        Assertions.assertThat(input[0]).isEqualTo(1);
        Assertions.assertThat(input[1]).isEqualTo(2);
    }

    @Test
    void t2() {
        int[] input = new int[] {0,0,1,1,1,2,2,3,3,4};
        Assertions.assertThat(removeDuplicates(input)).isEqualTo(5);
        for (int i = 0; i < 5; i++) {
            Assertions.assertThat(input[i]).isEqualTo(i);
        }
    }

    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int j = 1; j < nums.length;) {
            if (nums[i] == nums[j]) {
                j++;
                if (j >= nums.length) break;
            }
            if (nums[i] < nums[j]) {
                nums[++i] = nums[j];
            }
        }
        return i + 1;
    }
}

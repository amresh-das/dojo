package com.amz;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/next-permutation/"
 */
public class NextPermutation {
    private Solution solution = new Solution();

    @Test
    void t1() {
        int[] in = new int[] {1,2,3};
        solution.nextPermutation(in);

        Assertions.assertThat(in).containsExactly(1,3,2);
    }

    @Test
    void t2() {
        int[] in = new int[] {3,2,1};
        solution.nextPermutation(in);

        Assertions.assertThat(in).containsExactly(1,2,3);
    }

    @Test
    void t3() {
        int[] in = new int[] {1,1,5};
        solution.nextPermutation(in);

        Assertions.assertThat(in).containsExactly(1,5,1);
    }

    @Test
    void t4() {
        int[] in = new int[] {1,3,2};
        solution.nextPermutation(in);

        Assertions.assertThat(in).containsExactly(2,1,3);
    }

    @Test
    void t5() {
        int[] in = new int[] {2,3,1};
        solution.nextPermutation(in);

        Assertions.assertThat(in).containsExactly(3,1,2);
    }

    private class Solution {
        public void nextPermutation(int[] nums) {
            int pivot = -1;
            for (int i = nums.length - 1; i > 0; i--) {
                if (nums[i - 1] < nums[i]) {
                    pivot = i - 1;
                    for (int j = nums.length - 1; j >= i; j--) {
                        if (nums[i - 1] < nums[j]) {
                            swap(nums, i - 1, j);
                            break;
                        }
                    }
                    break;
                }
            }
            reverse(nums, pivot + 1);
        }

        private void reverse(final int[] nums, final int fromIndex) {
            for (int i = fromIndex, j = nums.length - 1; i < nums.length; i++, j--) {
                if (j < i) break;
                swap(nums, i, j);
            }
        }

        private void swap(final int[] nums, final int i, final int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}

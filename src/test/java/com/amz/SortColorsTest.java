package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://leetcode.com/problems/sort-colors/description/?envType=daily-question&envId=2024-11-08
 */
public class SortColorsTest {
    public void sortColors(int[] nums) {
        int[] counts = new int[3];
        for (int n : nums) {
            counts[n]++;
        }
        for (int i = 0, j = 0, cntIdx = 0; i < nums.length; i++, j++) {
            while (j == counts[cntIdx]) {
                j = 0;
                cntIdx++;
            }
            nums[i] = cntIdx;
        }
    }

    @Test
    void t1() {
        int[] input = new int[] {2,0,2,1,1,0};
        sortColors(input);
        Assertions.assertArrayEquals(new int[] {0,0,1,1,2,2}, input);
    }

    @Test
    void t2() {
        int[] input = new int[] {2,0,1};
        sortColors(input);
        Assertions.assertArrayEquals(new int[] {0,1,2}, input);
    }

    @Test
    void t3() {
        int[] input = new int[] {2};
        sortColors(input);
        Assertions.assertArrayEquals(new int[] {2}, input);
    }
}

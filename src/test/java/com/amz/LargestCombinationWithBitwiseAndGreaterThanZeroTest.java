package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/largest-combination-with-bitwise-and-greater-than-zero/?envType=daily-question&envId=2024-11-08"
 */
public class LargestCombinationWithBitwiseAndGreaterThanZeroTest {

    public int largestCombination(int[] candidates) {
        if (candidates.length <= 1) return candidates.length;
        int maxCount = 0;
        for (int i = 0; i < 32; i++) {
            int count = 0;
            for (int c : candidates) {
                if ((c & (1 << i)) > 0) count++;
            }
            if (count > maxCount) maxCount = count;
        }
        return maxCount;
    }

    @Test
    void check1() {
        System.out.println(Integer.toBinaryString(24));
        System.out.println(Integer.toBinaryString(69));
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
        System.out.println(Integer.toBinaryString(999999999));
        System.out.println(Integer.MAX_VALUE);
        Assertions.assertEquals(4, largestCombination(new int[] {16,17,71,62,12,24,14}));
    }

    @Test
    void check2() {
        Assertions.assertEquals(2, largestCombination(new int[] {8,8}));
    }
}

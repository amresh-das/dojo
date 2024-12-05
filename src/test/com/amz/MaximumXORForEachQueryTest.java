package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/maximum-xor-for-each-query/?envType=daily-question&envId=2024-11-10"
 */
public class MaximumXORForEachQueryTest {

    public int[] getMaximumXor(int[] nums, int maximumBit) {
        int[] result = new int[nums.length];
        for (int i = 0, j = result.length - 1, x = 0; i < nums.length; i++, j--) {
            result[j] = leastSignificantBits(~(x ^= nums[i]), maximumBit);
        }
        return result;
    }

    private int leastSignificantBits(int n, int maximumBit) {
        return n & ((1 << maximumBit) - 1);
    }

    @Test
    void t1() {
        Assertions.assertArrayEquals(new int[] {0,3,2,3}, getMaximumXor(new int[] {0,1,1,3}, 2));
    }

    @Test
    void t2() {
        Assertions.assertArrayEquals(new int[] {5,2,6,5}, getMaximumXor(new int[] {2,3,4,7}, 3));
    }
}

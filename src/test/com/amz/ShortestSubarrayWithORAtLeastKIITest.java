package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

    /**
 * @see "https://leetcode.com/problems/shortest-subarray-with-or-at-least-k-ii/description/?envType=daily-question&envId=2024-11-10
 **/
public class ShortestSubarrayWithORAtLeastKIITest {

    public int minimumSubarrayLength(int[] nums, int k) {
        int[] bitMap = new int[32];
        int minSize = -1;
        int start = 0, current = 0;
        int val = 0;
        while (start < nums.length) {
            if (current < nums.length) {
                val = addToMap(bitMap, nums[current++]);
            } else {
                val = removeFromMap(bitMap, nums[start++]);
            }
            while (k <= val) {
                int size = current - start;
                if (size == 1) return 1;
                if (minSize > size || minSize == -1) {
                    minSize = size;
                }
                val = removeFromMap(bitMap, nums[start++]);
            }
        }
        return minSize;
    }

    private int addToMap(int[] bitMap, int num) {
        int val = 0;
        final char[] bits = Integer.toBinaryString(num).toCharArray();
        for (int i = bits.length - 1, bitIndex = 0; bitIndex < 32; i--, bitIndex++) {
            if (i >= 0 && bits[i] == '1') bitMap[bitIndex]++;
            if (bitMap[bitIndex] > 0) val |= 1 << bitIndex;
        }
        return val;
    }

    private int removeFromMap(int[] bitMap, int num) {
        int val = 0;
        final char[] bits = Integer.toBinaryString(num).toCharArray();
        for (int i = bits.length - 1, bitIndex = 0; bitIndex < 32; i--, bitIndex++) {
            if (i >= 0 && bits[i] == '1') bitMap[bitIndex]--;
            if (bitMap[bitIndex] > 0) val |= 1 << bitIndex;
        }
        return val;
    }

    @Test
    void t1() {
        Assertions.assertEquals(1, minimumSubarrayLength(new int[] {1,2,3}, 2));
    }

    @Test
    void t2() {
        Assertions.assertEquals(3, minimumSubarrayLength(new int[] {2,1,8}, 10));
    }

    @Test
    void t3() {
        Assertions.assertEquals(1, minimumSubarrayLength(new int[] {1,2}, 0));
    }

    @Test
    void t4() {
        Assertions.assertEquals(3, minimumSubarrayLength(new int[] {1,2,32,21}, 55));
    }
}

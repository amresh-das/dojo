package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @see "https://leetcode.com/problems/find-if-array-can-be-sorted/?envType=daily-question&envId=2024-11-08"
 */
public class FindIfArrayCanBeSortedWithBitCountTest {

    public boolean canSortArray(int[] nums) {
        int prevMax = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            int min = n, max = n;
            int bitCount = Integer.bitCount(n);
            while (i < nums.length - 1 && Integer.bitCount(nums[i + 1]) == bitCount) {
                i++;
                if (min > nums[i]) min = nums[i];
                if (max < nums[i]) max = nums[i];
            }
            if (prevMax > min) return false;
            prevMax = max;
        }
        return true;
    }

    public boolean canSortArrayEvenNonContiguous(int[] nums) {
        int[] bitCounts = new int[nums.length];
        Map<Integer, PriorityQueue<Integer>> segments = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            segments.compute(bitCounts[i] = Integer.bitCount(n), (k, v) -> {
               PriorityQueue<Integer> q = v == null ? new PriorityQueue<>() : v;
               q.offer(n);
               return q;
            });
        }
        int prev = Integer.MIN_VALUE;
        for (int i = 0; i < bitCounts.length; i++) {
            final int next = segments.get(bitCounts[i]).poll();
            if (next < prev) return false;
            prev = next;
        }
        return true;
    }

    @Test
    void check1() {
        Assertions.assertTrue(canSortArray(new int[] {8,4,2,30,15}));
    }

    @Test
    void check2() {
        Assertions.assertTrue(canSortArray(new int[] {1,2,3,4,5}));
    }

    @Test
    void check3() {
        Assertions.assertFalse(canSortArray(new int[] {3,16,8,4,2}));
    }

    @Test
    void check4() {
        Assertions.assertFalse(canSortArray(new int[] {20,16}));
    }

    @Test
    void check5() {
        Assertions.assertFalse(canSortArray(new int[] {75,34,30}));
    }
}

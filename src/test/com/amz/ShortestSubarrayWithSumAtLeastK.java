package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @see "https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/"
 */
public class ShortestSubarrayWithSumAtLeastK {

    public int shortestSubarray(int[] nums, int k) {
        long[] prefixSum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        final Deque<Integer> mq = new LinkedList<>();
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < prefixSum.length; i++) {
            while (!mq.isEmpty() && prefixSum[i] <= prefixSum[mq.getLast()]) {
                mq.removeLast();
            }
            while (!mq.isEmpty() && prefixSum[i] >= prefixSum[mq.getFirst()] + k) {
                minLen = Math.min(minLen, i - mq.removeFirst());
            }
            mq.addLast(i);
        }
        return minLen < Integer.MAX_VALUE ? minLen : -1;
    }

    public int shortestSubarray2(int[] nums, int k) {
        Map<Integer, Integer> maxSumByStart = new HashMap<>();
        for (int length = 0; length < nums.length; length++) {
            final int len = length;
            for (int start = 0; start < nums.length - length; start++) {
                final int index = start;
                int sum = maxSumByStart.compute(start, (key, v) -> (v == null ? 0 : v) + nums[index + len]);
                if (sum >= k) return length + 1;
            }
        }
        return -1;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(1, shortestSubarray(Utils.stringToIntArray("[1]"), 1));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(-1, shortestSubarray(Utils.stringToIntArray("[1,2]"), 4));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(3, shortestSubarray(Utils.stringToIntArray("[2,-1,2]"), 3));
    }

    @Test
    public void check5() {
        Assertions.assertEquals(2, shortestSubarray(Utils.stringToIntArray("[17,85,93,-45,-21]"), 150));
    }

}

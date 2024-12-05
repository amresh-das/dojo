package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @see "https://leetcode.com/problems/kth-largest-element-in-an-array/description/?envType=study-plan-v2&envId=leetcode-75"
 */
public class KthLargestElementInAnArrayTest {

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>(Comparator.reverseOrder());
        for (var n : nums) {
            q.offer(n);
        }
        for (int i = 1; i <= k; i++) {
            if (i == k) return q.poll();
            q.poll();
        }
        return -1;
    }

    @Test
    void t1() {
        Assertions.assertEquals(5, findKthLargest(new int[] {3,2,1,5,6,4}, 2));
    }

    @Test
    void t2() {
        Assertions.assertEquals(4, findKthLargest(new int[] {3,2,3,1,2,4,5,5,6}, 4));
    }
}

package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

public class KthLargestInUnsortedArray {

    @Test
    void t1() {
        check(4, 2, 3,2,1,5,6,4);
    }

    @Test
    void t2() {
        check(4, 4, 3,2,3,1,2,4,5,5,6);
    }

    private void check(final int expected, final int k, final int... nums) {
        Assertions.assertEquals(expected, findKthLargest(nums, k));
    }

    public int findKthLargest(int[] nums, int k) {
        final PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int n : nums) {
            heap.offer(n);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        return heap.poll();
    }

}

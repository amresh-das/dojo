package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.IntStream;

/**
 * @see "https://leetcode.com/problems/sliding-window-maximum"
 */
public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k == 0) return new int[0];
        if (k == 1) return nums;

        final Deque<Integer> deque = new ArrayDeque<>(k);
        int[] output = new int[nums.length - k + 1];

        output[0] = Arrays.stream(nums, 0, k).max().orElse(-1);
        IntStream.range(0, k).boxed().forEach(i -> {
            clearDeque(deque, nums, i, k);
            deque.addLast(i);
        });

        for (int i = k; i < nums.length; i++) {
            clearDeque(deque, nums, i, k);
            deque.addLast(i);
            output[i - k + 1] = nums[deque.getFirst()];
        }
        return output;
    }

    private void clearDeque(final Deque<Integer> deque, final int[] nums, final int i, final int k) {
        if (!deque.isEmpty() && deque.peekFirst() <= i - k) deque.removeFirst();
        while(!deque.isEmpty() && i >= 0 && i < nums.length && nums[deque.peekLast()] <= nums[i]) deque.removeLast();
    }

    @Test
    public void check1() {
        Assertions.assertEquals("[3,3,5,5,6,7]", Utils.toString(maxSlidingWindow(Utils.stringToIntArray("[1,3,-1,-3,5,3,6,7]"), 3)));
    }

    @Test
    public void check2() {
        Assertions.assertEquals("[1]", Utils.toString(maxSlidingWindow(Utils.stringToIntArray("[1]"), 1)));
    }

    @Test
    public void check3() {
        Assertions.assertEquals("[3,3,2,5]", Utils.toString(maxSlidingWindow(Utils.stringToIntArray("[1,3,1,2,0,5]"), 3)));
    }

}

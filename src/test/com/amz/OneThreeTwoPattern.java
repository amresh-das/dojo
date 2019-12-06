package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

public class OneThreeTwoPattern {

    @Test
    void t1() {
        check(false, 1, 2, 3, 4);
    }

    @Test
    void t2() {
        check(true, 3, 1, 4, 2);
    }

    private void check(final boolean expected, final int... nums) {
        Assertions.assertEquals(expected, find132pattern(nums));
    }

    public boolean find132pattern(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int max = Integer.MIN_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() < nums[i]) {
                max = stack.pop();
            }
            if (nums[i] > max) stack.push(nums[i]);
            if (nums[i] < max) return true;
        }
        return false;
    }
}

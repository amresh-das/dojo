package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @see "https://leetcode.com/problems/jump-game-ii/"
 */
public class JumpGameII {

    public int jump(int[] nums) {
        final int[] dp = new int[nums.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        countJumps(0, nums, dp, 0);
        return dp[nums.length - 1];
    }

    private void countJumps(final int index, final int[] nums, final int[] dp, final int jumps) {
        if (jumps < dp[index]) {
            dp[index] = jumps;
            if (index == nums.length - 1) return;
            for (int i = nums[index]; i > 0; i--) {
                if (index + i < nums.length) {
                    countJumps(index + i, nums, dp, jumps + 1);
                }
            }
        }
    }

    @Test
    void t1() {
        Assertions.assertEquals(2, doJump(2,3,1,1,4));
    }

    @Test
    void t2() {
        Assertions.assertEquals(2, doJump(2,3,0,1,4));
    }

    @Test
    void t3() {
        Assertions.assertEquals(1, doJump(3,2,1));
    }

    @Test
    void t4() {
        Assertions.assertEquals(2, doJump(1,2,3));
    }

    @Test
    void t5() {
        Assertions.assertEquals(2, doJump(4,1,1,3,1,1,1));
    }

    @Test
    void t6() {
        Assertions.assertEquals(5, doJump(5,6,4,4,6,9,4,4,7,4,4,8,2,6,8,1,5,9,6,5,2,7,9,7,9,6,9,4,1,6,8,8,4,4,2,0,3,8,5));
    }

    private int doJump(final int... arr) {
        return jump(arr);
    }
}

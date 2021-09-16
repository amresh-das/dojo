package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @see "https://leetcode.com/problems/jump-game/"
 */
public class JumpGame {

    public boolean canJump(int[] nums) {
        final int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        return nums.length == 0 || canJump(0, nums, dp);
    }

    private boolean canJump(final int index, final int[] nums, final int[] dp) {
        if (index == nums.length - 1) {
            dp[index] = index;
            return true;
        }
        if (dp[index] != -1) return dp[index] > 0;
        for (int i = nums[index]; i > 0; i--) {
            if ( index + i < nums.length && canJump(index + i, nums, dp)) {
                dp[index] = index + i;
                return true;
            }
        }
        dp[index] = 0;
        return false;
    }

    @Test
    void t1() {
        Assertions.assertTrue(canJump(Utils.stringToIntArray("[2,3,1,1,4]")));
    }

    @Test
    void t2() {
        Assertions.assertFalse(canJump(Utils.stringToIntArray("[3,2,1,0,4]")));
    }

    @Test
    void t3() {
        Assertions.assertTrue(canJump(Utils.stringToIntArray("[2,0]")));
    }
}

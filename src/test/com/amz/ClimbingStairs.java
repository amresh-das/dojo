package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/climbing-stairs/"
 */
public class ClimbingStairs {

    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        return dp(n, dp);
    }

    private int dp(final int pos, final int[] dp) {
        if (dp[pos] != 0) return dp[pos];
        if (pos == 1) return 1;
        if (pos == 2) return 2;
        if (pos < 1) return 0;
        dp[pos] = dp(pos - 1, dp) + dp(pos - 2, dp);
        return dp[pos];
    }

    @Test
    void t1() {
        Assertions.assertEquals(2, climbStairs(2));
    }

    @Test
    void t2() {
        Assertions.assertEquals(3, climbStairs(3));
    }
}

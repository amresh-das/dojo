package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/min-cost-climbing-stairs/"
 */
public class MinCostClimbingStairs {

    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length + 1];
        return dp(cost.length, cost, dp);
    }

    private int dp(final int pos, int[] cost, final int[] dp) {
        if (dp[pos] != 0) return dp[pos];
        if (pos <= 1) return 0;
        dp[pos] = Math.min(dp(pos - 1, cost, dp) + cost[pos - 1], dp(pos - 2, cost, dp) + cost[pos - 2]);
        return dp[pos];
    }

    @Test
    public void t1() {
        check("[10,15,20]", 15);
    }

    @Test
    public void t2() {
        check("[1,100,1,1,1,100,1,1,100,1]", 6);
    }

    private void check(String input, int expected) {
        Assertions.assertEquals(expected, minCostClimbingStairs(Utils.stringToIntArray(input)));
    }

}

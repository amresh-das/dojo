package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HouseRobber {

    public int rob(int[] nums) {
        int[] dp = new int[nums.length + 1];
        dp[0] = 0;
        dp[1] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i + 1] = Math.max(dp[i], dp[i - 1] + nums[i]);
        }
        return dp[nums.length];
    }


    @Test
    public void t1() {
        check("[1,2,3,1]", 4);
    }

    @Test
    public void t2() {
        check("[2,7,9,3,1]", 12);
    }

    @Test
    public void t3() {
        check("[2,1,1,2]", 4);
    }

    private void check(String input, int expected) {
        Assertions.assertEquals(expected, rob(Utils.stringToIntArray(input)));
    }

}

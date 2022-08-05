package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/combination-sum-iv/"
 */
public class CombinationSumIV {

    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 0; i <= target; i++) {
            if (dp[i] > 0) {
                for (int n : nums) {
                    if (i + n <= target) dp[i + n] += dp[i];
                }
            }
        }
        return dp[target];
    }

    public int combinationSum4TLE(int[] nums, int target) {
        return countCombinations(nums, 0, target);
    }

    private int countCombinations(int[] nums, int sum, int target) {
        if (sum == target) return 1;
        if (sum > target) return 0;
        int count = 0;
        for (int n : nums) {
            count += countCombinations(nums, sum + n, target);
        }
        return count;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(7, combinationSum4(Utils.stringToIntArray("[1,2,3]"), 4));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(0, combinationSum4(Utils.stringToIntArray("[9]"), 3));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(181997601, combinationSum4(Utils.stringToIntArray("[1,2,3]"), 32));
    }
}

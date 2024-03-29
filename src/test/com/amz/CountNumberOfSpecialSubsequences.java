package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @see "https://leetcode.com/problems/count-number-of-special-subsequences/"
 */
public class CountNumberOfSpecialSubsequences {
    private static final int MOD = 1000000007;

    public int countSpecialSubsequences(int[] nums) {
        long count0 = 0, count1 = 0, count2 = 0;
        for (int n : nums) {
            if (n == 0) {
                count0 = (2 * count0 + 1) % MOD;
            } else if (n == 1) {
                count1 = (2 * count1 + count0) % MOD;
            } else {
                count2 = (2 * count2 + count1) % MOD;
            }
        }
        return (int) count2;
    }

    public int countSpecialSubsequencesTLE(int[] nums) {
        Map<Integer, List<Integer>> indices = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            final int index = i;
            indices.compute(nums[i], (k, v) -> {
               List<Integer> list = v == null ? new ArrayList<>() : v;
               list.add(index);
               return list;
            });
        }
        int[][] memo = new int[3][nums.length];
        for (int i = 0; i < 3; i++) {
            Arrays.fill(memo[i], -1);
        }
        return (countCombinations(indices, 0, -1, memo) % MOD);
    }

    private int countCombinations(Map<Integer, List<Integer>> indices, int num, int prevIndex, final int[][] memo) {
        if (num == 3) {
            return 1;
        }
        if (prevIndex >= 0 && memo[num][prevIndex] != -1) return memo[num][prevIndex];
        int count = 0;
        if (!indices.containsKey(num)) return 0;
        for (int index : indices.get(num)) {
            if (index > prevIndex) {
                count = (count + countCombinations(indices, num + 1, index, memo))  % MOD;
                count = (count + countCombinations(indices, num, index, memo)) % MOD;
            }
        }
        if (prevIndex >= 0) {
            memo[num][prevIndex] = count;
        }
        return count;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(3, countSpecialSubsequences(Utils.stringToIntArray("[0,1,2,2]")));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(0, countSpecialSubsequences(Utils.stringToIntArray("[2,2,0,0]")));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(7, countSpecialSubsequences(Utils.stringToIntArray("[0,1,2,0,1,2]")));
    }
}

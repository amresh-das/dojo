package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @see "https://leetcode.com/problems/longest-increasing-subsequence/"
 */
public class LongestIncreasingSubsequence {

    public int lengthOfLIS(int[] nums) {
        final List<Integer> sub = new ArrayList<>();
        sub.add(nums[0]);

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > sub.get(sub.size() - 1)) {
                sub.add(nums[i]);
            } else {
                sub.set(binarySearch(sub, nums[i]), nums[i]);
            }
        }
        return sub.size();
    }

    private int binarySearch(List<Integer> nums, int num) {
        int lo = 0, hi = nums.size() - 1;
        int mid;
        while (lo < hi) {
            mid = (lo + hi) / 2;
            if (nums.get(mid) == num) return mid;
            if (nums.get(mid) < num) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(4, lengthOfLIS(Utils.stringToIntArray("[10,9,2,5,3,7,101,18]")));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(4, lengthOfLIS(Utils.stringToIntArray("[0,1,0,3,2,3]")));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(1, lengthOfLIS(Utils.stringToIntArray("[7,7,7,7,7,7,7]")));
    }
}

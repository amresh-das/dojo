package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @see "https://leetcode.com/problems/longest-consecutive-sequence/"
 */
public class LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {
        Set<Integer> numbers = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        int longest = 0;
        for (final int j : nums) {
            int num = j;
            if (!numbers.contains(num - 1)) {
                int count = 0;
                while (numbers.contains(num++)) {
                    count++;
                }
                longest = Math.max(longest, count);
            }
        }
        return longest;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(4, longestConsecutive(Utils.stringToIntArray("[100,4,200,1,3,2]")));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(9, longestConsecutive(Utils.stringToIntArray("[0,3,7,2,5,8,4,6,0,1]")));
    }


}

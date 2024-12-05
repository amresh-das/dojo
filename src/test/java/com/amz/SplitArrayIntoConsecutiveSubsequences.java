package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.IntStream;

/**
 * @see "https://leetcode.com/problems/split-array-into-consecutive-subsequences/"
 */
public class SplitArrayIntoConsecutiveSubsequences {
    private final int subListSize = 3;


    public boolean isPossible(int... nums) {
        final Map<Integer, Integer> counts = new HashMap<>();
        final Map<Integer, Integer> subLists = new HashMap<>();
        for (int num : nums) {
            counts.compute(num, (k, v) -> v == null ? 1 : v + 1);
        }
        for (int num : nums) {
            if (counts.get(num) == 0) continue;
            if (subLists.getOrDefault(num - 1, 0) > 0) {
                subLists.compute(num - 1, (k, v) -> v == null ? 0 : v - 1);
                subLists.compute(num, (k, v) -> v == null ? 1 : v + 1);
            } else if (IntStream.range(1, subListSize).map(i -> num + i).allMatch(n -> counts.getOrDefault(n, 0) > 0)) {
                subLists.compute(num + subListSize - 1, (k, v) -> v == null ? 1 : v + 1);
                IntStream.range(1, subListSize).map(i -> num + i).forEach(n -> counts.compute(n, (k, v) -> v == null ? 0 : v - 1));
            } else {
                return false;
            }
            counts.compute(num, (k, v) -> v == null ? 0 : v - 1);
        }
        return true;
    }

    public boolean isPossible2(int... nums) {
        return isPossible(nums, 0, new ArrayList<>());
    }

    private boolean isPossible(int[] nums, int index, List<List<Integer>> subs) {
        if (nums.length == index) {
            return subs.stream().allMatch(s -> s.size() >= 3);
        }
        int num = nums[index];
        boolean isUsed = false;
        for (int i = 0; i < subs.size(); i++) {
            List<Integer> sub = subs.get(i);
            int lastElement = sub.get(sub.size() - 1);
            if (lastElement == num - 1) {
                sub.add(num);
                if (isPossible(nums, index + 1, subs)) {
                    return true;
                }
                sub.remove(sub.size() - 1);
                isUsed = true;
            } else if (lastElement < num){
                if (sub.size() < 3) return false;
                subs.remove(sub);
                i--;
            }
        }
        if (!isUsed) {
            List<Integer> newSub = new ArrayList<>();
            newSub.add(num);
            subs.add(newSub);
            return isPossible(nums, index + 1, subs);
        }
        return false;
    }

    @Test
    public void check1() {
        Assertions.assertTrue(isPossible(1,2,3,3,4,5));
    }

    @Test
    public void check2() {
        Assertions.assertTrue(isPossible(1,2,3,3,4,4,5,5));
    }

    @Test
    public void check3() {
        Assertions.assertFalse(isPossible(1,2,3,4,4,5));
    }

    @Test
    public void check4() {
        Assertions.assertTrue(isPossible(1,1,2,2,3,3,4,5,6));
    }

}

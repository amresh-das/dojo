package com.amz;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class AnySum {

    @Test
    void t1() {
        int[] nums = new int[] {1, 0, -1, 0, -2, 2};
        int target = 0;
        Set<List<Integer>> expected = Sets.newHashSet(
                Lists.newArrayList(-1, 0, 0, 1),
                Lists.newArrayList(-2, -1, 1, 2),
                Lists.newArrayList(-2, 0, 0, 2)
        );
        Assertions.assertEquals(expected, new HashSet<>(new Solution(4).sum(nums, target)));
    }

    public List<List<Integer>> fourSum1(int[] nums, int target) {
        List<List<Integer>> solution = new ArrayList<>();
        Arrays.sort(nums);
        Map<Integer, Integer> lastIndexes = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            lastIndexes.put(nums[i], i);
        }
        for (int i = 0; i < nums.length - 3; i++) {
            int a = nums[i];
            for (int j = i + 1; j < nums.length - 2; j++) {
                int b = nums[j];
                for (int k = j + 1; k < nums.length - 1; k++) {
                    int c = nums[k];
                    for (int l = k + 1; l < nums.length; l++) {
                        int d = nums[l];
                        if (a + b + c + d == target) {
                            solution.add(Arrays.asList(a, b, c, d));
                            i = lastIndexes.get(a);
                            j = lastIndexes.get(b);
                            k = lastIndexes.get(c);
                            l = lastIndexes.get(d);
                        }
                    }
                }
            }
        }
        return solution;
    }

    private class Solution {
        private int limit;
        private List<List<Integer>> solution;
        private List<Integer> frame;
        private int[] numbers;
        private int target;

        public Solution(int limit) {
            this.limit = limit;
            solution = new ArrayList<>();
            frame = new ArrayList<>(limit);
        }

        public List<List<Integer>> sum(int[] nums, int target) {
            if (nums.length > limit) {
                this.numbers = nums;
                this.target = target;
                Arrays.sort(this.numbers);
                solve(0, -1, 0);
            }
            return solution;
        }

        private void solve(int level, int index, int frameSum) {
            if (limit == level) {
                if (frameSum == target) {
                    solution.add(new ArrayList<>(frame));
                }
            } else {
                if (frame.size() <= level) {
                    frame.add(0);
                }
                for (int i = index + 1; i < numbers.length; i++) {
                    int num = numbers[i];
                    frame.set(level, num);
                    frameSum += num;
                    solve(level + 1, i, frameSum);
                    frameSum -= num;
                    while (i < numbers.length - 1 && numbers[i] == numbers[i + 1]) i++;
                }
            }
        }
    }
}

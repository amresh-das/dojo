package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @see "https://leetcode.com/problems/subsets/"
 */
public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        final List<List<Integer>> results = new ArrayList<>();
        for (int k = 0; k <= nums.length; k++) {
            permute(nums, 0, nums.length, k, new ArrayList<>(), results);
        }
        return results;
    }

    private void permute(final int[] nums, final int index, final int n, final int k, final List<Integer> result, final List<List<Integer>> results) {
        if (result.size() == k) {
            results.add(new ArrayList<>(result));
            return;
        }
        if (index == n) return;
        Integer current = nums[index];
        result.add(current);
        permute(nums, index + 1, n, k, result, results);
        result.remove(current);
        permute(nums, index + 1, n, k, result, results);
    }


    @Test
    public void t1() {
        check("[1,2,3]", "[[],[1],[2],[3],[1,2],[1,3],[2,3],[1,2,3]]");
    }

    @Test
    public void t2() {
        check("[0]", "[[],[0]]");
    }

    private void check(String input, String expected) {
        final List<List<Integer>> ex = Arrays.stream(Utils.to2dIntMatrix(expected))
                .map(r -> Arrays.stream(r).boxed().collect(Collectors.toList()))
                .collect(Collectors.toList());
        Assertions.assertEquals(ex, subsets(Utils.stringToIntArray(input)));
    }

}

package com.amz;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @see "https://leetcode.com/problems/combination-sum/"
 */
public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        final List<List<Integer>> results = new ArrayList<>();
        if (candidates.length == 0) return results;
        solve(results, new ArrayList<>(), Arrays.stream(candidates).boxed().collect(Collectors.toList()), target);
        return results;
    }

    private void solve(final List<List<Integer>> results, final List<Integer> combination, final List<Integer> candidates, final int target) {
        if (target == 0) {
            results.add(new ArrayList<>(combination));
            return;
        }
        if (target < 0 || candidates.size() == 0) return;
        Integer candidate = candidates.get(0);
        final List<Integer> currentCombinations = new ArrayList<>(combination);
        for (int i = 0; i <= target / candidate; i++) {
            if (i > 0) {
                currentCombinations.add(candidate);
            }
            solve(results, currentCombinations, candidates.subList(1, candidates.size()), target - candidate * i);
        }
    }

    @Test
    void t1() {
        final List<List<Integer>> results = check(8, 2, 3, 5);
        final List<List<Integer>> expected = new ArrayList<>();
        expected.add(Lists.newArrayList(3,5));
        expected.add(Lists.newArrayList(2,3,3));
        expected.add(Lists.newArrayList(2,2,2,2));
        Assertions.assertEquals(expected, results);
    }

    @Test
    void t2() {
        final List<List<Integer>> results = check(7, 2,3,6,7);
        final List<List<Integer>> expected = new ArrayList<>();
        expected.add(Lists.newArrayList(7));
        expected.add(Lists.newArrayList(2,2,3));
        Assertions.assertEquals(expected, results);
    }

    @Test
    void t3() {
        final List<List<Integer>> results = check(1, 2);
        final List<List<Integer>> expected = new ArrayList<>();
        Assertions.assertEquals(expected, results);
    }

    @Test
    void t4() {
        final List<List<Integer>> results = check(1, 1);
        final List<List<Integer>> expected = new ArrayList<>();
        expected.add(Lists.newArrayList(1));
        Assertions.assertEquals(expected, results);
    }

    @Test
    void t54() {
        final List<List<Integer>> results = check(2, 1);
        final List<List<Integer>> expected = new ArrayList<>();
        expected.add(Lists.newArrayList(1,1));
        Assertions.assertEquals(expected, results);
    }

    private List<List<Integer>> check(final int target, final int... candidates) {
        return combinationSum(candidates, target);
    }
}

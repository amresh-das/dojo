package com.amz;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CombinationSum2 {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        final List<List<Integer>> results = new ArrayList<>();
        if (candidates.length == 0) return results;
        final Map<Integer, Integer> candidateFrequency = new HashMap<>();
        final List<Integer> candidateList = Arrays.stream(candidates).boxed().collect(Collectors.toList());
        candidateList.forEach(c -> candidateFrequency.compute(c, (k, v) -> v == null ? 1 : v + 1));
        solve(results, new ArrayList<>(), candidateList.stream().distinct().collect(Collectors.toList()), candidateFrequency, target);
        return results;
    }

    private void solve(final List<List<Integer>> results, final List<Integer> combination, final List<Integer> candidates, final Map<Integer, Integer> candidateFrequency, final int target) {
        if (target == 0) {
            results.add(new ArrayList<>(combination));
            return;
        }
        if (target < 0 || candidates.size() == 0) return;
        Integer candidate = candidates.get(0);
        final List<Integer> currentCombinations = new ArrayList<>(combination);
        for (int i = 0; i <= candidateFrequency.get(candidate); i++) {
            if (i > 0) {
                currentCombinations.add(candidate);
            }
            solve(results, currentCombinations, candidates.subList(1, candidates.size()), candidateFrequency, target - candidate * i);
        }
    }

    @Test
    void t1() {
        final List<List<Integer>> results = check(8, 10,1,2,7,6,1,5);
        final List<List<Integer>> expected = new ArrayList<>();
        expected.add(Lists.newArrayList(1,1,6));
        expected.add(Lists.newArrayList(1,2,5));
        expected.add(Lists.newArrayList(1,7));
        expected.add(Lists.newArrayList(2,6));
        Assertions.assertEquals(expected, results);
    }

    @Test
    void t2() {
        final List<List<Integer>> results = check(5, 2,5,2,1,2);
        final List<List<Integer>> expected = new ArrayList<>();
        expected.add(Lists.newArrayList(1,2,2));
        expected.add(Lists.newArrayList(5));
        Assertions.assertEquals(expected, results);
    }

    private List<List<Integer>> check(final int target, final int... candidates) {
        return combinationSum2(candidates, target);
    }
}

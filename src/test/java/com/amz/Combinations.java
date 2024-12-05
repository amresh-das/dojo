package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @see "https://leetcode.com/problems/combinations/"
 */
public class Combinations {

    public List<List<Integer>> combine(final int n, final int k) {
        final List<List<Integer>> results = new ArrayList<>();
        final List<Integer> result = new ArrayList<>();
        permute(1, n, k, result, results);
        return results;
    }

    private void permute(final Integer current, final int n, final int k, final List<Integer> result, final List<List<Integer>> results) {
        if (result.size() == k) {
            results.add(new ArrayList<>(result));
            return;
        }
        if (current > n) return;
        result.add(current);
        permute(current + 1, n, k, result, results);
        result.remove(current);
        permute(current + 1, n, k, result, results);
    }

    @Test
    public void t1() {
        check(4, 2, "[[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]");
    }

    @Test
    public void t2() {
        check(1, 1, "[[1]]");
    }

    private void check(int n, int k, String expected) {
        final List<List<Integer>> ex = Arrays.stream(Utils.to2dIntMatrix(expected))
                .map(r -> Arrays.stream(r).boxed().collect(Collectors.toList()))
                .collect(Collectors.toList());
        Assertions.assertEquals(ex, combine(n, k));
    }
}

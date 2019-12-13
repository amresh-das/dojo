package com.amz;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ConnectionFinder {

    @Test
    void t1() {
        List<List<Integer>> input = Lists.newArrayList(
            Lists.newArrayList(5, 1, 2, 3),
            Lists.newArrayList(2, 4, 6)
        );
        Assertions.assertTrue(isConnected(input, 1, 3));
    }

    @Test
    void t2() {
        List<List<Integer>> input = Lists.newArrayList(
            Lists.newArrayList(5, 1, 2, 3),
            Lists.newArrayList(2, 4, 6)
        );
        Assertions.assertTrue(isConnected(input, 5, 4));
    }

    @Test
    void t3() {
        List<List<Integer>> input = Lists.newArrayList(
            Lists.newArrayList(5, 1, 2, 3),
            Lists.newArrayList(2, 4, 6)
        );
        Assertions.assertTrue(isConnected(input, 6, 3));
    }

    public boolean isConnected(List<List<Integer>> data, int x, int y) {
        Map<Integer, Set<Integer>> d = new HashMap<>();
        for (List<Integer> row : data) {
            for (Integer item : row) {
                d.putIfAbsent(item, new HashSet<>());
            }
            d.get(row.get(0)).addAll(row.subList(1, row.size()));
        }
        for (Set<Integer> row : d.values()) {
            if (row.contains(x) && row.contains(y)) return true;
        }
        return check(d, x, y) || check(d, y, x);
    }

    private boolean check(final Map<Integer, Set<Integer>> d, final int start, final int end) {
        if (start == end) return true;
        Set<Integer> children = d.getOrDefault(start, Sets.newHashSet());
        for (Integer c : children) {
            if (check(d, c, end)) {
                return true;
            }
        }
        return false;
    }
}

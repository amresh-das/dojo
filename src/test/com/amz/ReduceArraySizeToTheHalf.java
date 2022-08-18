package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @see "https://leetcode.com/problems/reduce-array-size-to-the-half/"
 */
public class ReduceArraySizeToTheHalf {

    public int minSetSize(int... arr) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int a : arr) counts.compute(a, (k, v) -> v == null ? 1 : v + 1);
        List<Integer> frequencies = counts.values().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        int count = 0, toDelete = arr.length / 2;
        while (toDelete > 0) toDelete -= frequencies.get(count++);
        return count;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(2, minSetSize(3,3,3,3,5,5,5,2,2,7));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(1, minSetSize(7,7,7,7,7,7));
    }
}

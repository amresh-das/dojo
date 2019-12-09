package com.amz;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class RelativeArraySort {
    @Test
    void t1() {
        int[] arr1 = new int[] {2,3,1,3,2,4,6,7,9,2,19};
        int[] arr2 = new int[] {2,1,4,3,9,6};
        List<Integer> expected = Lists.newArrayList(2,2,2,1,4,3,3,9,6,7,19);
        Assertions.assertEquals(expected, Arrays.stream(relativeSortArray(arr1, arr2)).boxed().collect(Collectors.toList()));
    }

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        final Map<Integer, Integer> itemFrequency = new TreeMap<>();
        for (int x : arr1) {
            itemFrequency.compute(x , (k, v) -> v == null ? 1 : v + 1);
        }
        AtomicInteger index = new AtomicInteger(0);
        for (int x : arr2) {
            Integer count = itemFrequency.remove(x);
            for (int i = 0; i < count; i++) {
                arr1[index.getAndIncrement()] = x;
            }
        }
        itemFrequency.forEach((x, count) -> {
            for (int i = 0; i < count; i++) {
                arr1[index.getAndIncrement()] = x;
            }
        });
        return arr1;
    }
}

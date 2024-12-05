package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class HighFiveScore {

    @Test
    void t1() {
        int[][] inputs = {
                new int[]{1, 91},
                new int[]{1, 92},
                new int[]{2, 93},
                new int[]{2, 97},
                new int[]{1, 60},
                new int[]{2, 77},
                new int[]{1, 65},
                new int[]{1, 87},
                new int[]{1, 100},
                new int[]{2, 100},
                new int[]{2, 76}
        };
        int[][] output = new int[][] {new int[] {1,87}, new int[]{2,88}};
        int[][] actual = highFive(inputs);
        for (int i = 0; i < output.length; i++) {
            for (int j = 0; j < 2; j++) {
                Assertions.assertEquals(output[i][j], actual[i][j]);
            }
        }
    }

    public int[][] highFive(int[][] items) {
        final Map<Integer, List<Integer>> scores = new HashMap<>();
        for (int[] it : items) {
            scores.compute(it[0], (k, v) -> {
               List<Integer> values = (v == null) ? new ArrayList<>() : v;
               values.add(it[1]);
               return values;
            });
        }
        final int[][] output = new int[scores.size()][1];
        AtomicInteger i = new AtomicInteger();
        scores.forEach((id, sc) -> {
            output[i.getAndIncrement()] = new int[] {id, sc.stream().sorted(Comparator.reverseOrder()).limit(5).reduce(0, Integer::sum) / 5};
        });
        return output;
    }
}

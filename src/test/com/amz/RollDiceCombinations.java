package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class RollDiceCombinations {

    @Test
    void t1() {
        Assertions.assertEquals(1, numRollsToTarget(1, 6, 3));
    }

    @Test
    void t2() {
        Assertions.assertEquals(6, numRollsToTarget(2, 6, 7));
    }

    @Test
    void t3() {
        Assertions.assertEquals(1, numRollsToTarget(2, 5, 10));
    }

    @Test
    void t4() {
        Assertions.assertEquals(0, numRollsToTarget(1, 2, 3));
    }

    @Test
    void t5() {
        Assertions.assertEquals(222616187, numRollsToTarget(30, 30, 500));
    }

    public int numRollsToTarget(int d, int f, int target) {
        if (d * f < target || d > target) return 0;
        final int mod = 1000000007;
        Map<Integer, Long> previousLevel;
        Map<Integer, Long> currentLevel = new HashMap<>();
        for (int i = 1; i <= f; i++) {
            currentLevel.put(i, 1L);
        }

        for (int rollCount = 2; rollCount <= d; rollCount++) {
            previousLevel = currentLevel;
            currentLevel = new HashMap<>();
            for (int rollValue = 1; rollValue <= f; rollValue++) {
                for (Map.Entry<Integer, Long> entry: previousLevel.entrySet()) {
                    currentLevel.compute(entry.getKey() + rollValue, (k, v) -> (v == null ? entry.getValue() : v + entry.getValue()) % mod);
                }
            }
        }
        return (int) currentLevel.get(target).longValue();
    }

}

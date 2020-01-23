package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @see "https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/"
 */
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
        Map<Integer, Integer> dThRoll = new HashMap<Integer, Integer>(){{
            put(0, 1);
        }};
        Map<Integer, Integer> d_1ThRoll;

        for (int rollCount = 1; rollCount <= d; rollCount++) {
            d_1ThRoll = dThRoll;
            dThRoll = new HashMap<>();
            for (int rollValue = 1; rollValue <= f; rollValue++) {
                for (Map.Entry<Integer, Integer> e: d_1ThRoll.entrySet()) {
                    if (e.getKey() + rollValue <= target) {
                        dThRoll.compute(e.getKey() + rollValue,
                                (k, v) -> ((v == null ? 0 : v) + e.getValue()) % mod);
                    }
                }
            }
        }
        return dThRoll.get(target);
    }

}

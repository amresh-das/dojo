package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.IntStream;

/**
 * @see "https://leetcode.com/problems/finding-3-digit-even-numbers/"
 */
public class Find3DigitEvenNumbers {

    public int[] findEvenNumbers(int... nums) {
        final Map<Integer, Integer> numCount = new HashMap<>();
        final int[] digitCounts = new int[10];
        for (int n : nums) {
            numCount.compute(n, (k, v) -> v == null ? 1 : v + 1);
        }
        return IntStream.range(100, 999)
                .filter(n -> n % 2 == 0)
                .filter(n -> {
                    int o = n % 10;
                    int t = (n / 10) % 10;
                    int h = (n / 100) % 10;
                    digitCounts[o] = 0;
                    digitCounts[t] = 0;
                    digitCounts[h] = 0;
                    digitCounts[o] += 1;
                    digitCounts[t] += 1;
                    digitCounts[h] += 1;
                    return (numCount.getOrDefault(o, 0) >= digitCounts[o]
                            && numCount.getOrDefault(t, 0) >= digitCounts[t]
                            && numCount.getOrDefault(h, 0) >= digitCounts[h]);
                }).toArray();
    }

    @Test
    public void check() {
        Assertions.assertEquals("[102,120,130,132,210,230,302,310,312,320]", Utils.toString(findEvenNumbers(2,1,3,0)));
    }

    @Test
    public void check2() {
        Assertions.assertEquals("[222,228,282,288,822,828,882]", Utils.toString(findEvenNumbers(2,2,8,8,2)));
    }

    @Test
    public void check3() {
        Assertions.assertEquals("[]", Utils.toString(findEvenNumbers(3,7,5)));
    }

}

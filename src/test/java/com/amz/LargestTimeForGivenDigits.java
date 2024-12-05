package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @see "https://leetcode.com/problems/largest-time-for-given-digits/"
 */
public class LargestTimeForGivenDigits {

    public String largestTimeFromDigits(int[] arr) {
        Map<Integer, Integer> digits = new HashMap<>();
        for (int n : arr) {
            digits.compute(n, (k, v) -> v == null ? 1 : v + 1);
        }
        for (int h = 23; h >= 0; h--) {
            int h1 = h / 10;
            int h2 = h % 10;
            if (digits.getOrDefault(h1, 0) > 0) {
                digits.compute(h1, (k, v) -> v == null ? 0 : v - 1);
                if (digits.getOrDefault(h2, 0) > 0) {
                    digits.compute(h2, (k, v) -> v == null ? 0 : v - 1);

                    for (int m = 59; m >= 0; m--) {
                        int m1 = m / 10;
                        int m2 = m % 10;

                        if (digits.getOrDefault(m1, 0) > 0) {
                            digits.compute(m1, (k, v) -> v == null ? 0 : v - 1);
                            if (digits.getOrDefault(m2, 0) > 0) {
                                return "" + h1 + h2 + ":" + m1 + m2;
                            }
                            digits.compute(m1, (k, v) -> v == null ? 1 : v + 1);
                        }
                    }

                    digits.compute(h2, (k, v) -> v == null ? 1 : v + 1);
                }
                digits.compute(h1, (k, v) -> v == null ? 1 : v + 1);
            }
        }
        return "";
    }

    @Test
    public void check1() {
        Assertions.assertEquals("23:41", largestTimeFromDigits(Utils.stringToIntArray("[1,2,3,4]")));
    }

    @Test
    public void check2() {
        Assertions.assertEquals("", largestTimeFromDigits(Utils.stringToIntArray("[5,5,5,5]")));
    }

}

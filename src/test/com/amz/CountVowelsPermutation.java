package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @see "https://leetcode.com/problems/count-vowels-permutation/"
 */
public class CountVowelsPermutation {

    public int countVowelPermutation(int n) {
        if (n == 0) return 0;
        Map<Character, Long> initial = new HashMap<>();
        initial.put('a', 1L);
        initial.put('e', 1L);
        initial.put('i', 1L);
        initial.put('o', 1L);
        initial.put('u', 1L);
        for (int i = 0; i < n - 1; i++) {
            Map<Character, Long> next = new HashMap<>();
            for (Map.Entry<Character, Long> e : initial.entrySet()) {
                char c = e.getKey();
                long count = e.getValue();
                if (c == 'a') {
                    apply(next, 'e', count);
                } else if (c == 'e') {
                    apply(next, 'a', count);
                    apply(next, 'i', count);
                } else if (c == 'i') {
                    apply(next, 'a', count);
                    apply(next, 'e', count);
                    apply(next, 'o', count);
                    apply(next, 'u', count);
                } else if (c == 'o') {
                    apply(next, 'i', count);
                    apply(next, 'u', count);
                } else if (c == 'u') {
                    apply(next, 'a', count);
                }
            }
            initial = next;
        }
        return initial.values().stream().reduce(0L, (a, b) -> {
            long sum = a + b;
            if (sum > 1000000007 || sum < 0) {
                return sum % 1000000007;
            } else {
                return sum;
            }
        }).intValue();
    }

    private void apply(Map<Character, Long> map, char ch, long count) {
        map.compute(ch, (k, v) -> {
            long prev = v == null ? 0 : v;
            long next = prev + count;
            if (next > 1000000007 || next < 0) {
                return next % 1000000007;
            } else {
                return next;
            }
        });
    }

    @Test
    public void check1() {
        Assertions.assertEquals(5, countVowelPermutation(1));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(10, countVowelPermutation(2));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(68, countVowelPermutation(5));
    }

    @Test
    public void check4() {
        Assertions.assertEquals(18208803, countVowelPermutation(144));
    }
}

package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @see "https://leetcode.com/problems/count-vowels-permutation/"
 */
public class CountVowelsPermutation {

    public int countVowelPermutation(int n) {
        if (n == 0) return 0;
        Map<Character, Long> initial = Stream.of('a', 'e', 'i', 'o', 'u').collect(Collectors.toMap(ch -> ch, ch -> 1L));
        for (int i = 0; i < n - 1; i++) {
            Map<Character, Long> next = new HashMap<>();
            for (Map.Entry<Character, Long> e : initial.entrySet()) {
                char c = e.getKey();
                long count = e.getValue();
                if (c == 'a') {
                    sum(next, 'e', count);
                } else if (c == 'e') {
                    sum(next, 'a', count);
                    sum(next, 'i', count);
                } else if (c == 'i') {
                    sum(next, 'a', count);
                    sum(next, 'e', count);
                    sum(next, 'o', count);
                    sum(next, 'u', count);
                } else if (c == 'o') {
                    sum(next, 'i', count);
                    sum(next, 'u', count);
                } else if (c == 'u') {
                    sum(next, 'a', count);
                }
            }
            initial = next;
        }
        return initial.values().stream().reduce(0L, (a, b) -> (a + b) % 1000000007).intValue();
    }

    private void sum(Map<Character, Long> map, char ch, long count) {
        map.compute(ch, (k, v) -> v == null ? count : (v + count) % 1000000007);
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

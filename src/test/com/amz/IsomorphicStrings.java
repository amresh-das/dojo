package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @see "https://leetcode.com/problems/isomorphic-strings/"
 */
public class IsomorphicStrings {

    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;
        Map<Integer, List<Integer>> source = toMap(s);
        Map<Integer, List<Integer>> target = toMap(t);
        return source.equals(target);
    }

    private Map<Integer, List<Integer>> toMap(final String s) {
        final Map<Character, Integer> sourceChars = new HashMap<>();
        final Map<Integer, List<Integer>> source = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            final int index = i;
            int digit = sourceChars.compute(s.charAt(i), (k, v) -> v == null ? index : v);
            source.compute(digit, (k, v) -> {
                List<Integer> list = v == null ? new ArrayList<>() : v;
                list.add(index);
                return list;
            });
        }
        return source;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(true, isIsomorphic("egg", "add"));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(false, isIsomorphic("foo", "bar"));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(true, isIsomorphic("paper", "title"));
    }
}

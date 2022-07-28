package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @see "https://leetcode.com/problems/valid-anagram/"
 */
public class ValidAnagram {

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        final Map<Character, Integer> sMap = new HashMap<>();
        final Map<Character, Integer> tMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            sMap.compute(s.charAt(i), (k, v) -> v == null ? 1 : v + 1);
            tMap.compute(t.charAt(i), (k, v) -> v == null ? 1 : v + 1);
        }
        return sMap.equals(tMap);
    }

    @Test
    public void check1() {
        Assertions.assertTrue(isAnagram("anagram", "nagaram"));
    }

    @Test
    public void check2() {
        Assertions.assertFalse(isAnagram("rat", "car"));
    }

}

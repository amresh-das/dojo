package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @see "https://leetcode.com/problems/string-transforms-into-another-string/"
 */
public class StringTransformsIntoAnotherString {
    public boolean canConvert(String str1, String str2) {
        if (str1.length() != str2.length()) return false;
        if (str1.equals(str2)) return true;
        final Map<Character, Character> map = new HashMap<>();
        final Set<Character> targetChars = new HashSet<>();
        for (int i = 0; i < str1.length(); i++) {
            char c1 = str1.charAt(i);
            char c2 = str2.charAt(i);
            if (!map.containsKey(c1)) {
                map.put(c1, c2);
                targetChars.add(c2);
            } else if (map.get(c1) != c2) return false;
        }
        return targetChars.size() < 26;
    }

    public boolean canConvert2(String str1, String str2) {
        if (str1.length() != str2.length()) return false;
        final Set<String> visited = new HashSet<>();
        return canConvert(str1, str2, visited);
    }

    private boolean canConvert(String source, String target, Set<String> visited) {
        if (source.equals(target)) return true;
        if (visited.contains(source)) return false;
        visited.add(source);
        for (char ch1 = 'a'; ch1 <= 'z'; ch1++) {
            for (char ch2 = 'a'; ch2 <= 'z'; ch2++) {
                if (canConvert(source.replaceAll("" + ch1, "" + ch2), target, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Test
    public void check1() {
        Assertions.assertTrue(canConvert("aabcc", "ccdee"));
    }

    @Test
    public void check2() {
        Assertions.assertFalse(canConvert("leetcode", "codeleet"));
    }

    @Test
    public void check3() {
        Assertions.assertTrue(canConvert("abcdefghijklmnopqrstuvwxyz", "bcadefghijklmnopqrstuvwxzz"));
    }
}

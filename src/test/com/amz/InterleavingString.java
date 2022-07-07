package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class InterleavingString {

    public boolean isInterleave(String s1, String s2, String s3) {
        final Map<String, Boolean> memo = new HashMap<>();
        return isInterLeaveFrom(s1, s2, s3, 0, 0, 0, memo);
    }

    private boolean isInterLeaveFrom(String s1, String s2, String s3, int s1Index, int s2Index, int s3Index, final Map<String, Boolean> memo) {
        System.out.printf("%s: [%s] [%s]", s3.substring(0, s3Index), s1.substring(s1Index), s2.substring(s2Index));
        String key = s1Index + ":" + s2Index + ":" + s3Index;
        if (s3Index == s3.length()) {
            memo.put(key, true);
            return s1Index == s1.length() && s2Index == s2.length();
        }
        if (memo.containsKey(key)) return memo.get(key);
        if (s1Index < s1.length() && s3.charAt(s3Index) == s1.charAt(s1Index)) {
            if (isInterLeaveFrom(s1, s2, s3, s1Index + 1, s2Index, s3Index + 1, memo)) {
                memo.put(key, true);
                return true;
            }
        }
        if (s2Index < s2.length() && s3.charAt(s3Index) == s2.charAt(s2Index)) {
            if (isInterLeaveFrom(s1, s2, s3, s1Index, s2Index + 1, s3Index + 1, memo)) {
                memo.put(key, true);
                return true;
            }
        }
        memo.put(key, false);
        return false;
    }

    public boolean isInterleaveBad(String s1, String s2, String s3) {
        final Map<Character, Integer> s1Counts = countChars(s1);
        final Map<Character, Integer> s2Counts = countChars(s2);
        final Map<Character, Integer> s3Counts = countChars(s3);

        final Set<Character> chars = new HashSet<>(s1Counts.keySet());
        chars.removeAll(s2Counts.keySet());
        chars.removeAll(s1Counts.keySet());
        if (!chars.isEmpty()) return false;

        for (Map.Entry<Character, Integer> count : s3Counts.entrySet()) {
            if (count.getValue() != s1Counts.getOrDefault(count.getKey(), 0) + s2Counts.getOrDefault(count.getKey(), 0)) {
                return false;
            }
        }
        return true;
    }

    private Map<Character, Integer> countChars(final String s) {
        Map<Character, Integer> count = new HashMap<>();
        for (char c : s.toCharArray()) {
            count.compute(c, (k, v) -> v == null ? 1 : v + 1);
        }
        return count;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(true, isInterleave("aabcc", "dbbca", "aadbbcbcac"));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(false, isInterleave("aabcc", "dbbca", "aadbbbaccc"));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(true, isInterleave("", "", ""));
    }

    @Test
    public void check4() {
        Assertions.assertEquals(false, isInterleave("", "", "a"));
    }

    @Test
    public void check5() {
        Assertions.assertEquals(true, isInterleave("aabcc", "dbbca", "aadbbcbcac"));
    }

    @Test
    public void check6() {
        Assertions.assertEquals(true, isInterleave("bbbbbabbbbabaababaaaabbababbaaabbabbaaabaaaaababbbababbbbbabbbbababbabaabababbbaabababababbbaaababaa",
                "babaaaabbababbbabbbbaabaabbaabbbbaabaaabaababaaaabaaabbaaabaaaabaabaabbbbbbbbbbbabaaabbababbabbabaab",
                "babbbabbbaaabbababbbbababaabbabaabaaabbbbabbbaaabbbaaaaabbbbaabbaaabababbaaaaaabababbababaababbababbbababbbbaaaabaabbabbaaaaabbabbaaaabbbaabaaabaababaababbaaabbbbbabbbbaabbabaabbbbabaaabbababbabbabbab"));
    }
}

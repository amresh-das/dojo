package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class WildcardMatching {

    public boolean isMatch(String s, String p) {
        if (p.length() == 0) {
            return s.length() == 0;
        }
        char[] pattern = removeDupeWildMatch(p);
        System.out.println(pattern);
        boolean[][] dp = new boolean[s.length() + 1][pattern.length + 1];

        dp[0][0] = true;
        if (pattern.length > 0 && pattern[0] == '*') {
            dp[0][1] = true;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (pattern[j - 1] == '?' || s.charAt(i - 1) == pattern[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pattern[j - 1] == '*') {
                    dp[i][j] = dp[i - 1][j] || dp [i][j -1];
                }
            }
        }
        return dp[s.length()][pattern.length];
    }

    private char[] removeDupeWildMatch(final String p) {
        int pLength = 0;
        char[] pattern = new char[p.length()];
        for (int i = 0; i < p.length() - 1; i++) {
            while (p.charAt(i) == '*' && i < p.length() -1 && p.charAt(i + 1) == '*') {
                i++;
            }
            if (i < p.length() - 1) {
                pattern[pLength++] = p.charAt(i);
            }
        }
        pattern[pLength++] = p.charAt(p.length() - 1);
        return Arrays.copyOfRange(pattern, 0, pLength);
    }


    public boolean isMatch2(String s, String p) {
        return isMatch(s, p.replaceAll("\\*+", "*"), 0, 0);
    }

    private boolean isMatch(final String s, final String p, final int stringIndex, final int patternIndex) {
        final boolean isStringConsumed = stringIndex == s.length();
        final boolean isPatternConsumed = patternIndex == p.length();
        if (isStringConsumed && (isPatternConsumed || p.substring(patternIndex).equals("*"))) return true;
        if (isStringConsumed || isPatternConsumed) return false;
        char sChar = s.charAt(stringIndex);
        char pChar = p.charAt(patternIndex);
        if (pChar == '?') {
            return isMatch(s, p, stringIndex + 1, patternIndex + 1);
        } else if (pChar == '*') {
            return isMatch(s, p, stringIndex, patternIndex + 1) ||
                    isMatch(s, p, stringIndex + 1, patternIndex + 1) ||
                    isMatch(s, p, stringIndex + 1, patternIndex);
        } else if (sChar == pChar) {
            return isMatch(s, p, stringIndex + 1, patternIndex + 1);
        } else {
            return false;
        }
    }


    @Test
    void t1() {
        Assertions.assertFalse(isMatch("aa", "a"));
    }

    @Test
    void t2() {
        Assertions.assertTrue(isMatch("aa", "*"));
    }

    @Test
    void t3() {
        Assertions.assertFalse(isMatch("cb", "?a"));
    }

    @Test
    void t4() {
        Assertions.assertTrue(isMatch("adceb", "*a*b"));
    }

    @Test
    void t5() {
        Assertions.assertFalse(isMatch("acdcb", "a*c?b"));
    }

    @Test
    void t6() {
        Assertions.assertTrue(isMatch("", "******"));
    }

    @Test
    void t7() {
        final String s = "babbbbaabababaabbababaababaabbaabababbaaababbababaaaaaabbabaaaabababbabbababbbaaaababbbabbbbbbbbbbaabbb";
        final String p = "b**bb**a**bba*b**a*bbb**aba***babbb*aa****aabb*bbb***a";
//        System.out.println(s.matches(p.replaceAll("\\*", ".*")));
        Assertions.assertFalse(isMatch(s, p));
    }
}

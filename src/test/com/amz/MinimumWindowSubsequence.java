package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/minimum-window-subsequence/"
 */
public class MinimumWindowSubsequence {

    public String minWindow(String s1, String s2) {
        int[] indices = new int[s2.length()];
        int startIndex = 0;
        String minMatch = "";
        while (startIndex != -1) {
            indices[0] = s1.indexOf(s2.charAt(0), startIndex);
            if (indices[0] == -1) return minMatch;
            for (int i = 1; i < s2.length(); i++) {
                indices[i] = s1.indexOf(s2.charAt(i), indices[i - 1] + 1);
                if (indices[i] == -1) return minMatch;
            }
            String match = s1.substring(indices[0], indices[s2.length() - 1] + 1);
            if (match.length() == s2.length()) return match;
            if (minMatch.length() == 0 || match.length() < minMatch.length()) {
                minMatch = match;
            }
            startIndex = indices[0] + 1;
        }
        return minMatch;
    }


    @Test
    public void check1() {
        Assertions.assertEquals("bcde", minWindow("abcdebdde", "bde"));
    }

    @Test
    public void check2() {
        Assertions.assertEquals("", minWindow("jmeqksfrsdcmsiwvaovztaqenprpvnbstl", "u"));
    }

    @Test
    public void check3() {
        Assertions.assertEquals("abcd", minWindow("abceeedabcd", "abcd"));
    }

    @Test
    public void check4() {
        Assertions.assertEquals("mccqouqadqtm", minWindow("cnhczmccqouqadqtmjjzl", "mm"));
    }
}

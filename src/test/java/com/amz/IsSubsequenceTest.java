package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/is-subsequence/description/?envType=study-plan-v2&envId=leetcode-75"
 */
public class IsSubsequenceTest {

    public boolean isSubsequence(String s, String t) {
        int matchIndex = 0;
        final int n = s.length();
        final int m = t.length();
        if (n == 0) return true;
        for (int i = 0; i < m; i++) {
            if (t.charAt(i) == s.charAt(matchIndex)) {
                matchIndex++;
                if (matchIndex == n) return true;
            }
            if (n - matchIndex > m - i) {
                return false;
            }
        }
        return false;
    }

    @Test
    void check1() {
        Assertions.assertTrue(isSubsequence("abc", "ahbgdc"));
    }

    @Test
    void check2() {
        Assertions.assertFalse(isSubsequence("axc", "ahbgdc"));
    }

    @Test
    void check3() {
        Assertions.assertFalse(isSubsequence("axcfdfs", "ahbgdc"));
    }

    @Test
    void check4() {
        Assertions.assertFalse(isSubsequence("", "ahbgdc"));
    }
}

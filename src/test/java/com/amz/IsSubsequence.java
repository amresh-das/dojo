package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/is-subsequence/"
 */
public class IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        int index = -1;
        for (char c : s.toCharArray()) {
            index = t.indexOf(c, index + 1);
            if (index == -1) return false;
        }
        return true;
    }

    @Test
    public void check1() {
        Assertions.assertTrue(isSubsequence("abc", "ahbgdc"));
    }

    @Test
    public void check2() {
        Assertions.assertFalse(isSubsequence("axc", "ahbgdc"));
    }

}

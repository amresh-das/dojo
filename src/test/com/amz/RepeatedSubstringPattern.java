package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RepeatedSubstringPattern {

    @Test
    public void t1() {
        verify("", false);
    }

    @Test
    void t2() {
        verify(null, false);
    }

    @Test
    void t3() {
        verify("a", false);
    }

    @Test
    void t4() {
        verify("ab", false);
    }

    @Test
    void t5() {
        verify("aa", true);
        verify("abab", true);
    }

    private void verify(String input, boolean expected) {
        Assertions.assertEquals(expected, repeatedSubstringPattern(input));
    }

    public boolean repeatedSubstringPattern(String s) {
        if (s != null) {
            if (s.length() <= 1) {
                return false;
            } else {
                int len = s.length() / 2;
                boolean result = true;
                for (int i = 0, j = len; i < len; i++, j++) {
                    if (s.charAt(i) != s.charAt(j)) {
                        result = false;
                        break;
                    }
                }
                return result;
            }
        }
        return false;
    }
}

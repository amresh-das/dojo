package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * @see "https://leetcode.com/problems/backspace-string-compare/"
 */
public class BackspaceStringCompare {

    public boolean backspaceCompare(String s, String t) {
        return evaluate(s).equals(evaluate(t));
    }

    private String evaluate(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == '#') {
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public boolean backspaceCompare2(String s, String t) {
        int i = s.length() - 1, j = t.length() - 1;
        for (; i >= 0 || j >= 0; i--, j--) {
            int sBackSpaceCount = 0;
            while (i >= 0 && s.charAt(i) == '#') {
                i--;
                sBackSpaceCount++;
            }
            int tBackSpaceCount = 0;
            while (j >= 0 && t.charAt(j) == '#') {
                j--;
                tBackSpaceCount++;
            }
            i = Math.max(-1, i - sBackSpaceCount);
            j = Math.max(-1, j - tBackSpaceCount);
            Character sChar = i < 0 ? null : s.charAt(i);
            Character tChar = j < 0 ? null : t.charAt(j);
            if (sChar != null && tChar!= null && sChar != tChar) return false;
        }
        return i == j;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(true, backspaceCompare("ab#c", "ad#c"));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(true, backspaceCompare("ab##", "c#d#"));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(false, backspaceCompare("a#c", "b"));
    }

    @Test
    public void check4() {
        Assertions.assertEquals(true, backspaceCompare("xywrrmp", "xywrrmu#p"));
    }

}

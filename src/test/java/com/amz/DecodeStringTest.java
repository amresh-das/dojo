package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * @see "https://leetcode.com/problems/decode-string/description/?envType=study-plan-v2&envId=leetcode-75"
 */
public class DecodeStringTest {

    public String decodeString(String s) {
        final StringBuilder out = new StringBuilder(s);
        final Stack<int[]> stack = new Stack<>();
        int i = 0;
        int num = 0;
        int numStart = -1;
        while (i < out.length()) {
            final char c = out.charAt(i);
            if (isDigit(c)) {
                if (numStart == -1) numStart = i;
                num = num * 10 + (c - '0');
            } else {
                final int endIndex = i + 1;
                if (c == '[') {
                    stack.push(new int[] {numStart, endIndex, num});
                    numStart = -1;
                    num = 0;
                } else if (c == ']') {
                    int[] params = stack.pop();
                    String expr = out.substring(params[1], i);
                    final int start = params[0];
                    final int repeatCount = params[2];
                    out.replace(start, endIndex, expr.repeat(repeatCount));
                    i = start + expr.length() * repeatCount - 1;
                }
            }
            i++;
        }
        return out.toString();
    }

    private static boolean isDigit(final char c) {
        return c >= '0' && c <= '9';
    }

    private static boolean isLetter(final char c) {
        return c >= 'a' && c <= 'z';
    }

    @Test
    void check1() {
        Assertions.assertEquals("aaabcbc", decodeString("3[a]2[bc]"));
    }

    @Test
    void check2() {
        Assertions.assertEquals("accaccacc", decodeString("3[a2[c]]"));
    }

    @Test
    void check3() {
        Assertions.assertEquals("abcabccdcdcdef", decodeString("2[abc]3[cd]ef"));
    }
}

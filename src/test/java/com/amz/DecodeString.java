package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * @see "https://leetcode.com/problems/decode-string/"
 */
public class DecodeString {

    public String decodeString(String s) {
        final StringBuilder output = new StringBuilder();
        final Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ']') {
                String expr = getExpression(stack);
                int repeater = getMultiplier(stack);
                for (int j = 0; j < repeater; j++) {
                    for (char ch : expr.toCharArray()) {
                        stack.push(ch);
                    }
                }
            } else {
                stack.push(c);
            }
        }
        while (!stack.isEmpty()) {
            output.insert(0, stack.pop());
        }
        return output.toString();
    }

    private int getMultiplier(final Stack<Character> stack) {
        String multiplier = "";
        while (!stack.isEmpty()) {
            char ch = stack.peek();
            if (ch >= '0' && ch <= '9') {
                multiplier = (ch - '0') + multiplier;
                stack.pop();
            } else break;
        }
        return Integer.parseInt(multiplier);
    }

    private String getExpression(final Stack<Character> stack) {
        StringBuilder expr = new StringBuilder();
        do {
            char ch = stack.pop();
            if (ch == '[') break;
            expr.insert(0, ch);
        } while (true);
        return expr.toString();
    }

    @Test
    public void check1() {
        Assertions.assertEquals("aaabcbc", decodeString("3[a]2[bc]"));
    }

    @Test
    public void check2() {
        Assertions.assertEquals("accaccacc", decodeString("3[a2[c]]"));
    }

    @Test
    public void check3() {
        Assertions.assertEquals("abcabccdcdcdef", decodeString("2[abc]3[cd]ef"));
    }

    @Test
    public void check4() {
        Assertions.assertEquals("leetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcode", decodeString("100[leetcode]"));
    }
}

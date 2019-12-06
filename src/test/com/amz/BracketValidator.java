package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

public class BracketValidator {

    @Test
    void t1() {
        Assertions.assertEquals(true, isValid("()"));
    }

    @Test
    void t2() {
        Assertions.assertEquals(true, isValid("()[]{}"));
    }

    @Test
    void t3() {
        Assertions.assertEquals(false, isValid("(]"));
    }

    @Test
    void t4() {
        Assertions.assertEquals(false, isValid("([)]"));
    }

    @Test
    void t5() {
        Assertions.assertEquals(true, isValid("{[]}"));
    }

    public boolean isValid(String s) {
        final Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            final char lastChar = stack.isEmpty() ? ' ' : stack.peek();
            if (lastChar == '(' && c == ')' || lastChar == '[' && c == ']' || lastChar == '{' && c == '}') {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}

package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * @see "https://leetcode.com/problems/longest-valid-parentheses/"
 */
public class LongestValidParentheses {
    private Solution solution = new Solution();

    @Test
    public void t1() {
        check("(()", 2);
    }

    @Test
    public void t2() {
        check(")()())", 4);
    }

    private void check(final String input, final int expected) {
        Assertions.assertEquals(expected, solution.longestValidParentheses(input));
    }

    class Solution {
        public int longestValidParentheses(String input) {
           final Stack<Integer> stack = new Stack<>();
           int longestLength = 0;
           stack.push(-1);
           for (int i = 0; i < input.length(); i++) {
                char ch = input.charAt(i);
                if (ch == '(') {
                    stack.push(i);
                } else {
                    stack.pop();
                   if (stack.isEmpty()) {
                       stack.push(i);
                   } else {
                       longestLength = Math.max(longestLength, i - stack.peek());
                   }
                }
           }
           return longestLength;
        }
    }

}

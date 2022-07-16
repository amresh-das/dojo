package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * @see "https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/"
 */
public class RemoveAllAdjacentDuplicatesInString {
    public String removeDuplicates(String s) {
        final StringBuilder r = new StringBuilder();
        final Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        stack.forEach(r::append);
        return r.toString();
    }

    @Test
    public void check1() {
        Assertions.assertEquals("ca", removeDuplicates("abbaca"));
    }

    @Test
    public void check2() {
        Assertions.assertEquals("ay", removeDuplicates("azxxzy"));
    }
}

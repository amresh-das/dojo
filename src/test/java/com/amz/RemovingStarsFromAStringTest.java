package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Stack;

/**
 * @see "https://leetcode.com/problems/removing-stars-from-a-string/?envType=study-plan-v2&envId=leetcode-75"
 */
public class RemovingStarsFromAStringTest {

    public String removeStars(String s) {
        final char[] chars = s.toCharArray();
        int sz = 0;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '*') {
                sz--;
            } else {
                chars[sz++] = c;
            }
        }
        return new String(Arrays.copyOf(chars, sz));
    }

    public String removeStars2(String s) {
        final Stack<Character> characters = new Stack<>();
        for (char c: s.toCharArray()) {
            if (c == '*') {
                characters.pop();
            } else {
                characters.push(c);
            }
        }
        final StringBuilder out = new StringBuilder();
        while (!characters.isEmpty()) out.insert(0, characters.pop());
        return out.toString();
    }

    @Test
    void check1() {
        Assertions.assertEquals("lecoe", removeStars("leet**cod*e"));
    }

    @Test
    void check2() {
        Assertions.assertEquals("", removeStars("erase*****"));
    }
}

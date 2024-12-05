package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/length-of-last-word/"
 */
public class LengthOfLastWord {

    public int lengthOfLastWord(String s) {
        int streak = 0;
        char prev = ' ';
        for (char c : s.toCharArray()) {
            if (prev == ' ' && c != ' ') {
                streak = 0;
            }
            if (c != ' ') {
                streak++;
            }
            prev = c;
        }
        return streak;
    }

    @Test
    void t1() {
        verify("Hello World", 5);
    }

    @Test
    void t2() {
        verify("   fly me   to   the moon  ", 4);
    }

    @Test
    void t3() {
        verify("luffy is still joyboy", 6);
    }

    @Test
    void t4() {
        verify("Today is a nice day", 3);
    }

    private void verify(final String input, final int expected) {
        Assertions.assertEquals(expected, lengthOfLastWord(input), input);
    }
}

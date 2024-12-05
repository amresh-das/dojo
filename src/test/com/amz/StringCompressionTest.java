package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/string-compression/description/?envType=study-plan-v2&envId=leetcode-75"
 */
public class StringCompressionTest {

    public int compress(char[] chars) {
        int insIndex = 0;
        for (int readIndex = 0; readIndex < chars.length; readIndex++) {
            char cur = chars[readIndex];
            int count = 1;
            while (readIndex + count < chars.length && chars[readIndex + count] == cur) count++;
            chars[insIndex++] = cur;
            if (count > 1) {
                char[] countChars = String.valueOf(count).toCharArray();
                for (int idx = 0; idx < countChars.length; idx++) {
                    chars[idx + insIndex] = countChars[idx];
                }
                insIndex += countChars.length;
            }
            readIndex += count - 1;
        }
        return insIndex;
    }

    @Test
    void check1() {
        verify(new char[]{'a','a','b','b','c','c','c'}, 6, "a2b2c3");
    }

    @Test
    void check2() {
        verify(new char[]{'a'}, 1, "a");
    }

    @Test
    void check3() {
        verify(new char[]{'a','b','b','b','b','b','b','b','b','b','b','b','b'}, 4, "ab12");
    }

    private void verify(final char[] input, final int expectedLen, final String expectedOutput) {
        int count = compress(input);
        Assertions.assertEquals(expectedOutput, new String(input).substring(0, expectedLen));
        Assertions.assertEquals(expectedLen, count);
    }
}

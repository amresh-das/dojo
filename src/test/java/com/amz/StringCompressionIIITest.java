package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/string-compression-iii/?envType=daily-question&envId=2024-11-08"
 */
public class StringCompressionIIITest {
    
    public String compressedString(String word) {
        final StringBuilder out = new StringBuilder();
        char prev = word.charAt(0);
        int count = 1;
        for (int i = 1; i < word.length(); i++) {
            final char c = word.charAt(i);
            if (c == prev) {
                count++;
            } else {
                print(out, prev, count);
                prev = c;
                count = 1;
            }
        }
        print(out, prev, count);
        return out.toString();
    }

    private static void print(final StringBuilder out, final char c, int count) {
        if (count > 9) {
            out.append(String.format("9%c", c).repeat(count / 9));
            count = count % 9;
        }
        if (count > 0) {
            out.append(String.format("%d%c", count, c));
        }
    }

    @Test
    void check1() {
        Assertions.assertEquals("1a1b1c1d1e", compressedString("abcde"));
    }

    @Test
    void check2() {
        Assertions.assertEquals("9a5a2b", compressedString("aaaaaaaaaaaaaabb"));
    }

    @Test
    void check3() {
        Assertions.assertEquals("9a5a", compressedString("aaaaaaaaaaaaaa"));
    }
}

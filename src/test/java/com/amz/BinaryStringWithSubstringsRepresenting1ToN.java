package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/binary-string-with-substrings-representing-1-to-n/"
 */
public class BinaryStringWithSubstringsRepresenting1ToN {
    public boolean queryString(String s, int n) {
        for (int i = n; i > 0; i--) {
            if (!s.contains(Integer.toString(i, 2))) return false;
        }
        return true;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(true, queryString("0110", 3));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(false, queryString("0110", 4));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(false, queryString("110101011011000011011111000000", 15));
    }

    @Test
    public void check4() {
        Assertions.assertEquals(false, queryString("011010101010111101010101011111111111111111111111111111111110000000000000011111101010101001010101010101010101010101111010101010111111111111111111111111111111111100000000000000111111010101010010101010101010101010100"
                , 1000000000));
    }
}

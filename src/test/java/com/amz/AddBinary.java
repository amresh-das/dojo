package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/add-binary/"
 */
public class AddBinary {

    public String addBinary(String a, String b) {
        final int aLength = a.length();
        final int bLength = b.length();
        int len = Math.max(aLength, bLength);
        int carry = 0;
        final StringBuilder s = new StringBuilder();
        for (int i = 1; i <= len + 1; i++) {
            int aDigit = i <= aLength ? a.charAt(aLength - i) == '0' ? 0 : 1 : 0;
            int bDigit = i <= bLength ? b.charAt(bLength - i) == '0' ? 0 : 1 : 0;
            int result = carry + aDigit + bDigit;
            if (result > 1) {
                carry = 1;
                s.insert(0, result % 2);
            } else {
                carry = 0;
                s.insert(0, result % 2);
            }
        }
        if (s.charAt(0) == '0') {
            s.deleteCharAt(0);
        }
        return s.toString();
    }

    @Test
    void t1() {
        verify("11", "1", "100");
    }

    @Test
    void t2() {
        verify("1010", "1011", "10101");
    }

    @Test
    void t3() {
        verify("1111", "1111", "11110");
    }

    @Test
    void t4() {
        verify("100", "110010", "110110");
    }

    private void verify(final String a, final String b, final String expected) {
        Assertions.assertEquals(expected, addBinary(a, b));
    }
}

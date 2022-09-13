package com.amz.bit.manipulation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @see "https://leetcode.com/problems/utf-8-validation/"
 */
public class UTF8Validation {

    public boolean validUtf8(int... data) {
        int currentByteCount = 0;
        for (int i = 0; i < data.length; i++) {
            String byteVal = Integer.toBinaryString(data[i]);
            if (byteVal.length() >= 8) {
                byteVal = byteVal.substring(byteVal.length() - 8);
            } else {
                byteVal = Stream.generate(() -> "0").limit(8 - byteVal.length()).collect(Collectors.joining()) + byteVal;
            }
            if (currentByteCount == 0) {
                for (int j = 0; j < 8; j++) {
                    if (byteVal.charAt(j) == '1') {
                        currentByteCount++;
                    } else break;
                }
                if (currentByteCount == 0) continue;
                if (currentByteCount > 4 || currentByteCount == 1) return false;
            } else {
                if (!byteVal.startsWith("10")) return false;
            }
            currentByteCount--;
        }
        return currentByteCount == 0;
    }

    @Test
    public void check1() {
        Assertions.assertTrue(validUtf8(197,130,1));
    }

    @Test
    public void check2() {
        Assertions.assertFalse(validUtf8(235,140,4));
    }

}

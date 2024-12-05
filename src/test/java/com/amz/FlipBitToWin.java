package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "Cracking software coding interview - Bit Manipulation 5.3"
 */
public class FlipBitToWin {

    private static final int BYTES_IN_INTEGER = 4;
    private static final int BITS_IN_BYTE = 8;

    public int flipAndMaxCount(int input) {
        if (~input == 0) return BITS_IN_BYTE * BYTES_IN_INTEGER;
        int prevOnesLen = 0, currOnesLen = 0, maxLen = 0, zeroCount = 0;
        int n = input;
        while (n != 0) {
            if ((n & 1) == 1) {
                zeroCount = 0;
                currOnesLen++;
            }
            else {
                zeroCount++;
                if (zeroCount > 1) {
                    prevOnesLen = 0;
                } else {
                    prevOnesLen = currOnesLen;
                    currOnesLen = 0;
                }
            }
            maxLen = Math.max(maxLen, currOnesLen + prevOnesLen + 1);
            n >>= 1;
        }
        return maxLen;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(8, flipAndMaxCount(1775));
    }

}

package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @see "https://leetcode.com/problems/plus-one"
 */
public class PlusOne {

    public int[] plusOne(int[] digits) {
        int[] result = new int[digits.length + 1];
        int carry = 1;
        for (int i = result.length - 1; i >= 0; i--) {
            int digit = i == 0 ? 0 : digits[i - 1];
            int sum = carry + digit;
            if (sum == 10) {
                result[i] = 0;
                carry = 1;
            } else {
                result[i] = sum;
                carry = 0;
            }
        }
        return result[0] == 0 ? Arrays.copyOfRange(result, 1, result.length) : result;
    }

    @Test
    void t1() {
        verify("[1,2,3]", "[1,2,4]");
    }

    @Test
    void t2() {
        verify("[4,3,2,1]", "[4,3,2,2]");
    }

    @Test
    void t3() {
        verify("[0]", "[1]");
    }

    @Test
    void t4() {
        verify("[9]", "[1,0]");
    }

    private void verify(final String input, final String expected) {
        Assertions.assertEquals(expected, Utils.toString(plusOne(Utils.stringToIntArray(input))));
    }
}

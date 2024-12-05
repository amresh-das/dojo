package com.amz;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/divide-two-integers/"
 */
public class DivideTwoInts {

    @Test
    void t1() {
        verify(10, 3, 3);
    }

    @Test
    void t2() {
        verify(7, -3, -2);
    }

    @Test
    void t3() {
        int dividend = -2147483648;
        int divisor = -1;
        int expected = 2147483647;
        verify(dividend, divisor, expected);
    }

    @Test
    void t4() {
        verify(-2147483648, 1, -2147483648);
    }

    @Test
    void t5() {
        verify(1, -1, -1);
    }

    @Test
    void t6() {
        verify(-1, -1, 1);
    }

    @Test
    void t7() {
        verify(-1139973263, -1119586052, -1139973263/-1119586052);
    }

    private void verify(int dividend, int divisor, int expected) {
        Assertions.assertThat(divide(dividend, divisor)).as("%d / %d = %d", dividend, divisor, expected).isEqualTo(expected);
    }

    public int divide(int dividend, int divisor) {
        if (dividend == 0) return 0;
        if (dividend == divisor) return 1;
        if (divisor == 1) return dividend;
        if (divisor == -1) return dividend == Integer.MIN_VALUE ? Integer.MAX_VALUE : dividend < 0 ? dividend : -dividend;
        boolean negative = divisor < 0 && dividend >= 0 || divisor >= 0 && dividend < 0;
        long quotient = divide(Math.abs((long) dividend), Math.abs((long) divisor), negative);
        return quotient > Integer.MAX_VALUE ? Integer.MAX_VALUE : quotient < Integer.MIN_VALUE ? Integer.MIN_VALUE : (int) quotient;
    }

    private long divide(long dividend, long divisor, boolean negative) {
        if (dividend < divisor) return 0;
        int n = 1;
        long result = 0;
        do {
            divisor <<= 1;
            n++;
        } while (dividend > divisor);

        for (int i = 0; i < n; i++) {
            result <<= 1;
            if (dividend >= divisor) {
                dividend -= divisor;
                result |= 1;
            }
            divisor >>= 1;
        }
        return negative ? -result : result;
    }

}

package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/valid-number/"
 */
public class ValidNumber {
    enum STATE {
        EMPTY,
        SIGN_START,
        INTEGER,
        PROBABLE_DECIMAL,
        DECIMAL,
        PROBABLE_EXPONENT,
        SIGN_EXP,
        EXPONENTIAL,
        INVALID;

        STATE apply(final char next) {
            if (this == EMPTY && next == '+') return SIGN_START;
            if (this == EMPTY && next == '-') return SIGN_START;
            if (this == EMPTY && next == '.') return PROBABLE_DECIMAL;
            if (this == EMPTY && next >= '0' && next <= '9') return INTEGER;

            if (this == SIGN_START && next == '.') return PROBABLE_DECIMAL;
            if (this == SIGN_START && next >= '0' && next <= '9') return INTEGER;

            if (this == PROBABLE_DECIMAL && next >= '0' && next <= '9') return DECIMAL;

            if (this == INTEGER && next >= '0' && next <= '9') return INTEGER;
            if (this == INTEGER && next == '.') return DECIMAL;
            if (this == INTEGER && next == 'e') return PROBABLE_EXPONENT;

            if (this == DECIMAL && next >= '0' && next <= '9') return DECIMAL;
            if (this == DECIMAL && next == 'e') return PROBABLE_EXPONENT;

            if (this == PROBABLE_EXPONENT && next == '+') return SIGN_EXP;
            if (this == PROBABLE_EXPONENT && next == '-') return SIGN_EXP;
            if (this == PROBABLE_EXPONENT && next >= '0' && next <= '9') return EXPONENTIAL;

            if (this == SIGN_EXP && next >= '0' && next <= '9') return EXPONENTIAL;

            if (this == EXPONENTIAL && next >= '0' && next <= '9') return EXPONENTIAL;

            return INVALID;
        }
    }

    public boolean isNumber(String s) {
        STATE state = STATE.EMPTY;
        for (char c : s.toLowerCase().toCharArray()) {
            state = state.apply(c);
            if (state == STATE.INVALID) return false;
        }
        return state == STATE.INTEGER || state == STATE.DECIMAL || state == STATE.EXPONENTIAL;
    }

    @Test
    void t1() {
        final String input = "0";
        verify(input, true);
    }

    @Test
    void t2() {
        verify("e", false);
    }

    @Test
    void t3() {
        verify(".", false);
    }

    @Test
    void t4() {
        verify(".1", true);
    }

    @Test
    void t5() {
        verify("0e", false);
    }

    @Test
    void t6() {
        verify("3.", true);
    }

    @Test
    void t7() {
        verify("4e+", false);
    }

    @Test
    void t8() {
        verify("+.", false);
    }

    @Test
    void t9() {
        verify("-1.", true);
    }

    @Test
    void t10() {
        verify("-1", true);
    }

    @Test
    void t11() {
        verify("+.8", true);
    }

    @Test
    void t12() {
        verify(".0e7", true);
    }

    @Test
    void t13() {
        verify("46.e3", true);
    }

    private void verify(final String input, final boolean expected) {
        Assertions.assertEquals(expected, isNumber(input), input);
    }
}

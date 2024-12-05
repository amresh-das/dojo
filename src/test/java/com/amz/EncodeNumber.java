package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/encode-number/"
 */
public class EncodeNumber {

    public String encode(int num) {
        return Integer.toString(++num, 2).substring(1);
    }

    @Test
    public void check1() {
        Assertions.assertEquals("1000", encode(23));
    }

    @Test
    public void check2() {
        Assertions.assertEquals("101100", encode(107));
    }
}

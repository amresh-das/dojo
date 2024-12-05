package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/sqrtx"
 */
public class Sqrtx {
    public int mySqrt(int x) {
        int l = 1, h = x;
        while (l <= h) {
            int mid = l + (h - l) / 2;
            long sqr = (long) mid * mid;
            if (sqr == x) return mid;
            if (sqr > x) {
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return h;
    }

    @Test
    void t1() {
        Assertions.assertEquals(2, mySqrt(4));
    }

    @Test
    void t2() {
        Assertions.assertEquals(2, mySqrt(8));
    }
}

package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/powx-n/"
 */
public class PowXN {
    public double myPow(double x, int n) {
        return pow(x, n);
    }

    public double pow(double x, long n) {
        if (n == 0) return 1.0;
        if (x == 1) return 1.0;
        if (n > 0) {
            if (n % 2 == 0) {
                return pow(x * x, n / 2L);
            } else {
                return x * pow(x , n - 1L);
            }
        } else {
            return 1.0 / pow(x, n * -1L);
        }
    }

    @Test
    void t1() {
        Assertions.assertEquals(1024.00000, myPow(2.0d, 10), 0.001);
    }

    @Test
    void t2() {
        Assertions.assertEquals(9.26100, myPow(2.1d, 3), 0.001);
    }

    @Test
    void t3() {
        Assertions.assertEquals(0.25000, myPow(2.0d, -2), 0.001);
    }

    @Test
    void t4() {
        Assertions.assertEquals(1.0, myPow(1.0d, -2147483648), 0.001);
    }

    @Test
    void t5() {
        Assertions.assertEquals(0.0, myPow(2.0d, -2147483648), 0.001);
    }
}

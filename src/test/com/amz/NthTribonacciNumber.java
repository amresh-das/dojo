package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/n-th-tribonacci-number/"
 */
public class NthTribonacciNumber {

    public int tribonacci(int n) {
        final int[] tribonacci = new int[Math.max(3, n + 1)];
        tribonacci[0] = 0;
        tribonacci[1] = 1;
        tribonacci[2] = 1;
        for (int i = 3; i <= n; i++) {
            tribonacci[i] = tribonacci[i - 3] + tribonacci[i - 2] + tribonacci[i - 1];
        }
        return tribonacci[n];
    }

    @Test
    public void t1() {
        Assertions.assertEquals(4, tribonacci(4));
    }

}

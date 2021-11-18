package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/fibonacci-number"
 */
public class FibonacciNumber {

    public int fib(int n) {
        return n == 1 ? 1 : n > 1 ? fib(n - 2) + fib(n - 1) : 0;
    }

    @Test
    public void t1() {
        Assertions.assertEquals(0, fib(0));
    }

    @Test
    public void t2() {
        Assertions.assertEquals(1, fib(1));
    }

    @Test
    public void t3() {
        Assertions.assertEquals(1, fib(2));
    }

    @Test
    public void t4() {
        Assertions.assertEquals(2, fib(3));
    }

    @Test
    public void t5() {
        Assertions.assertEquals(3, fib(4));
    }

}

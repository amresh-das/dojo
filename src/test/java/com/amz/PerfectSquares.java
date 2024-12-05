package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @see "https://leetcode.com/problems/perfect-squares/"
 */
public class PerfectSquares {

    public int numSquares(int n) {
        if (n < 4) return n;
        return getMinCount(n, 0, new HashMap<>());
    }

    private int getMinCount(final int n, final int count, final Map<Integer, Integer> memo) {
        if (n == 0) {
            return count;
        }
        int min = Integer.MAX_VALUE;
        for (int x = (int) Math.sqrt(n); x > 0; x--) {
            int sqr = x * x;
            int minCount = getMinCount(n % sqr, count + n / sqr, memo);
            min = Math.min(minCount, min);
        }
        return min;
    }

    @Test
    public void check0() {
        Assertions.assertEquals(1, numSquares(1));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(2, numSquares(2));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(3, numSquares(3));
    }

    @Test
    public void check4() {
        Assertions.assertEquals(1, numSquares(4));
    }

    @Test
    public void check5() {
        Assertions.assertEquals(2, numSquares(5));
    }

    @Test
    public void check6() {
        Assertions.assertEquals(3, numSquares(12));
    }

    @Test
    public void check7() {
        Assertions.assertEquals(2, numSquares(13));
    }

    @Test
    public void check8() {
        Assertions.assertEquals(3, numSquares(5673));
    }

    @Test
    public void check9() {
        Assertions.assertEquals(2, numSquares(17));
    }

    @Test
    public void check10() {
        Assertions.assertEquals(2, numSquares(6730));
    }

    @Test
    public void check11() {
        Assertions.assertEquals(2, numSquares(13));
    }
}

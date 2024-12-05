package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * //TODO
 * @see "https://leetcode.com/problems/digit-count-in-range/"
 */
public class DigitCountInRange {
    public int digitsCount(int d, int low, int high) {
        return digitsCount(d, high) - digitsCount(d, low - 1);
    }

    public int digitsCount(int d, int num) {
        if (num == 0) return d == 0 ? 1 : 0;
        List<Integer> digitCounts = new ArrayList<>();
        int n = num;
        int product = 1;
        while (n > 0) {
            int x = n % 10;
            digitCounts.add(x + 1);
            product *= x + 1;
            n = n / 10;
        }
        Collections.reverse(digitCounts);
        final int productVal = product;
        System.out.println(digitCounts);
        System.out.println(productVal);
        return digitCounts.stream().map(x -> productVal / x + (d < x ? 1 : 0)).reduce(0, Integer::sum);
    }

    @Test
    public void check1() {
        Assertions.assertEquals(6, digitsCount(1, 1, 13));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(35, digitsCount(3, 100, 250));
    }

}

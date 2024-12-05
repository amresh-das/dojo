package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/subtract-the-product-and-sum-of-digits-of-an-integer"
 */
public class SubtractProductSumOfInt {

    @Test
    void t1() {
        Assertions.assertEquals(15, subtractProductAndSum(234));
    }

    @Test
    void t2() {
        Assertions.assertEquals(21, subtractProductAndSum(4421));
    }

    public int subtractProductAndSum(int n) {
        int temp = n;
        int sum = 0;
        int product = 1;
        while (temp != 0) {
            int digit = temp % 10;
            sum += digit;
            product *= digit;
            temp = temp / 10;
        }
        return product - sum;
    }
}

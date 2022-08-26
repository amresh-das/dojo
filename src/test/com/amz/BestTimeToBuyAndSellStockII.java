package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/"
 */
public class BestTimeToBuyAndSellStockII {

    public int maxProfit(int... prices) {
        long profit = 0;
        long prev = Integer.MAX_VALUE;
        for (int p : prices) {
            if (p > prev) {
                profit += Math.max(0, p - prev);
            }
            prev = p;
        }
        return (int) profit;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(7, maxProfit(7,1,5,3,6,4));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(4, maxProfit(1,2,3,4,5));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(0, maxProfit(7,6,4,3,1));
    }

}

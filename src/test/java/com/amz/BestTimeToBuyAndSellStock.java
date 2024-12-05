package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/best-time-to-buy-and-sell-stock/"
 */
public class BestTimeToBuyAndSellStock {

    public int maxProfit(int... prices) {
        long currentMin = Integer.MAX_VALUE;
        long currentMax = -currentMin;
        long maxProfit = 0;
        for (int price : prices) {
            if (price <= currentMin) {
                maxProfit = Math.max(maxProfit, currentMax - currentMin);
                currentMin = price;
                currentMax = price;
            }
            currentMax = Math.max(currentMax, price);
        }
        maxProfit = Math.max(maxProfit, currentMax - currentMin);
        return (int) maxProfit;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(5, maxProfit(7,1,5,3,6,4));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(0, maxProfit(7,6,4,3,1));
    }

}

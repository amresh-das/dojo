package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/poor-pigs/"
 */
public class PoorPigs {

    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int maxRetries = minutesToTest / minutesToDie + 1;
        return (int) Math.ceil(Math.log(buckets) / Math.log(maxRetries));
    }

    @Test
    public void check1() {
        Assertions.assertEquals(5, poorPigs(1000, 15, 60));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(2, poorPigs(4, 15, 15));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(2, poorPigs(4, 15, 30));
    }

}

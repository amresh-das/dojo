package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/find-the-highest-altitude/description/?envType=study-plan-v2&envId=leetcode-75"
 */
public class FindTheHighestAltitudeTest {

    public int largestAltitude(int[] gain) {
        int max = 0;
        int alt = 0;
        for (int g : gain) {
            alt += g;
            if (alt > max) max = alt;
        }
        return max;
    }

    @Test
    void check1() {
        Assertions.assertEquals(1, largestAltitude(new int[] {-5,1,5,0,-7}));
    }

    @Test
    void check2() {
        Assertions.assertEquals(0, largestAltitude(new int[] {-4,-3,-2,-1,4,3,2}));
    }
}

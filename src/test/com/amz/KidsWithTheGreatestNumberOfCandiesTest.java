package com.amz;

import java.util.Arrays;
import java.util.List;

/**
 * @see "https://leetcode.com/problems/kids-with-the-greatest-number-of-candies/?envType=study-plan-v2&envId=leetcode-75"
 */
public class KidsWithTheGreatestNumberOfCandiesTest {

    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        Boolean[] result = new Boolean[candies.length];
        int max = candies[0];
        for (int i = 1; i < candies.length; i++) {
            max = candies[i] > max ? candies[i] : max;
        }
        for (int i = 0; i < candies.length; i++) {
            result[i] = candies[i] + extraCandies >= max;
        }
        return Arrays.asList(result);
    }

}

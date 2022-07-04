package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @see "https://leetcode.com/problems/candy/"
 */
public class Candy {

    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];
        Arrays.fill(candies, 1);
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }
        int totalCandies = candies[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
            totalCandies += candies[i];
        }
        return totalCandies;
    }

    private List<Integer> findIndexOfMin(final int[] ratings) {
        int min = 99999;
        final List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < ratings.length; i++) {
            if (ratings[i] < min) {
                min = ratings[i];
                indices.clear();
                indices.add(i);
            } else if (ratings[i] == min) {
                indices.add(i);
            }
        }
        return indices;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(5, candy(Utils.stringToIntArray("[1,0,2]")));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(4, candy(Utils.stringToIntArray("[1,2,2]")));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(15, candy(Utils.stringToIntArray("[1,2,0,3,4,0,1,3]")));
    }

    @Test
    public void check4() {
        Assertions.assertEquals(7, candy(Utils.stringToIntArray("[1,3,2,2,1]"))); //[1,3,2,2,1]
    }
}

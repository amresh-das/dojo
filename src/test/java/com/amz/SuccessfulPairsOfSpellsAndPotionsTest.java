package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @see "https://leetcode.com/problems/successful-pairs-of-spells-and-potions/description/?envType=study-plan-v2&envId=leetcode-75"
 */
public class SuccessfulPairsOfSpellsAndPotionsTest {

    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int[] result = new int[spells.length];
        Arrays.sort(potions);
        Map<Integer, Integer> cache = new HashMap<>();
        long[] potionsToSearch = new long[potions.length];
        for (int i = 0; i < potions.length; i++) {
            potionsToSearch[i] = potions[i];
        }
        for (int i = 0; i < spells.length; i++) {
            var s = spells[i];
            long reqPotions = success / s + (success % s == 0 ? 0 : 1);
            result[i] = cache.computeIfAbsent(s, k -> {
                int idx = Arrays.binarySearch(potionsToSearch, reqPotions);
                if (idx >= 0) {
                    while (idx > 0 && potionsToSearch[idx - 1] == reqPotions) idx--;
                } else if (-idx > potions.length) {
                    return 0;
                } else {
                    idx = -idx - 1;
                }
                return potions.length - idx;
            });
        }
        return result;
    }

    @Test
    void t1() {
        Assertions.assertArrayEquals(new int[] {4,0,3}, successfulPairs(new int[] {5,1,3}, new int[] {1,2,3,4,5}, 7));
    }

    @Test
    void t2() {
        Assertions.assertArrayEquals(new int[] {2,0,2}, successfulPairs(new int[] {3,1,2}, new int[] {8,5,8}, 16));
    }

    @Test
    void t3() {
        final int[] spells = {36,36,22,11,35,21,4,25,30,35,31,10,8,39,7,22,18,9,23,30,9,37,22,7,36,40,17,37,38,27,6,15,1,15,7,31,36,29,9,15,3,37,15,17,25,35,9,21,5,17,25,8,18,25,7,19,4,33,9,5,29,13,9,18,5,10,31,6,7,24,13,11,8,19,2};
        final int[] potions = {5, 11, 11, 11, 17, 17, 17, 17, 18, 18, 19, 19, 19, 20, 20, 20, 22, 22, 22, 22, 22, 23, 24, 24, 25, 25, 27, 27, 28, 28, 28, 28, 29, 29, 29, 30, 31, 32, 32, 32, 33, 33, 33, 34, 34, 35, 35, 35, 36, 36, 36, 36, 36, 36, 37, 37, 37, 37, 38, 38, 38, 39, 39, 39, 40, 40, 40, 40, 40, 40, 40, 40};
        final int success = 135;
        final int[] expected = {72, 72, 71, 68, 72, 71, 29, 71, 72, 72, 72, 68, 68, 72, 59, 71, 71, 68, 71, 72, 68, 72, 71, 59, 72, 72, 71, 72, 72, 72, 51, 71, 0, 71, 59, 72, 72, 72, 68, 71, 0, 72, 71, 71, 71, 72, 68, 71, 46, 71, 71, 68, 71, 71, 59, 71, 29, 72, 68, 46, 72, 71, 68, 71, 46, 68, 72, 51, 59, 71, 71, 68, 68, 71, 0};
        Assertions.assertArrayEquals(expected, successfulPairs(spells, potions, success));
    }
}

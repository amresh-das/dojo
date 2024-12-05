package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @see "https://leetcode.com/problems/unique-number-of-occurrences/description/?envType=study-plan-v2&envId=leetcode-75"
 */
public class UniqueNumberOfOccurrencesTest {

    public boolean uniqueOccurrences(int[] arr) {
        if (arr.length == 1) return true;
        Arrays.sort(arr);
        int count = 0;
        int[] counts = new int[2000];
        int prev = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (prev == arr[i]) {
                count++;
            } else {
                if (counts[count] != 0) return false;
                counts[count] = prev + 1001;
                prev = arr[i];
                count = 1;
            }
        }
        return counts[count] == 0;
    }

    public boolean uniqueOccurrences2(int[] arr) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num: arr) {
            counts.compute(num, (k, v) -> v == null ? 1 : v + 1);
        }
        return counts.keySet().stream().map(counts::get).distinct().count() == counts.size();
    }

    @Test
    void check1() {
        Assertions.assertTrue(uniqueOccurrences(new int[] {1,2,2,1,1,3}));
    }

    @Test
    void check2() {
        Assertions.assertFalse(uniqueOccurrences(new int[] {1,2}));
    }

    @Test
    void check3() {
        Assertions.assertTrue(uniqueOccurrences(new int[] {-3,0,1,-3,1,1,1,-3,10,0}));
    }

    @Test
    void check4() {
        Assertions.assertFalse(uniqueOccurrences(new int[] {1, 2, 2, 0, 0}));
    }
}

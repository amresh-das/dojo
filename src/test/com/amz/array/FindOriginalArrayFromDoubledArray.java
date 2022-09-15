package com.amz.array;

import com.amz.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * @see "https://leetcode.com/problems/find-original-array-from-doubled-array/"
 */
public class FindOriginalArrayFromDoubledArray {
    public int[] findOriginalArray(int[] changed) {
        if (changed.length % 2 == 1) return new int[0];
        int max = Integer.MIN_VALUE;
        for (int n : changed) {
            if (n > max) max = n;
        }
        int[] freq = new int[max * 2 + 1];
        for (int n : changed) {
            freq[n]++;
        }
        int[] original = new int[changed.length / 2];
        int index = 0;
        for (int i = 0; i < freq.length; i++) {
            while (freq[i] > 0) {
                if (freq[i * 2] == 0) return new int[0];
                freq[i]--;
                freq[i * 2]--;
                original[index++] = i;
            }
        }
        return original;
    }

    public int[] findOriginalArrayWithMap(int[] changed) {
        if (changed.length % 2 == 1) return new int[0];
        int[] original = new int[changed.length / 2];
        int index = 0;
        Map<Integer, Integer> counts = new TreeMap<>();
        for (int x : changed) {
            counts.compute(x, (k, v) -> v == null ? 1 : v + 1);
        }

        for (int n : new ArrayList<>(counts.keySet())) {
            while (counts.get(n) > 0) {
                if (counts.getOrDefault(n * 2, 0) > 0) {
                    original[index++] = n;
                    counts.compute(n, (k, v) -> v == null ? 0 : v - 1);
                    counts.compute(n * 2, (k, v) -> v == null ? 0 : v - 1);
                } else {
                    return new int[0];
                }
            }
        }
        return original;
    }

    @Test
    public void check1() {
        Assertions.assertEquals("[1,3,4]", Utils.toString(findOriginalArray(Utils.stringToIntArray("[1,3,4,2,6,8]"))));
    }

    @Test
    public void check2() {
        Assertions.assertEquals("[]", Utils.toString(findOriginalArray(Utils.stringToIntArray("[6,3,0,1]"))));
    }

    @Test
    public void check3() {
        Assertions.assertEquals("[]", Utils.toString(findOriginalArray(Utils.stringToIntArray("[1]"))));
    }

}

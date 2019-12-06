package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TwoSumLessThanK {

    @Test
    void t1() {
        int[] input = new int[] {34,23,1,24,75,33,54,8};
        int limit = 60;
        int output = twoSumLessThanK(input, limit);
        Assertions.assertEquals(58, output);
    }

    @Test
    void t2() {
        int[] input = new int[] {10,20,30};
        int limit = 15;
        int output = twoSumLessThanK(input, limit);
        Assertions.assertEquals(-1, output);
    }

    public int twoSumLessThanK(int[] A, int K) {
        int diff = Integer.MAX_VALUE;
        boolean found = false;
        for (int i = 0; i < A.length - 1; i++) {
            for (int j = i + 1; j < A.length; j++) {
                int newDiff = K - A[i] - A[j];
                if (newDiff > 0 && newDiff < diff) {
                    found = true;
                    diff = newDiff;
                }
            }
        }
        return found ? K - diff : -1;
    }
}

package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @see "https://leetcode.com/problems/numbers-with-same-consecutive-differences/"
 */
public class NumbersWithSameConsecutiveDifferences {

    public int[] numsSameConsecDiff(int n, int k) {
        final List<Integer> ans = new ArrayList<>();
        if (n != 0) {
            final PriorityQueue<String> pq = new PriorityQueue<>(Comparator.comparingInt(String::length));
            for (char c = '1'; c <= '9'; c++) {
                pq.offer("" + c);
            }
            while (!pq.isEmpty()) {
                String num = pq.poll();
                int size = num.length();
                if (size == n) {
                    ans.add(Integer.parseInt(num));
                } else {
                    int lastDigit = num.charAt(size - 1) - '0';
                    int optA = lastDigit + k;
                    if (optA < 10) {
                        pq.offer(num + optA);
                    }
                    if (k > 0) {
                        int optB = lastDigit - k;
                        if (optB >= 0) {
                            pq.offer(num + optB);
                        }
                    }
                }
            }
        }
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    @Test
    public void check1() {
        verify(3, 7, 181,292,707,818,929);
    }

    @Test
    public void check2() {
        verify(2, 1, 10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98);
    }

    @Test
    public void check3() {
        verify(2, 0, 11,22,33,44,55,66,77,88,99);
    }

    private void verify(int n, int k, int... expected) {
        Assertions.assertEquals(Arrays.stream(expected).boxed().collect(Collectors.toSet()), Arrays.stream(numsSameConsecDiff(n, k)).boxed().collect(Collectors.toSet()));
    }

}

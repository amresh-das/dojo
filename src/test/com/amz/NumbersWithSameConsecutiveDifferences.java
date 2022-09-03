package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @see "https://leetcode.com/problems/numbers-with-same-consecutive-differences/"
 */
public class NumbersWithSameConsecutiveDifferences {

    public int[] numsSameConsecDiff(int n, int k) {
        List<Integer> ans = new ArrayList<>();
        if (n != 0) {
            final PriorityQueue<Object[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> (int) o[0]));
            for (Integer i = 1; i < 10; i++) {
                pq.offer(new Object[]{1, i.toString(), i});
            }
            while (!pq.isEmpty()) {
                Object[] obj = pq.poll();
                int digitCount = (int) obj[0];
                String num = (String) obj[1];
                if (digitCount == n) {
                    ans.add(Integer.parseInt(num));
                } else {
                    int lastDigit = (int) obj[2];
                    int optA = lastDigit + k;
                    if (optA < 10) {
                        pq.offer(new Object[]{digitCount + 1, num + optA, optA});
                    }
                    if (k > 0) {
                        int optB = lastDigit - k;
                        if (optB >= 0) {
                            pq.offer(new Object[]{digitCount + 1, num + optB, optB});
                        }
                    }
                }
            }
        }
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    @Test
    public void check1() {
        Assertions.assertEquals("[181,292,707,818,929]", Utils.toString(numsSameConsecDiff(3, 7)));
    }

    @Test
    public void check2() {
        Assertions.assertEquals("[10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]", Utils.toString(numsSameConsecDiff(2, 1)));
    }

    @Test
    public void check3() {
        Assertions.assertEquals("[11,22,33,44,55,66,77,88,99]", Utils.toString(numsSameConsecDiff(2, 0)));
    }

}

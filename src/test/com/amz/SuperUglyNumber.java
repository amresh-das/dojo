package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * TODO
 * @see "https://leetcode.com/problems/super-ugly-number/"
 */
public class SuperUglyNumber {

    public int nthSuperUglyNumber(int n, int[] primes) {
        if (n == 1) return 1;
        final SortedSet<Integer> numbers = new TreeSet<>();
        for (int p : primes) {
            numbers.add(p);
        }
        while (numbers.size() < n) {
            final Set<Integer> next = new HashSet<>();
            for (int x : numbers) {
                for (int p : primes) {
                    next.add(p * x);
                }
            }
            numbers.addAll(next);
        }
        System.out.println(numbers);
        return numbers.stream().skip(n - 2L).findFirst().get();
    }

    @Test
    public void check1() {
        Assertions.assertEquals(32, nthSuperUglyNumber(12, Utils.stringToIntArray("[2,7,13,19]")));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(1, nthSuperUglyNumber(1, Utils.stringToIntArray("[2,3,5]")));
    }

}

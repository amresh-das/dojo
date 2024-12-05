package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @see "https://leetcode.com/problems/permutation-sequence/"
 */
public class PermutationSequence {

    public String getPermutation(int n, int k) {
        final int[] factorial = getFactorials(n);
        final List<Character> digits = getInitial(n);
        final StringBuilder s = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            int blockSize = factorial[n - i];
            int index = (k - 1) / blockSize;
            s.append(getDigit(digits, index));
            k -= index * blockSize;
        }

        return s.toString();
    }

    private char getDigit(final List<Character> digits, final int index) {
        char curr = digits.get(index);
        digits.remove(index);
        return curr;
    }

    private List<Character> getInitial(final int n) {
        final List<Character> chars = new ArrayList<>();
        for (char i = 1; i <= n; i++) {
            chars.add((char) ('0' + i));
        }
        return chars;
    }

    private int[] getFactorials(final int n) {
        final int[] factorials = new int[n];
        factorials[0] = 1;
        for (int i = 1; i < n; i++) {
            factorials[i] = factorials[i - 1] * i;
        }
        return factorials;
    }

    public String getPermutation1(int n, int k) {
        return permute("123456789".substring(0, n), k).get(k - 1);
    }

    private List<String> permute(final String input, final int k) {
        final List<String> solutions = new ArrayList<>();
        if (input.length() < 1) {
            solutions.add(input);
        } else {
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                final String remaining = input.substring(0, i) + input.substring(i + 1);
                for (final String p : permute(remaining, k)) {
                    solutions.add(c + p);
                    if (solutions.size() >= k) break;
                }
            }
        }
        return solutions;
    }

    @Test
    void t1() {
        Assertions.assertEquals("213", getPermutation(3, 3));
    }

    @Test
    void t2() {
        Assertions.assertEquals("2314", getPermutation(4, 9));
    }

    @Test
    void t3() {
        Assertions.assertEquals("123", getPermutation(3, 1));
    }

    @Test
    void t4() {
        Assertions.assertEquals("24186735", getPermutation(8, 6593));
    }
}

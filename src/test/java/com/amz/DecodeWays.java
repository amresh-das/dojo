package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @see "https://leetcode.com/problems/decode-ways/"
 */
public class DecodeWays {
    private static final Map<String, Character> TO_CHAR = new HashMap<>();
    {
        for (char c = 'A'; c <= 'Z'; c++) {
            TO_CHAR.put(Integer.toString(c - 'A' + 1), c);
        }
    }

    public int numDecodings(String s) {
        return countNums(s, 0, new HashMap<>());
    }

    private int countNums(String str, int position, final Map<Integer, Integer> memo) {
        if (position == str.length()) {
            memo.put(position, 1);
            return 1;
        }
        if (memo.containsKey(position)) return memo.get(position);
        char ch = str.charAt(position);
        if (TO_CHAR.containsKey(str.substring(position, position + 1))) {
            int n = countNums(str, position + 1, memo);
            if (position < str.length() - 1) {
                String x = "" + ch + str.charAt(position + 1);
                if (TO_CHAR.containsKey(x)) {
                    n += countNums(str, position + 2, memo);
                }
            }
            memo.put(position, n);
            return n;
        } else {
            memo.put(position, 0);
            return 0;
        }
    }

    @Test
    public void check1() {
        Assertions.assertEquals(2, numDecodings("12"));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(3, numDecodings("226"));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(0, numDecodings("06"));
    }

    @Test
    public void check4() {
        Assertions.assertEquals(1836311903, numDecodings("111111111111111111111111111111111111111111111"));
    }
}

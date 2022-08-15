package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @see "https://leetcode.com/problems/roman-to-integer/"
 */
public class RomanToInteger {
    private final static Map<String, Integer> DIGITS = new HashMap<>();
    {
        DIGITS.put("I", 1);
        DIGITS.put("IV", 4);
        DIGITS.put("V", 5);
        DIGITS.put("IX", 9);
        DIGITS.put("X", 10);
        DIGITS.put("XL", 40);
        DIGITS.put("L", 50);
        DIGITS.put("XC", 90);
        DIGITS.put("C", 100);
        DIGITS.put("CD", 400);
        DIGITS.put("D", 500);
        DIGITS.put("CM", 900);
        DIGITS.put("M", 1000);
    }
    public int romanToInt(String s) {
        int num = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            String c = s.substring(i, i + 1);
            if (i > 0) {
                String x = s.substring(i - 1, i + 1);
                if (DIGITS.containsKey(x)) {
                    num += DIGITS.get(x);
                    i--;
                    continue;
                }
            }
            num += DIGITS.get(c);
        }
        return num;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(3, romanToInt("III"));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(58, romanToInt("LVIII"));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(1994, romanToInt("MCMXCIV"));
    }
}

package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @see "https://leetcode.com/problems/strobogrammatic-number/"
 */
public class StrobogrammaticNumber {

    Map<Character, Character> strobograms = new HashMap<>();
    {
        strobograms.put('0', '0');
        strobograms.put('1', '1');
        strobograms.put('8', '8');
        strobograms.put('6', '9');
        strobograms.put('9', '6');
    }

    public boolean isStrobogrammatic(String num) {
        char[] digits = num.toCharArray();
        String strobogram = "";
        for (int i = digits.length - 1; i >= 0; i--) {
            if (!strobograms.containsKey(digits[i])) return false;
            strobogram += strobograms.get(digits[i]);
        }
        return num.equals(strobogram);
    }

    @Test
    public void check1() {
        Assertions.assertTrue(isStrobogrammatic("69"));
    }

    @Test
    public void check2() {
        Assertions.assertTrue(isStrobogrammatic("88"));
    }

    @Test
    public void check3() {
        Assertions.assertFalse(isStrobogrammatic("962"));
    }
}

package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/description/?envType=study-plan-v2&envId=leetcode-75"
 */
public class MaximumNumberOfVowelsInAStringOfGivenLengthTest {

    public int maxVowels(String s, int k) {
        if (k == 0) return 0;
        Integer maxVowels = null;
        int windowVowels = 0;
        for (int i = 0; i < s.length(); i++) {
            final char c = s.charAt(i);
            if ((c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')) {
                windowVowels++;
            }
            if (i >= k) {
                final char l = s.charAt(i - k);
                if ((l == 'a' || l == 'e' || l == 'i' || l == 'o' || l == 'u')) {
                    windowVowels--;
                }
            }
            if (i >= k - 1) {
                maxVowels = maxVowels == null ? windowVowels : Math.max(maxVowels, windowVowels);
            }
        }
        return maxVowels;
    }

    @Test
    void check1() {
        Assertions.assertEquals(3, maxVowels("abciiidef", 3));
    }

    @Test
    void check2() {
        Assertions.assertEquals(2, maxVowels("aeiou", 2));
    }

    @Test
    void check3() {
        Assertions.assertEquals(2, maxVowels("leetcode", 3));
    }
}

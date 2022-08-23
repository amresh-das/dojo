package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/"
 */
public class MaximumNumberOfVowelsInASubstringOfGivenLength {

    public int maxVowels(String s, int k) {
        int[] vowels = new int[s.length() + 1];
        vowels[0] = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            vowels[i + 1] = ((c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') ? 1 : 0) + vowels[i];
        }
        int max = 0;
        for (int i = 0; i <= s.length() - k; i++) {
            max = Math.max(max, vowels[i + k] - vowels[i]);
        }
        return max;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(3, maxVowels("abciiidef", 3));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(2, maxVowels("aeiou", 2));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(2, maxVowels("leetcode", 3));
    }

}

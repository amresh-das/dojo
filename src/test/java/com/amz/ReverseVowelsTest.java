package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/reverse-vowels-of-a-string/description/?envType=study-plan-v2&envId=leetcode-75"
 */
public class ReverseVowelsTest {

    @Test
    public void check1() {
        Assertions.assertEquals("leotcede", reverseVowels("leetcode"));
    }

    public String reverseVowels(String s) {
        char[] c = s.toCharArray();
        int start = 0, end = s.length() - 1;
        while (start < end) {
            while (start < s.length() && !isVowel(c[start])) {
                start++;
            }
            while (end >= 0 && !isVowel(c[end])) {
                end--;
            }
            if (start < end && isVowel(c[start]) && isVowel(c[end])) {
                char t = c[start];
                c[start] = c[end];
                c[end] = t;
            }
            start++;
            end--;
        }
        return new String(c);
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
                || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }
}

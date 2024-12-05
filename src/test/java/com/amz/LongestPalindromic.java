package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/longest-palindromic-substring/"
 */
public class LongestPalindromic {

    @Test
    void t1() {
        Assertions.assertEquals("bab", longestPalindrome("babad"));
    }

    @Test
    void t2() {
        Assertions.assertEquals("bb", longestPalindrome("cbbd"));
    }

    public String longestPalindrome(String s) {
        if (s == null) return "";
        final int length = s.length();
        Boolean[][] intermediate = new Boolean[length + 1][length + 1];
        for (int subLength = length; subLength > 0; subLength--) {
            for (int subStart = 0; subStart <= length - subLength; subStart++) {
                if (intermediate[subStart][subLength] == null) {
                    boolean isOdd = subLength % 2 == 1;
                    int mid = subLength / 2;
                    int i = isOdd ? mid : mid - 1;
                    int j = mid;
                    while (i >= 0) {
                        if (s.charAt(subStart + i--) == s.charAt(subStart + j++)) {
                            intermediate[subStart][subLength] = true;
                        } else {
                            intermediate[subStart][subLength] = false;
                            break;
                        }
                    }
                }
                if (intermediate[subStart][subLength] != null && intermediate[subStart][subLength]) {
                    return s.substring(subStart, subStart + subLength);
                } else {
                    intermediate[subStart][subLength] = false;
                }
            }
        }
        return "";
    }

}

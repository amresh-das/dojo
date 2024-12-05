package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/longest-substring-without-repeating-characters/"
 */
public class LongestSubstringWithoutRepeats {

    @Test
    void t1() {
        Assertions.assertEquals(3, lengthOfLongestSubstring("abcabcbb"));
    }

    @Test
    void t2() {
        Assertions.assertEquals(1, lengthOfLongestSubstring("bbbbb"));
    }

    @Test
    void t3() {
        Assertions.assertEquals(3, lengthOfLongestSubstring("pwwkew"));
    }

    @Test
    void t4() {
        Assertions.assertEquals(1, lengthOfLongestSubstring(" "));
    }

    @Test
    void t5() {
        Assertions.assertEquals(2, lengthOfLongestSubstring("abba"));
    }

    @Test
    void t6() {
        Assertions.assertEquals(3, lengthOfLongestSubstring("dvdf"));
    }

    @Test
    void t7() {
        Assertions.assertEquals(3, lengthOfLongestSubstring("abcb"));
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) return 0;
        int result = 0;
        int index = -1;
        int start = 0;
        while (++index < s.length()) {
            char c = s.charAt(index);
            int prevIndex = s.indexOf(c, start);
            if (prevIndex != index) {
                result = Math.max(result, index - start);
                start = prevIndex + 1;
            }
        }
        return Math.max(result, index - start);
    }

}

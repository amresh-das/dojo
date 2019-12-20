package com.amz;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/implement-strstr/"
 */
public class StrStr {

    @Test
    void t1() {
        Assertions.assertThat(strStr("hello", "ll")).isEqualTo(2);
    }

    @Test
    void t2() {
        Assertions.assertThat(strStr("aaaaa", "bba")).isEqualTo(-1);
    }

    @Test
    void t3() {
        Assertions.assertThat(strStr("aaaaabb", "bba")).isEqualTo(-1);
    }

    @Test
    void t4() {
        Assertions.assertThat(strStr("a", "a")).isEqualTo(0);
    }

    @Test
    void t5() {
        Assertions.assertThat(strStr("mississippi", "pi")).isEqualTo(9);
    }

    public int strStr(String haystack, String needle) {
        if (needle == null || needle.isEmpty()) return 0;
        if (haystack.length() == needle.length()) return haystack.equals(needle) ? 0 : -1;
        for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
            if(haystack.charAt(i) == needle.charAt(0)) {
                boolean isFound = true;
                for (int n = 1; n < needle.length(); n++) {
                    if (haystack.charAt(i + n) != needle.charAt(n)) {
                        isFound = false;
                        break;
                    }
                }
                if (isFound) return i;
            }
        }
        return -1;
    }
}

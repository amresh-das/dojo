package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FirstUniqueChar {

    @Test
    void t1() {
        Assertions.assertEquals(0, firstUniqChar("leetcode"));
    }

    @Test
    void t2() {
        Assertions.assertEquals(2, firstUniqChar("loveleetcode"));
    }

    @Test
    void t3() {
        Assertions.assertEquals(-1, firstUniqChar("cc"));
    }

    public int firstUniqChar(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (s.lastIndexOf(c) == s.indexOf(c)) {
                return i;
            }
        }
        return -1;
    }
}

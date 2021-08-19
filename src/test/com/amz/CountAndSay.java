package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CountAndSay {

    public String countAndSay(int n) {
        if (n < 1) return "";
        if (n == 1) return "1";
        return say(countAndSay(n - 1));
    }

    private String say(final String term) {
        final StringBuilder txt = new StringBuilder();
        char prev = term.charAt(0);
        int streak = 1;
        for (int i = 1; i < term.length(); i++) {
            char curr = term.charAt(i);
            if (prev == curr) {
                streak++;
            } else {
                txt.append(streak);
                txt.append(prev);
                prev = curr;
                streak = 1;
            }
        }
        txt.append(streak);
        txt.append(prev);
        return txt.toString();
    }

    @Test
    void t1() {
        Assertions.assertEquals("1", countAndSay(1));
    }

    @Test
    void t2() {
        Assertions.assertEquals("11", countAndSay(2));
    }

    @Test
    void t3() {
        Assertions.assertEquals("21", countAndSay(3));
    }

    @Test
    void t4() {
        Assertions.assertEquals("1211", countAndSay(4));
    }
}

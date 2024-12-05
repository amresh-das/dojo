package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/latest-time-by-replacing-hidden-digits/"
 */
public class LatestTimeByReplacingHiddenDigits {

    public String maximumTime(String time) {
        char[] maxTime = time.toCharArray();

        if (maxTime[0] == '?') {
            if (maxTime[1] == '?' || maxTime[1] < '4') {
                maxTime[0] = '2';
            } else {
                maxTime[0] = '1';
            }
        }
        if (maxTime[1] == '?') {
            if (maxTime[0] == '2') {
                maxTime[1] = '3';
            } else {
                maxTime[1] = '9';
            }
        }
        if (maxTime[3] == '?') {
            maxTime[3] = '5';
        }
        if (maxTime[4] == '?') {
            maxTime[4] = '9';
        }
        return new String(maxTime);
    }

    public String maximumTime2(String time) {
        int maxHrs = 23;
        int maxMin = 59;
        String[] t = time.split(":");
        return getMax(t[0], maxHrs) + ":" + getMax(t[1], maxMin);
    }

    private String getMax(String mask, int max) {
        if (mask.contains("?")) {
            mask = mask.replaceAll("\\?", ".");
            for (int x = max; x >= 0; x--) {
                String val = String.format("%02d", x);
                if (val.matches(mask)) {
                    return val;
                }
            }
        }
        return mask;
    }

    @Test
    public void check1() {
        Assertions.assertEquals("23:50", maximumTime("2?:?0"));
    }

    @Test
    public void check2() {
        Assertions.assertEquals("09:39", maximumTime("0?:3?"));
    }

    @Test
    public void check3() {
        Assertions.assertEquals("19:22", maximumTime("1?:22"));
    }

}

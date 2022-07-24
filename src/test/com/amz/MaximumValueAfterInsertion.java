package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/maximum-value-after-insertion/"
 */
public class MaximumValueAfterInsertion {

    public String maxValue(String n, int x) {
        int insertionIndexStart = 0;
        if (n.charAt(0) == '-') {
            insertionIndexStart++;
            while (insertionIndexStart < n.length() &&  n.charAt(insertionIndexStart) - '0' <= x) {
                insertionIndexStart++;
            }
        } else {
            if (n.charAt(0) == '+') insertionIndexStart++;
            while (insertionIndexStart < n.length() &&  n.charAt(insertionIndexStart) - '0' >= x) {
                insertionIndexStart++;
            }
        }
        return n.substring(0, insertionIndexStart) + x + n.substring(insertionIndexStart);
    }

    @Test
    public void check1() {
        Assertions.assertEquals("999", maxValue("99", 9));
    }

    @Test
    public void check2() {
        Assertions.assertEquals("-123", maxValue("-13", 2));
    }

    @Test
    public void check3() {
        Assertions.assertEquals("763", maxValue("73", 6));
    }

    @Test
    public void check4() {
        Assertions.assertEquals("-255", maxValue("-55", 2));
    }

    @Test
    public void check5() {
        Assertions.assertEquals("-1323", maxValue("-132", 3));
    }

    @Test
    public void check6() {
        Assertions.assertEquals("4699757879438632651173569913153377", maxValue("469975787943862651173569913153377", 3));
    }

}

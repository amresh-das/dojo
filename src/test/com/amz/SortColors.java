package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/sort-colors/"
 */
public class SortColors {

    public void sortColors(int[] nums) {
        final int[] colorCount = new int[3];
        for (int n :  nums) {
            colorCount[n]++;
        }
        int index = 0;
        for (int k = 0; k < 3; k++) {
            final int count = colorCount[k];
            for (int i = 0; i < count; i++) {
                nums[index++] = k;
            }
        }
    }

    @Test
    void t1() {
        verify("[2,0,2,1,1,0]", "[0,0,1,1,2,2]");
    }

    @Test
    void t2() {
        verify("[2,0,1]", "[0,1,2]");
    }

    @Test
    void t3() {
        verify("[0]", "[0]");
    }

    @Test
    void t4() {
        verify("[1]", "[1]");
    }

    private void verify(final String input, final String expected) {
        int[] in = Utils.stringToIntArray(input);
        sortColors(in);
        Assertions.assertArrayEquals(Utils.stringToIntArray(expected), in);
    }
}

package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/valid-mountain-array/"
 */
public class ValidMountainArray {
    public boolean validMountainArray(int[] arr) {
        if (arr.length < 3) return false;
        int index = 0, upCount = 0, downCount = 0;
        while (index < (arr.length - 1) && arr[index + 1] > arr[index] ) {
            upCount++;
            index++;
        }
        while (index < (arr.length - 1) && arr[index + 1] < arr[index] ) {
            downCount++;
            index++;
        }
        return index == arr.length - 1 && upCount > 0 && downCount > 0;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(false, validMountainArray(Utils.stringToIntArray("[2,1]")));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(false, validMountainArray(Utils.stringToIntArray("[3,5,5]")));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(true, validMountainArray(Utils.stringToIntArray("[0,3,2,1]")));
    }

    @Test
    public void check4() {
        Assertions.assertEquals(false, validMountainArray(Utils.stringToIntArray("[4,4,3,2,1]")));
    }
}

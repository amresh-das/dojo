package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/duplicate-zeros/"
 */
public class DuplicateZeros {

    public void duplicateZeros(int[] arr) {
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] == 0) {
                for (int j = arr.length - 1; j > i; j--) {
                    arr[j] = arr[j - 1];
                }
            }
        }
    }

    @Test
    public void check1() {
        int[] arr = Utils.stringToIntArray("[1,0,2,3,0,4,5,0]");
        duplicateZeros(arr);
        Assertions.assertEquals("[1,0,0,2,3,0,0,4]", Utils.toString(arr));
    }

    @Test
    public void check2() {
        int[] arr = Utils.stringToIntArray("[1,2,3]");
        duplicateZeros(arr);
        Assertions.assertEquals("[1,2,3]", Utils.toString(arr));
    }

}

package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @see "https://leetcode.com/problems/find-the-middle-index-in-array/"
 */
public class FindTheMiddleIndexInArray {

    public int findMiddleIndex(int[] nums) {
        if (nums.length == 0) return -1;
        if (nums.length == 1) return 0;
        int sum = Arrays.stream(nums).sum();

        int index = -1;
        int lefSum = 0;
        int righSum = sum;
        for (int i = 0; i < nums.length; i++) {
            righSum -= nums[i];
            if (lefSum == righSum) return i;
            lefSum += nums[i];
        }
        return index;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(3, findMiddleIndex(Utils.stringToIntArray("[2,3,-1,8,4]")));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(2, findMiddleIndex(Utils.stringToIntArray("[1,-1,4]")));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(-1, findMiddleIndex(Utils.stringToIntArray("[2,5]")));
    }

}

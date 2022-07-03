package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/wiggle-subsequence/"
 */
public class WiggleSubsequence {
    private final static Boolean START = null;
    private final static Boolean UP = Boolean.TRUE;
    private final static Boolean DOWN = Boolean.FALSE;

    public int wiggleMaxLength(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return 1;
        if (nums.length == 2) {
            return nums[0] == nums[1] ? 1 : 2;
        }
        int wiggleCount = 1;
        Boolean prevDir = START;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] < nums[i] && (prevDir == START || prevDir == DOWN)) {
                prevDir = UP;
                wiggleCount++;
            } else if (nums[i - 1] > nums[i] && (prevDir == START || prevDir == UP)) {
                prevDir = DOWN;
                wiggleCount++;
            }
        }
        return wiggleCount;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(6, wiggleMaxLength(Utils.stringToIntArray("[1,7,4,9,2,5]")));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(7, wiggleMaxLength(Utils.stringToIntArray("[1,17,5,10,13,15,10,5,16,8]")));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(2, wiggleMaxLength(Utils.stringToIntArray("[1,2,3,4,5,6,7,8,9]")));
    }

}

package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/delete-and-earn/"
 */
public class DeleteAndEarn {

    public int deleteAndEarn(int[] nums) {
        return 0;
    }

    @Test
    public void t1() {
        check("[3,4,2]", 6);
    }

    @Test
    public void t2() {
        check("[2,2,3,3,3,4]", 9);
    }

    private void check(String input, int expected) {
        Assertions.assertEquals(expected, deleteAndEarn(Utils.stringToIntArray(input)), input);
    }

}

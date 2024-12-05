package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @see "https://leetcode.com/problems/n-repeated-element-in-size-2n-array/"
 */
public class NRepeatedElementInSize2NArray {

    public int repeatedNTimes(int[] nums) {
        Set<Integer> found = new HashSet<>();
        for (int n : nums) {
            if (found.contains(n)) return n;
            found.add(n);
        }
        return -1;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(3, repeatedNTimes(Utils.stringToIntArray("[1,2,3,3]")));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(2, repeatedNTimes(Utils.stringToIntArray("[2,1,2,5,3,2]")));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(5, repeatedNTimes(Utils.stringToIntArray("[5,1,5,2,5,3,5,4]")));
    }
}

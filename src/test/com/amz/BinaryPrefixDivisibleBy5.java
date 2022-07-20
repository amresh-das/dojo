package com.amz;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @see "https://leetcode.com/problems/binary-prefix-divisible-by-5/"
 */
public class BinaryPrefixDivisibleBy5 {

    public List<Boolean> prefixesDivBy5(int[] nums) {
        final List<Boolean> ans = new ArrayList<>(nums.length);
        int num = 0;
        for (final int n : nums) {
            num = (num * 2 + n) % 10;
            ans.add(num % 5 == 0);
        }
        return ans;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(Lists.newArrayList(true, false, false), prefixesDivBy5(Utils.stringToIntArray("[0,1,1]")));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(Lists.newArrayList(false, false, false), prefixesDivBy5(Utils.stringToIntArray("[1,1,1]")));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(Lists.newArrayList(true,false,false,false,true,false), prefixesDivBy5(Utils.stringToIntArray("[0,1,1,1,1,1]")));
    }

    @Test
    public void check4() {
        Assertions.assertEquals(Lists.newArrayList(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,false,false,true,true,true,true,false), prefixesDivBy5(Utils.stringToIntArray("[1,0,0,1,0,1,0,0,1,0,1,1,1,1,1,1,1,1,1,1,0,0,0,0,1,0,1,0,0,0,0,1,1,0,1,0,0,0,1]")));
    }

    @Test
    public void check5() {
        String input = "[1,0,1,1,1,1,0,0,0,0,1,0,0,0,0,0,1,0,0,1,1,1,1,1,0,0,0,0,1,1,1,0,0,0,0,0,1,0,0,0,1,0,0,1,1,1,1,1,1,0,1,1,0,1,0,0,0,0,0,0,1,0,1,1,1,0,0,1,0]";
        Assertions.assertEquals(Lists.newArrayList(false,false,true,false,false,false,false,false,false,false,true,true,true,true,true,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,false,false,false,true,false,false,true,false,false,true,true,true,true,true,true,true,false,false,true,false,false,false,false,true,true), prefixesDivBy5(Utils.stringToIntArray(input)));
    }

}

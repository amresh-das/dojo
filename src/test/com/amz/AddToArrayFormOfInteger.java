package com.amz;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @see "https://leetcode.com/problems/add-to-array-form-of-integer/"
 */
public class AddToArrayFormOfInteger {
    public List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> sum = new ArrayList<>();
        int remaining = k;
        int numIndex = num.length - 1;
        int carry = 0;
        while (remaining > 0 || numIndex >= 0) {
            int a = remaining % 10;
            int temp = a + (numIndex >= 0 ? num[numIndex--] : 0) + carry;
            if (temp > 9) {
                sum.add(0, temp % 10);
                carry = 1;
            } else {
                sum.add(0, temp);
                carry = 0;
            }
            remaining = remaining / 10;
        }
        if (carry == 1) {
            sum.add(0, 1);
        }
        return sum;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(Lists.newArrayList(1,2,3,4), addToArrayForm(Utils.stringToIntArray("[1,2,0,0]"), 34));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(Lists.newArrayList(4,5,5), addToArrayForm(Utils.stringToIntArray("[2,7,4]"), 181));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(Lists.newArrayList(1,0,2,1), addToArrayForm(Utils.stringToIntArray("[2,1,5]"), 806));
    }

}

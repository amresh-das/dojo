package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RotateArray {

    public void rotate(int[] nums, int k) {
        final Runnable[] fns = new Runnable[nums.length];
        for (int i = 0; i < nums.length; i++) {
            final int index = (i + k) % nums.length;
            final int value = nums[i];
            fns[i] = () -> nums[index] = value;
        }
        for(Runnable fn : fns) {
            fn.run();
        }
    }

    @Test
    public void t1() {
        check("[1,2,3,4,5,6,7]", 3, "[5,6,7,1,2,3,4]");
    }

    @Test
    public void t2() {
        check("[-1,-100,3,99]", 2, "[3,99,-1,-100]");
    }

    private void check(String input, int k, String expected) {
        int[] nums = Utils.stringToIntArray(input);
        rotate(nums, k);
        Assertions.assertEquals(expected, Utils.toString(nums), String.format("%s rotated by %d", input, k));
    }

}

package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JumpGameII {

    public int jump(int[] nums) {
        if (nums.length == 0) return 0;
        return jumpAndCount(nums, 0, 0);
    }

    private int jumpAndCount(final int[] nums, final int index, final int count) {
        if (index == nums.length - 1) return count;
        final int skipJump = jumpAndCount(nums, index + 1, count + 1);
        if (index + nums[index] <= nums.length && nums[index] != 0) {
            return Math.min(skipJump, (index + nums[index] == nums.length) ? count + 1 : jumpAndCount(nums, index + nums[index], count + 1));
        }
        return skipJump;
    }

    @Test
    void t1() {
        Assertions.assertEquals(2, doJump(2,3,1,1,4));
    }

    @Test
    void t2() {
        Assertions.assertEquals(2, doJump(2,3,0,1,4));
    }

    @Test
    void t3() {
        Assertions.assertEquals(1, doJump(3,2,1));
    }

    @Test
    void t4() {
        Assertions.assertEquals(2, doJump(1,2,3));
    }

    @Test
    void t5() {
        Assertions.assertEquals(2, doJump(4,1,1,3,1,1,1));
    }

    private int doJump(final int... arr) {
        return jump(arr);
    }
}

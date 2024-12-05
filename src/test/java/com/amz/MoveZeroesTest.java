package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @see "https://leetcode.com/problems/move-zeroes/description/?envType=study-plan-v2&envId=leetcode-75"
 */
public class MoveZeroesTest {

    public void moveZeroes(int[] nums) {
        int zeroCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroCount++;
            } else {
                nums[i - zeroCount] = nums[i];
                if (zeroCount != 0) nums[i] = 0;
            }
        }
    }

    @Test
    void check1() {
        int[] input = new int[] {0,1,0,3,12};
        List<Integer> expected = List.of(1,3,12,0,0);
        moveZeroes(input);
        Assertions.assertEquals(expected, Arrays.stream(input).boxed().toList());
    }

    @Test
    void check2() {
        int[] input = new int[] {1};
        List<Integer> expected = List.of(1);
        moveZeroes(input);
        Assertions.assertEquals(expected, Arrays.stream(input).boxed().toList());
    }
}

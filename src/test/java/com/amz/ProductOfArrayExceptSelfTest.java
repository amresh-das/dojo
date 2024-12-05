package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @see "https://leetcode.com/problems/product-of-array-except-self/?envType=study-plan-v2&envId=leetcode-75"
 */
public class ProductOfArrayExceptSelfTest {

    @Test
    void check1() {
        verify(new int[] {1, 2, 3, 4}, List.of(24, 12, 8, 6));
    }

    @Test
    void check2() {
        verify(new int[] {-1,1,0,-3,3}, List.of(0,0,9,0,0));
    }

    private void verify(final int[] input, final List<Integer> output) {
        Assertions.assertEquals(output, Arrays.stream(productExceptSelf(input)).boxed().toList());
    }

    public int[] productExceptSelf(int[] nums) {
        final int n = nums.length;
        final int[] output = new int[n];
        final int[] leftProds = new int[n + 1];
        final int[] rightProds = new int[n + 1];
        leftProds[0] = 1;
        rightProds[n] = 1;
        for (int i = 0; i < n - 1; i++) {
            leftProds[i + 1] = leftProds[i] * nums[i];
            rightProds[n - i - 1] = rightProds[n - i] * nums[n - i - 1];
        }
        for (int i = 0; i < n; i++) {
            int left = leftProds[i];
            int right = rightProds[i + 1];
            output[i] = left * right;
        }
        return output;
    }

    private int multiplyExcept(final int[] nums, final int exceptIndex) {
        int product = 1;
        for (int i = 0; i < nums.length; i++) {
            if (i != exceptIndex) product *= nums[i];
        }
        return product;
    }

}

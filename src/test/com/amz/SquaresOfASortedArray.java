package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/squares-of-a-sorted-array/"
 */
public class SquaresOfASortedArray {

    public int[] sortedSquares(int[] nums) {
        int[] squares = new int[nums.length];
        int positiveIndex = nums[nums.length - 1] < 0 ? nums.length : getMinPositiveIndex(nums);
        int count = 0;
        int left = positiveIndex - 1;
        int right = positiveIndex;
        while (count < nums.length) {
            if (left < 0) {
                squares[count++] = nums[right] * nums[right];
                right++;
            } else if (right >= nums.length) {
                squares[count++] = nums[left] * nums[left];
                left--;
            } else {
                if (Math.abs(nums[left]) < Math.abs(nums[right])) {
                    squares[count++] = nums[left] * nums[left];
                    left--;
                } else {
                    squares[count++] = nums[right] * nums[right];
                    right++;
                }
            }
        }
        return squares;
    }

    private int getMinPositiveIndex(int[] nums) {
        int low = 0;
        int high = nums.length;
        int positiveIndex = nums.length;
        do {
            int mid = low + (high - low) / 2;
            if (nums[mid] < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
                positiveIndex = mid;
            }
        } while (low <= high);
        return positiveIndex;
    }

    @Test
    public void t1() {
        check("[-4,-1,0,3,10]", "[0,1,9,16,100]");
    }

    @Test
    public void t2() {
        check("[-7,-3,2,3,11]", "[4,9,9,49,121]");
    }

    @Test
    public void t3() {
        check("[-1,-1,-1,0,0,1,1,1]", "[0,0,1,1,1,1,1,1]");
    }

    @Test
    public void t4() {
        check("[-1]", "[1]");
    }

    @Test
    public void t5() {
        check("[-5,-3,-2,-1]", "[1,4,9,25]");
    }

    @Test
    public void t6() {
        check("[-4,-3,-2,3,3]", "[4,9,9,9,16]");
    }

    private void check(String input, String expected) {
        Assertions.assertEquals(expected, Utils.toString(sortedSquares(Utils.stringToIntArray(input))), input);
    }

}

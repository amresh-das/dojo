package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/merge-sorted-array/"
 */
public class MergeSortedArray {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int answerIndex = nums1.length - 1;
        int n1Index = m - 1;
        int n2Index = n - 1;
        while (answerIndex >= 0) {
            if (n1Index >= 0 && n2Index >=0) {
                if (nums1[n1Index] > nums2[n2Index]) {
                    nums1[answerIndex--] = nums1[n1Index--];
                } else {
                    nums1[answerIndex--] = nums2[n2Index--];
                }
            } else if (n1Index >= 0) {
                nums1[answerIndex--] = nums1[n1Index--];
            } else {
                nums1[answerIndex--] = nums2[n2Index--];
            }
        }
    }

    @Test
    public void check1() {
        int[] nums1 = new int[] {1,2,3,0,0,0};
        int[] nums2 = new int[] {2,5,6};
        merge(nums1, 3, nums2, 3);
        Assertions.assertEquals("[1,2,2,3,5,6]", Utils.toString(nums1));
    }

    @Test
    public void check2() {
        int[] nums1 = new int[] {1};
        int[] nums2 = new int[] {};
        merge(nums1, 1, nums2, 0);
        Assertions.assertEquals("[1]", Utils.toString(nums1));
    }

    @Test
    public void check3() {
        int[] nums1 = new int[] {0};
        int[] nums2 = new int[] {1};
        merge(nums1, 0, nums2, 1);
        Assertions.assertEquals("[1]", Utils.toString(nums1));
    }

}

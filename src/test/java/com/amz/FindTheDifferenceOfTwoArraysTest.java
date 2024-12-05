package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @see "https://leetcode.com/problems/find-the-difference-of-two-arrays/description/?envType=study-plan-v2&envId=leetcode-75"
 */
public class FindTheDifferenceOfTwoArraysTest {

    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        final List<Integer> diff1 = new ArrayList<>();
        final List<Integer> diff2 = new ArrayList<>();
        final List<List<Integer>> result = List.of(diff2, diff1);
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            while (i < nums1.length - 1 && nums1[i] == nums1[i + 1]) i++;
            while (j < nums2.length - 1 && nums2[j] == nums2[j + 1]) j++;
            if (nums1[i] == nums2[j]) {
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                diff2.add(nums1[i++]);
            } else {
                diff1.add(nums2[j++]);
            }
        }
        if (i < nums1.length) {
            diff2.addAll(Arrays.stream(nums1, i, nums1.length).boxed().collect(Collectors.toSet()));
        }
        if (j < nums2.length) {
            diff1.addAll(Arrays.stream(nums2, j, nums2.length).boxed().collect(Collectors.toSet()));
        }
        return result;
    }

    @Test
    void check1() {
        List<List<Integer>> output = findDifference(new int[] {1,2,3}, new int[] {2,4,6});
        Assertions.assertEquals(Set.of(1,3), new HashSet<>(output.get(0)));
        Assertions.assertEquals(Set.of(4,6), new HashSet<>(output.get(1)));
    }

    @Test
    void check2() {
        List<List<Integer>> output = findDifference(new int[] {1,2,3,3}, new int[] {1,1,2,2});
        Assertions.assertEquals(Set.of(3), new HashSet<>(output.get(0)));
        Assertions.assertEquals(Set.of(), new HashSet<>(output.get(1)));
    }
}

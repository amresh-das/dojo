package com.amz;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @see "https://leetcode.com/problems/subsets-ii/"
 */
public class SubsetsII {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        final List<List<Integer>> ans = new ArrayList<>();
        compute(nums, ans, new ArrayList<>(), 0);
        return ans;
    }

    private void compute(int[] nums, List<List<Integer>> ans, List<Integer> list, int index) {
        ans.add(new ArrayList<>(list));
        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i - 1] == nums[i]) continue;
            list.add(nums[i]);
            compute(nums, ans, list, i + 1);
            list.remove(list.size() - 1);
        }
    }

    //    @Test
//    public void check1() {
//        System.out.println(subsetsWithDup(Utils.stringToIntArray("[1,2,2]")));
//    }
//
    @Test
    public void check2() {
        System.out.println(subsetsWithDup(Utils.stringToIntArray("[4,4,4,1,4]")));
    }
}

package com.amz;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @see "https://leetcode.com/problems/minimum-index-sum-of-two-lists/"
 */
public class MinimumIndexSumOfTwoLists {

    public String[] findRestaurant(String[] list1, String[] list2) {
        final List<String> ans = new ArrayList<>();
        int minIndexSum = Integer.MAX_VALUE;
        Map<String, Integer> indices = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            indices.putIfAbsent(list1[i], i);
        }
        for (int i = 0; i < list2.length; i++) {
            String s = list2[i];
            if (indices.containsKey(s)) {
                int index = indices.get(s);
                if (minIndexSum == index + i) {
                    ans.add(s);
                } else if (minIndexSum > index + i) {
                    ans.clear();
                    minIndexSum = index + i;
                    ans.add(s);
                }
            }
        }
        return ans.toArray(new String[0]);
    }

    @Test
    public void check1() {
        String[] list1 = new String[] {"Shogun","Tapioca Express","Burger King","KFC"};
        String[] list2 = new String[] {"Piatti","The Grill at Torrey Pines","Hungry Hunter Steakhouse","Shogun"};
        Assertions.assertEquals(Sets.newHashSet("Shogun"), Arrays.stream(findRestaurant(list1, list2)).collect(Collectors.toSet()));
    }

    @Test
    public void check2() {
        String[] list1 = new String[] {"Shogun","Tapioca Express","Burger King","KFC"};
        String[] list2 = new String[] {"KFC","Shogun","Burger King"};
        Assertions.assertEquals(Sets.newHashSet("Shogun"), Arrays.stream(findRestaurant(list1, list2)).collect(Collectors.toSet()));
    }

    @Test
    public void check3() {
        String[] list1 = new String[] {"happy","sad","good"};
        String[] list2 = new String[] {"sad","happy","good"};
        Assertions.assertEquals(Sets.newHashSet("sad","happy"), Arrays.stream(findRestaurant(list1, list2)).collect(Collectors.toSet()));
    }
}

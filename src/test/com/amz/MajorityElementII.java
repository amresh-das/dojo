package com.amz;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @see "https://leetcode.com/problems/majority-element-ii/"
 */
public class MajorityElementII {

    public List<Integer> majorityElement(int... nums) {
        final List<Integer> elements = new ArrayList<>();
        final Map<Integer, Integer> counts = new HashMap<>();
        int limit = nums.length / 3;
        for (int n: nums) {
            if (elements.contains(n)) continue;
            if (counts.compute(n, (k, v) -> v == null ? 1 : v + 1) > limit) {
                elements.add(n);
            }
        }
        return elements;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(Lists.newArrayList(3), majorityElement(3,2,3));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(Lists.newArrayList(1), majorityElement(1));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(Lists.newArrayList(1,2), majorityElement(1,2));
    }
}

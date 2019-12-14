package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @see "https://leetcode.com/problems/group-the-people-given-the-group-size-they-belong-to"
 */
public class GroupPeopleByGroupSizes {

    @Test
    void t1() {
        int[] in = new int[] {3,3,3,3,3,1,3};
        verify(in);
    }

    @Test
    void t2() {
        int[] in = new int[] {2,1,3,3,3,2};
        verify(in);
    }

    private void verify(int[] in) {
        List<List<Integer>> out = groupThePeople(in);
        for (int i = 0; i < in.length; i++) {
            Integer id = i;
            List<List<Integer>> match = out.stream().filter(o -> o.contains(id)).collect(Collectors.toList());
            Assertions.assertEquals(1, match.size());
            Assertions.assertEquals(in[i], match.get(0).size());
        }
    }

    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < groupSizes.length; i++) {
            Integer id = i;
            map.compute(groupSizes[i], (k, v) -> {
               List<Integer> values = v == null ? new ArrayList<>() : v;
               values.add(id);
               return values;
            });
        }
        map.forEach((k, v) -> {
            for (int i = 0; i < v.size() / k; i++) {
                result.add(v.subList(i * k, (i + 1) * k));
            }
        });
        return result;
    }
}

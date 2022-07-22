package com.amz;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * TODO
 * @see "https://leetcode.com/problems/shortest-distance-to-target-color/"
 */
public class ShortestDistanceToTargetColor {
    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        final Map<Integer, List<Integer>> indices = new HashMap<>();
        for (int i = 0; i < colors.length; i++) {
            final int index = i;
            indices.compute(colors[i], (k, v) -> {
                List<Integer> list = v == null ? new ArrayList<>() : v;
                list.add(index);
                return list;
            });
        }
        final List<Integer> ans = new ArrayList<>();
        for (int[] query: queries) {
            int start = query[0];
            List<Integer> places = indices.get(query[1]);
            if (places == null) {
                ans.add(-1);
            } else {
                ans.add(places.stream().map(x -> Math.abs(start - x)).min(Comparator.comparingInt(a -> a)).get());
            }
        }
        return ans;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(Lists.newArrayList(3,0,3), shortestDistanceColor(Utils.stringToIntArray("1,1,2,1,3,2,2,3,3"), Utils.to2dIntMatrix("[[1,3],[2,2],[6,1]]")));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(Lists.newArrayList(-1), shortestDistanceColor(Utils.stringToIntArray("1,2"), Utils.to2dIntMatrix("[[0,3]]")));
    }


}

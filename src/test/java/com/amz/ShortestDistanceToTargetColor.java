package com.amz;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
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
                int size = places.size();
                if (size == 1) {
                    ans.add(Math.abs(start - places.get(0)));
                } else {
                    int index = Collections.binarySearch(places, start);
                    if (index >= 0) {
                        ans.add(0);
                    } else {
                        int idx = -index - 1;
                        int min = Integer.MAX_VALUE;
                        if (idx < size) {
                            if (idx > 0) {
                                min = Math.min(min, Math.abs(start - places.get(idx - 1)));
                            }
                            min = Math.min(min, Math.abs(start - places.get(idx)));
                            if (idx < size - 1) {
                                min = Math.min(min, Math.abs(start - places.get(idx + 1)));
                            }
                        } else {
                            min = Math.min(min, Math.abs(start - places.get(size - 1)));
                        }
                        ans.add(min);
                    }
                }
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

    @Test
    public void check3() {
        Assertions.assertEquals(Lists.newArrayList(0,-1,-1,1,1), shortestDistanceColor(Utils.stringToIntArray("[2,1,2,2,1]"), Utils.to2dIntMatrix("[[1,1],[4,3],[1,3],[4,2],[2,1]]")));
    }


}

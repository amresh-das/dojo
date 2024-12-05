package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @see "https://leetcode.com/problems/campus-bikes/"
 */
public class CampusBikes {

    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int[] ans = new int[workers.length];
        Arrays.fill(ans, -1);
        final TreeMap<Integer, List<int[]>> map = new TreeMap<>();
        for (int i = 0; i < workers.length; i++) {
            final int workerIndex = i;
            for (int j = 0; j < bikes.length; j++) {
                final int bikeIndex = j;
                int dist = manhattanDist(workers[i], bikes[j]);
                map.compute(dist, (k, v) -> {
                    List<int[]> list = v == null ? new ArrayList<>() : v;
                    list.add(new int[] {workerIndex, bikeIndex});
                    return list;
                });
            }
        }
        final Set<Integer> matchedWorkers = new HashSet<>();
        final Set<Integer> matchedBikes = new HashSet<>();
        for (int dist : map.keySet()) {
            if (matchedWorkers.size() == workers.length) break;
            final List<int[]> matches = map.get(dist).stream().sorted(Comparator.comparingInt(x -> x[0] * 10000 + x[1])).collect(Collectors.toList());
            for (int[] match : matches) {
                if (!matchedWorkers.contains(match[0]) && !matchedBikes.contains(match[1])) {
                    ans[match[0]] = match[1];
                    matchedWorkers.add(match[0]);
                    matchedBikes.add(match[1]);
                }
            }
        }
        return ans;
    }

    private int manhattanDist(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }

    @Test
    public void check1() {
        Assertions.assertEquals("[1,0]", Utils.toString(assignBikes(Utils.to2dIntMatrix("[[0,0],[2,1]]"), Utils.to2dIntMatrix("[[1,2],[3,3]]"))));
    }

    @Test
    public void check2() {
        Assertions.assertEquals("[0,2,1]", Utils.toString(assignBikes(Utils.to2dIntMatrix("[[0,0],[1,1],[2,0]]"), Utils.to2dIntMatrix("[[1,0],[2,2],[2,1]]"))));
    }
}

package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @see "https://leetcode.com/problems/connecting-cities-with-minimum-cost/"
 */
public class ConnectingCitiesWithMinimumCost {

    public int minimumCost(int n, int[][] connections) {
        DSU dsu = new DSU(n);
        Arrays.sort(connections, Comparator.comparingInt(x -> x[2]));
        int minCost = 0;
        int edgeCount = 0;
        for (int[] conn : connections) {
            int a = conn[0];
            int b = conn[1];
            int cost = conn[2];
            if (dsu.isDisjoint(a, b)) {
                edgeCount++;
                dsu.union(a, b);
                minCost += cost;
                if (edgeCount == n - 1) return minCost;
            }
        }
        return -1;
    }

    class DSU {
        int[] parents;
        int[] weights;

        DSU(int n) {
            parents = new int[n + 1];
            weights = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parents[i] = i;
                weights[i] = 1;
            }
        }

        void union(int a, int b) {
            int parentA = find(a);
            int parentB = find(b);
            if (parentA == parentB) return;
            if (weights[parentA] > weights[parentB]) {
                parents[parentB] = parentA;
                weights[parentA] += weights[parentB];
            } else {
                parents[parentA] = parentB;
                weights[parentB] += weights[parentA];
            }
        }

        int find(int a) {
            int x = a;
            while (x != parents[x]) {
                parents[x] = parents[parents[x]];
                x = parents[x];
            }
            return x;
        }

        boolean isDisjoint(int a, int b) {
            return find(a) != find(b);
        }
    }

    public int minimumCost2(int n, int[][] connections) {
        final Map<Integer, List<int[]>> edges = new HashMap<>();
        for (int[] conn : connections) {
            edges.compute(conn[0], (k, v) -> {
                List<int[]> list = Optional.ofNullable(v).orElseGet(ArrayList::new);
                list.add(new int[] {conn[1], conn[2]});
                return list;
            });
            edges.compute(conn[1], (k, v) -> {
                List<int[]> list = Optional.ofNullable(v).orElseGet(ArrayList::new);
                list.add(new int[] {conn[0], conn[2]});
                return list;
            });
        }
        if (edges.size() < n) return -1;

        final Set<Integer> visited = new HashSet<>();
        long cost = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            visited.add(i);
            long c = minTraversed(edges, visited, n);
            cost = Math.min(cost, c);
            visited.remove(i);
        }
        return cost == Integer.MAX_VALUE ? -1 : (int) cost;
    }

    private long minTraversed(Map<Integer, List<int[]>> edges, Set<Integer> visited, final int n) {
        if (visited.size() == n) return 0;
        long min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (!visited.contains(i)) continue;
            for (int[] next : edges.get(i)) {
                if (!visited.contains(next[0])) {
                    visited.add(next[0]);
                    long cost = next[1] + minTraversed(edges, visited, n);
                    min = Math.min(min, cost);
                    visited.remove(next[0]);
                }
            }
        }
        return min;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(6, minimumCost(3, Utils.to2dIntMatrix("[[1,2,5],[1,3,6],[2,3,1]]")));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(-1, minimumCost(4, Utils.to2dIntMatrix("[[1,2,3],[3,4,4]]")));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(6, minimumCost(4, Utils.to2dIntMatrix("[[1,2,1],[1,3,2],[3,4,4],[1,4,3]]")));
    }

}

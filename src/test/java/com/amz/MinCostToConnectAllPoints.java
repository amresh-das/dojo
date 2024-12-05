package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @see "https://leetcode.com/problems/min-cost-to-connect-all-points/"
 */
public class MinCostToConnectAllPoints {

    public int minCostConnectPoints(int[][] points) {
        DSU dsu = new DSU(points.length);
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x[0]));
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (i != j) {
                    int manhattan = manhattan(points[i], points[j]);
                    pq.offer(new int[] {manhattan, i, j});
                }
            }
        }
        int edgeCount = 0;
        int cost = 0;
        while (edgeCount < points.length && !pq.isEmpty()) {
            int[] item = pq.poll();
            if (dsu.isDisjoint(item[1], item[2])) {
                cost += item[0];
                dsu.union(item[1], item[2]);
                edgeCount++;
            }
        }
        return cost;
    }

    private int manhattan(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }

    class DSU {
        private final int[] parents;
        private final int[] sizes;

        DSU(final int size) {
            parents = new int[size];
            for (int i = 0; i < size; i++) {
                parents[i] = i;
            }
            sizes = new int[size];
            Arrays.fill(sizes, 1);
        }

        public void union(final int a, final int b) {
            int parentA = find(a);
            int parentB = find(b);

            if (sizes[parentA] > sizes[parentB]) {
                parents[parentB] = parentA;
                sizes[parentA] += sizes[parentB];
            } else {
                parents[parentA] = parentB;
                sizes[parentB] += sizes[parentA];
            }
        }

        public int find(int x) {
            int parent = x;
            while (parent != parents[parent]) {
                parents[parent] = parents[parents[parent]];
                parent = parents[parent];
            }
            return parent;
        }

        public boolean isDisjoint(int a, int b) {
            return find(a) != find(b);
        }
    }

    @Test
    public void check1() {
        Assertions.assertEquals(18, minCostConnectPoints(Utils.to2dIntMatrix("[[3,12],[-2,5],[-4,1]]")));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(20, minCostConnectPoints(Utils.to2dIntMatrix("[[0,0],[2,2],[3,10],[5,2],[7,0]]")));
    }
}

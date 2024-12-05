package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class ReorderRoutesToMakeAllPathsLeadToTheCityZeroTest {

    public int minReorder(int n, int[][] connections) {
        var visited = new boolean[n];
        List<Integer>[] nodes = new List[n];
        var reorders = 0;
        for (int i = 0; i < n; i++) {
            nodes[i] = new ArrayList<>(2);
        }
        for (int[] edge : connections) {
            nodes[edge[0]].add(edge[1]);
            nodes[edge[1]].add(-edge[0]);
        }
        final Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        while (!queue.isEmpty()) {
            int v;
            if(!visited[Math.abs(v = queue.poll())]) {
                if (v > 0) reorders++;
                else v *= -1;
                visited[v] = true;
                queue.addAll(nodes[v]);
            }
        }
        return reorders;
    }

    private int findRoot(int[] roots, int x) {
        while (roots[x] != x) {
            x = roots[x];
        }
        return x;
    }

    public int minReorder2(int n, int[][] connections) {
        LinkedList<int[]> queue = new LinkedList<>();
        Set<Integer> goingToZero = new HashSet<>();
        int reorderCount = 0;
        for (int[] conn : connections) {
            queue.offer(conn);
        }
        while (!queue.isEmpty()) {
            int[] conn = queue.poll();
            int e1 = conn[0];
            int e2 = conn[1];
            if (e1 == 0) {
                reorderCount++;
                goingToZero.add(e2);
            } else if (e2 == 0) {
                goingToZero.add(e1);
            } else {
                if (goingToZero.contains(e1)) {
                    reorderCount++;
                    goingToZero.add(e2);
                } else if (goingToZero.contains(e2)) {
                    goingToZero.add(e1);
                } else {
                    queue.offer(conn);
                }
            }
        }
        return reorderCount;
    }

    @Test
    void t1() {
        Assertions.assertEquals(3, minReorder(6, new int[][] {
                new int[] {0, 1},
                new int[] {1, 3},
                new int[] {2, 3},
                new int[] {4, 0},
                new int[] {4, 5}
        }));
    }

    @Test
    void t2() {
        Assertions.assertEquals(2, minReorder(5, new int[][] {
                new int[] {1, 0},
                new int[] {1, 2},
                new int[] {3, 2},
                new int[] {3, 4}
        }));
    }

    @Test
    void t3() {
        Assertions.assertEquals(0, minReorder(3, new int[][] {
                new int[] {1, 0},
                new int[] {2, 0}
        }));
    }

    @Test
    void t4() {
        Assertions.assertEquals(0, minReorder(3, new int[][] {
                new int[] {1, 2},
                new int[] {2, 0}
        }));
    }
}

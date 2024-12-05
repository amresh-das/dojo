package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @see "https://leetcode.com/problems/total-cost-to-hire-k-workers/description/?envType=study-plan-v2&envId=leetcode-75"
 */
public class TotalCostToHireKWorkersTest {

    public long totalCost(int[] costs, int k, int candidates) {
        if (2 * candidates >= costs.length) {
            Arrays.sort(costs);
            return Arrays.stream(costs, 0, k).boxed().mapToLong(Integer::longValue).sum();
        }

        var cost = 0L;
        int l = -1, r = costs.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        for (int i = 0; i < candidates; i++) {
            pq.offer(new int[]{costs[++l], 0});
            pq.offer(new int[]{costs[--r], 1});
        }
        while (k > 0 && !pq.isEmpty()) {
            final int[] c = pq.poll();
            cost += c[0];
            if (c[1] == 0 && l + 1 < r) pq.offer(new int[]{costs[++l], 0});
            if (c[1] == 1 && r - 1 > l) pq.offer(new int[]{costs[--r], 1});
            k--;
        }
        return cost;
    }

    @Test
    void t1() {
        var costs = new int[] {17,12,10,2,7,2,11,20,8};
        Assertions.assertEquals(11, totalCost(costs, 3, 4));
    }

    @Test
    void t2() {
        var costs = new int[] {1,2,4,1};
        Assertions.assertEquals(4, totalCost(costs, 3, 3));
    }

    @Test
    void t3() {
        var costs = new int[] {31,25,72,79,74,65,84,91,18,59,27,9,81,33,17,58};
        Assertions.assertEquals(423, totalCost(costs, 11, 2));
    }

    @Test
    void t4() {
        var costs = new int[] {28,35,21,13,21,72,35,52,74,92,25,65,77,1,73,32,43,68,8,100,84,80,14,88,42,53,98,69,64,40,60,23,99,83,5,21,76,34};
        Assertions.assertEquals(1407, totalCost(costs, 32, 12));
    }

    @Test
    void t5() {
        var costs = new int[] {18,64,12,21,21,78,36,58,88,58,99,26,92,91,53,10,24,25,20,92,73,63,51,65,87,6,17,32,14,42,46,65,43,9,75};
        Assertions.assertEquals(223, totalCost(costs, 13, 23));
    }
}

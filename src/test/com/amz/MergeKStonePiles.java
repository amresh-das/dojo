package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//TODO Fix Implementation
public class MergeKStonePiles {

    @Test
    void t1() {
        int[] input = new int[] {3,2,4,1};
        int k = 2;
        Assertions.assertEquals(20, mergeStones(input, k));
    }

    @Test
    void t2() {
        int[] input = new int[] {3,2,4,1};
        int k = 3;
        Assertions.assertEquals(-1, mergeStones(input, k));
    }

    @Test
    void t3() {
        int[] input = new int[] {3,5,1,2,6};
        int k = 3;
        Assertions.assertEquals(25, mergeStones(input, k));
    }

    @Test
    void t4() {
        int[] input = new int[] {1};
        int k = 2;
        Assertions.assertEquals(0, mergeStones(input, k));
    }

    @Test
    void t5() {
        int[] input = new int[] {6,4,4,6};
        int k = 2;
        Assertions.assertEquals(40, mergeStones(input, k));
    }

    @Test
    void t6() {
        int[] input = new int[] {1,3,2};
        int k = 2;
        int actual = mergeStones(input, k);
        Assertions.assertEquals(10, actual);
    }

    @Test
    void t7() {
        int[] input = new int[] {9,8,3,2,9,4};
        int k = 2;
        int actual = mergeStones(input, k);
        Assertions.assertEquals(88, actual);
    }

    public int mergeStones(int[] stones, int K) {
        if (stones.length == 1) return 0;
        int solution = merge(Arrays.stream(stones).boxed().collect(Collectors.toList()), K, 0);
        System.out.println(solution);
        return solution;
    }

    private int merge(final List<Integer> stones, final int k, final int cost) {
        System.out.print(stones);
        System.out.println(" -> " + cost);
        int pileCount = stones.size();
        if (pileCount == k) {
            return cost + sum(stones, 0, k);
        }
        if (pileCount > k) {
            int minRunningSum = Integer.MAX_VALUE;
            int minRunningSumIndex = -1;
            for (int i = 0; i < pileCount - k + 1; i++) {
                int runningSum = sum(stones, i, k);
                if (runningSum < minRunningSum) {
                    minRunningSum = runningSum;
                    minRunningSumIndex = i;
                }
            }
            for (int i = 0; i < k; i++) {
                stones.remove(minRunningSumIndex);
            }
            stones.add(minRunningSumIndex, minRunningSum);
            return merge(stones, k, cost + minRunningSum);
        }
        return -1;
    }

    private Integer sum(final List<Integer> stones, int offset, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += stones.get((offset + i) % stones.size());
        }
        return sum;
    }
}

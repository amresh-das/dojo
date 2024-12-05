package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @see "https://leetcode.com/problems/maximum-subsequence-score/submissions/1463151251/?envType=study-plan-v2&envId=leetcode-75"
 */
public class MaximumSubsequenceScoreTest {

    public long maxScore(int[] nums1, int[] nums2, int k) {
        int[][] n = new int[nums1.length][2];
        for (int i = 0; i < nums1.length; i++) {
            n[i][0] = nums1[i];
            n[i][1] = nums2[i];
        }
        Arrays.sort(n, Collections.reverseOrder(Comparator.comparingInt(x -> x[1])));
        PriorityQueue<Long> pq = new PriorityQueue<>();
        long maxScore = 0;
        long pSum = 0;
        for (int[] x : n) {
            long s = x[0];
            long m = x[1];
            pq.offer(s);
            pSum += s;
            if (pq.size() > k) pSum -= pq.poll();
            if (pq.size() == k) maxScore = Math.max(maxScore, pSum * m);
        }
        return maxScore;
    }

    @Test
    void t1() {
        var n1 = new int[] {1,3,3,2};
        var n2 = new int[] {2,1,3,4};
        var k = 3;
        Assertions.assertEquals(12L, maxScore(n1, n2, k));
    }

    @Test
    void t2() {
        var n1 = new int[] {4,2,3,1,1};
        var n2 = new int[] {7,5,10,9,6};
        var k = 1;
        Assertions.assertEquals(30L, maxScore(n1, n2, k));
    }

    @Test
    void t3() {
        var n1 = new int[] {2,1,14,12};
        var n2 = new int[] {11,7,13,6};
        var k = 3;
        Assertions.assertEquals(168L, maxScore(n1, n2, k));
    }
}

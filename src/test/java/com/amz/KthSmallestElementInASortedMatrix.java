package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @see "https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/"
 */
public class KthSmallestElementInASortedMatrix {

    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for (int i = 0; i < matrix.length; i++) {
            pq.offer(new int[] {matrix[i][0], i, 0});
        }
        while (k > 0) {
            int[] item = pq.poll();
            int val = item[0];
            int row = item[1];
            int col = item[2];
            if (--k == 0) return val;
            if (col < matrix[0].length - 1) {
                pq.offer(new int[] {matrix[row][col + 1], row, col + 1});
            }
        }
        return -1;
    }

    @Test
    public void check1() {
        String input = "[[1,5,9]," +
                        "[10,11,13]," +
                        "[12,13,15]]";
        Assertions.assertEquals(13, kthSmallest(Utils.to2dIntMatrix(input), 8));
    }

    @Test
    public void check2() {
        String input = "[[1,5,9]," +
                        "[10,11,13]," +
                        "[12,13,15]]";
        Assertions.assertEquals(15, kthSmallest(Utils.to2dIntMatrix(input), 9));
    }

    @Test
    public void check3() {
        String input = "[[1,5,9]," +
                        "[10,11,13]," +
                        "[12,13,15]]";
        Assertions.assertEquals(15, kthSmallest(Utils.to2dIntMatrix(input), 9));
    }

}

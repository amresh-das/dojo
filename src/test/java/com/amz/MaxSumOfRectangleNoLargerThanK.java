package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * @see "https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/"
 */
public class MaxSumOfRectangleNoLargerThanK {
    int result = Integer.MIN_VALUE;

    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        if (n > m) {
            int[] rowSum = new int[n];
            for (int i = 0; i < m; i++) {
                Arrays.fill(rowSum, 0);
                for (int r = i; r < m; r++) {
                    for (int c = 0; c < n; c++) {
                        rowSum[c] += matrix[r][c];
                    }
                    evalArrForK(rowSum, k);
                    if (result == k) return k;
                }
            }
        } else {
            int[] colSum = new int[m];
            for (int i = 0; i < n; i++) {
                Arrays.fill(colSum, 0);
                for (int c = i; c < n; c++) {
                    for (int r = 0; r < m; r++) {
                        colSum[r] += matrix[r][c];
                    }
                    evalArrForK(colSum, k);
                    if (result == k) return k;
                }
            }
        }
        return result;
    }

    private int computeMaxSum(int[] arr) {
        long currentMax = Integer.MIN_VALUE, maxSum = 0;
        for (int n : arr) {
            currentMax = Math.max(currentMax + n, n);
            maxSum = Math.max(currentMax, maxSum);
        }
        return (int) maxSum;
    }

    private void evalArrForK(int[] arr, int k) {
        int maxSum = computeMaxSum(arr);
        if (maxSum <= k) {
            result = Math.max(result, maxSum);
        } else {
            int sum = 0;
            TreeSet<Integer> sortedPrefixSum = new TreeSet<>();
            sortedPrefixSum.add(0);
            for (int n : arr) {
                sum += n;
                Integer closestDelta = sortedPrefixSum.ceiling(sum - k);
                if (closestDelta != null) result = Math.max(result, sum - closestDelta);
                sortedPrefixSum.add(sum);
            }
        }
    }

    @Test
    public void check1() {
        final String matrix = "[[1,0,1],[0,-2,3]]";
        final int k = 2;
        Assertions.assertEquals(2, maxSumSubmatrix(Utils.to2dIntMatrix(matrix), k));
    }

    @Test
    public void check3() {
        final String matrix = "[[2,2,-1]]";
        final int k = 3;
        Assertions.assertEquals(3, maxSumSubmatrix(Utils.to2dIntMatrix(matrix), k));
    }

    @Test
    public void check4() {
        final String matrix = "[[27,5,-20,-9,1,26,1,12,7,-4,8,7,-1,5,8],[16,28,8,3,16,28,-10,-7,-5,-13,7,9,20,-9,26],[24,-14,20,23,25,-16,-15,8,8,-6,-14,-6,12,-19,-13],[28,13,-17,20,-3,-18,12,5,1,25,25,-14,22,17,12],[7,29,-12,5,-5,26,-5,10,-5,24,-9,-19,20,0,18],[-7,-11,-8,12,19,18,-15,17,7,-1,-11,-10,-1,25,17],[-3,-20,-20,-7,14,-12,22,1,-9,11,14,-16,-5,-12,14],[-20,-4,-17,3,3,-18,22,-13,-1,16,-11,29,17,-2,22],[23,-15,24,26,28,-13,10,18,-6,29,27,-19,-19,-8,0],[5,9,23,11,-4,-20,18,29,-6,-4,-11,21,-6,24,12],[13,16,0,-20,22,21,26,-3,15,14,26,17,19,20,-5],[15,1,22,-6,1,-9,0,21,12,27,5,8,8,18,-1],[15,29,13,6,-11,7,-6,27,22,18,22,-3,-9,20,14],[26,-6,12,-10,0,26,10,1,11,-10,-16,-18,29,8,-8],[-19,14,15,18,-10,24,-9,-7,-19,-14,23,23,17,-5,6]]";
        final int k = -100;
        Assertions.assertEquals(-101, maxSumSubmatrix(Utils.to2dIntMatrix(matrix), k));
    }
}

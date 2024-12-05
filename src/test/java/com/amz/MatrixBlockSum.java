package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/matrix-block-sum/"
 */
public class MatrixBlockSum {

    public int[][] matrixBlockSum(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int sum = 0;
                for (int r = Math.max(i - k, 0); r <= Math.min(i + k, m - 1); r++) {
                    for (int c = Math.max(j - k, 0); c <= Math.min(j + k, n - 1); c++) {
                        sum += mat[r][c];
                    }
                }
                ans[i][j] = sum;
            }
        }
        return ans;
    }

    @Test
    public void check1() {
        Assertions.assertEquals("[[12,21,16],[27,45,33],[24,39,28]]",
                Utils.from2dIntMatrix(matrixBlockSum(Utils.to2dIntMatrix("[[1,2,3],[4,5,6],[7,8,9]]"), 1)));
    }

    @Test
    public void check2() {
        Assertions.assertEquals("[[45,45,45],[45,45,45],[45,45,45]]",
                Utils.from2dIntMatrix(matrixBlockSum(Utils.to2dIntMatrix("[[1,2,3],[4,5,6],[7,8,9]]"), 2)));
    }
}

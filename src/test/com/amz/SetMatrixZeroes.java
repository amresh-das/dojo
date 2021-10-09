package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @see "https://leetcode.com/problems/set-matrix-zeroes/"
 */
public class SetMatrixZeroes {

    public void setZeroes(int[][] matrix) {
        final List<int[]> zeroElements = new ArrayList<>();
        final int m = matrix.length;
        final int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    zeroElements.add(new int[] {i, j});
                }
            }
        }

        for (int[] zIndex : zeroElements) {
            for (int i = 0; i < m; i++) {
                matrix[i][zIndex[1]] = 0;
            }
            for (int j = 0; j < n; j++) {
                matrix[zIndex[0]][j] = 0;
            }
        }
    }

    @Test
    void t1() {
        verify("[[1,1,1],[1,0,1],[1,1,1]]", "[[1,0,1],[0,0,0],[1,0,1]]");
    }

    @Test
    void t2() {
        verify("[[0,1,2,0],[3,4,5,2],[1,3,1,5]]", "[[0,0,0,0],[0,4,5,0],[0,3,1,0]]");
    }

    private void verify(final String input, final String expected) {
        int[][] in = Utils.to2dIntMatrix(input);
        setZeroes(in);
        Assertions.assertArrayEquals(Utils.to2dIntMatrix(expected), in);
    }
}

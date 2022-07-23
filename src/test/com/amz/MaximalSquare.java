package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/maximal-square/submissions/"
 */
public class MaximalSquare {

    public int maximalSquare(char[][] matrix) {
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    max = Math.max(max, explore(matrix, i, j));
                }
            }
        }
        return max;
    }

    private int explore(char[][] matrix, int x, int y) {
        int count = 1;
        while (count <= Math.min(matrix.length - x, matrix[0].length - y)) {
            for (int i = 0; i < count; i++) {
                if (matrix[x + i][y + count - 1] == '0' || matrix[x + count - 1][y + i] == '0') {
                    return (count - 1) * (count - 1);
                }
            }
            if (matrix[x + count - 1][y + count - 1] == '0') return (count - 1) * (count - 1);
            count++;
        }
        return (int) Math.pow(Math.min(matrix.length - x, matrix[0].length - y), 2);
    }

    @Test
    public void check1() {
        Assertions.assertEquals(4, maximalSquare(Utils.to2dCharMatrix("[[\"1\",\"0\",\"1\",\"0\",\"0\"],[\"1\",\"0\",\"1\",\"1\",\"1\"],[\"1\",\"1\",\"1\",\"1\",\"1\"],[\"1\",\"0\",\"0\",\"1\",\"0\"]]")));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(1, maximalSquare(Utils.to2dCharMatrix("[[\"0\",\"1\"],[\"1\",\"0\"]]")));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(0, maximalSquare(Utils.to2dCharMatrix("[[\"0\"]]")));
    }

    @Test
    public void check4() {
        Assertions.assertEquals(1, maximalSquare(Utils.to2dCharMatrix("[[\"1\"]]")));
    }

    @Test
    public void check5() {
        Assertions.assertEquals(4, maximalSquare(Utils.to2dCharMatrix("[[\"1\",\"1\"],[\"1\",\"1\"]]")));
    }

    @Test
    public void check6() {
        Assertions.assertEquals(4, maximalSquare(Utils.to2dCharMatrix("[" +
                "[\"0\",\"1\",\"1\",\"0\",\"0\",\"1\",\"0\",\"1\",\"0\",\"1\"]," +
                "[\"0\",\"0\",\"1\",\"0\",\"1\",\"0\",\"1\",\"0\",\"1\",\"0\"]," +
                "[\"1\",\"0\",\"0\",\"0\",\"0\",\"1\",\"0\",\"1\",\"1\",\"0\"]," +
                "[\"0\",\"1\",\"1\",\"1\",\"1\",\"1\",\"1\",\"0\",\"1\",\"0\"]," +
                "[\"0\",\"0\",\"1\",\"1\",\"1\",\"1\",\"1\",\"1\",\"1\",\"0\"]," +
                "[\"1\",\"1\",\"0\",\"1\",\"0\",\"1\",\"1\",\"1\",\"1\",\"0\"]," +
                "[\"0\",\"0\",\"0\",\"1\",\"1\",\"0\",\"0\",\"0\",\"1\",\"0\"]," +
                "[\"1\",\"1\",\"0\",\"1\",\"1\",\"0\",\"0\",\"1\",\"1\",\"1\"]," +
                "[\"0\",\"1\",\"0\",\"1\",\"1\",\"0\",\"1\",\"0\",\"1\",\"1\"]]")));
    }
}

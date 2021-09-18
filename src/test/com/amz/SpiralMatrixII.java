package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/spiral-matrix-ii/"
 */
public class SpiralMatrixII {

    public int[][] generateMatrix(int n) {
        final int[][] m = new int[n][n];
        final int max = n * n;
        int r = -1, c = -1, current = 0, rows = n, cols = n;

        while (current < max) {
            r++;
            c++;
            for (int i = 0; i < cols && current < max; i++) {
                m[r][c++] = ++current;
            }
            rows--;

            r++;
            c--;
            for (int i = 0; i < rows && current < max; i++) {
                m[r++][c] = ++current;
            }
            cols--;

            r--;
            c--;
            for (int i = 0; i < cols && current < max; i++) {
                m[r][c--] = ++current;
            }
            rows--;

            r--;
            c++;
            for (int i = 0; i < rows && current < max; i++) {
                m[r--][c] = ++current;
            }
            cols--;

        }
        return m;
    }

    @Test
    void t1() {
        Assertions.assertEquals("[[1]]", Utils.from2dIntMatrix(generateMatrix(1)));
    }

    @Test
    void t2() {
        Assertions.assertEquals("[[1,2],[4,3]]", Utils.from2dIntMatrix(generateMatrix(2)));
    }

    @Test
    void t3() {
        Assertions.assertEquals("[[1,2,3],[8,9,4],[7,6,5]]", Utils.from2dIntMatrix(generateMatrix(3)));
    }

    @Test
    void t4() {
        Assertions.assertEquals("[[1,2,3,4],[12,13,14,5],[11,16,15,6],[10,9,8,7]]", Utils.from2dIntMatrix(generateMatrix(4)));
    }
}

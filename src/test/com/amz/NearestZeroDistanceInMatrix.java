package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @see "https://leetcode.com/problems/01-matrix/"
 */
public class NearestZeroDistanceInMatrix {

    public int[][] updateMatrix(int[][] mat) {
        int[][] result = new int[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            Arrays.fill(result[i], 999999);
        }

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 0) {
                    result[i][j] = 0;
                } else {
                    if (j > 0) {
                        result[i][j] = Math.min(result[i][j], result[i][j - 1] + 1);
                    }
                    if (i > 0) {
                        result[i][j] = Math.min(result[i][j], result[i - 1][j] + 1);
                    }
                }
            }
        }
        for (int i = mat.length - 1; i >= 0; i--) {
            for (int j = mat[0].length - 1; j >= 0; j--) {
                if (mat[i][j] == 0) {
                    result[i][j] = 0;
                } else {
                    if (j < mat[0].length - 1) {
                        result[i][j] = Math.min(result[i][j], result[i][j + 1] + 1);
                    }
                    if (i < mat.length - 1) {
                        result[i][j] = Math.min(result[i][j], result[i + 1][j] + 1);
                    }
                }
            }
        }

        return result;
    }

    @Test
    public void check1() {
        Assertions.assertEquals("[[0,0,0],[0,1,0],[0,0,0]]", Utils.from2dIntMatrix(updateMatrix(Utils.to2dIntMatrix("[[0,0,0],[0,1,0],[0,0,0]]"))));
    }

    @Test
    public void check2() {
        Assertions.assertEquals("[[0,0,0],[0,1,0],[1,2,1]]", Utils.from2dIntMatrix(updateMatrix(Utils.to2dIntMatrix("[[0,0,0],[0,1,0],[1,1,1]]"))));
    }

    @Test
    public void check3() {

        Assertions.assertEquals("[[2,1,0,0,1,0,0,1,1,0],[1,0,0,1,0,1,1,2,2,1],[1,1,1,0,0,1,2,2,1,0],[0,1,2,1,0,1,2,3,2,1],[0,0,1,2,1,2,1,2,1,0],[1,1,2,3,2,1,0,1,1,1],[0,1,2,3,2,1,1,0,0,1],[1,2,1,2,1,0,0,1,1,2],[0,1,0,1,1,0,1,2,2,3],[1,2,1,0,1,0,1,2,3,4]]"
                , Utils.from2dIntMatrix(updateMatrix(Utils.to2dIntMatrix("[[1,1,0,0,1,0,0,1,1,0],[1,0,0,1,0,1,1,1,1,1],[1,1,1,0,0,1,1,1,1,0],[0,1,1,1,0,1,1,1,1,1],[0,0,1,1,1,1,1,1,1,0],[1,1,1,1,1,1,0,1,1,1],[0,1,1,1,1,1,1,0,0,1],[1,1,1,1,1,0,0,1,1,1],[0,1,0,1,1,0,1,1,1,1],[1,1,1,0,1,0,1,1,1,1]]"))));
    }

}

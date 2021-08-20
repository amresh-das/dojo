package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RotateImage {
    public void rotate(int[][] matrix) {
        rotateDiagonally(matrix);
        rotateVertically(matrix);
    }

    private void rotateVertically(final int[][] matrix) {
        int mid = (matrix.length - 1) / 2;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j <= mid; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length - j - 1];
                matrix[i][matrix.length - j - 1] = temp;
            }
        }
    }

    private void rotateDiagonally(final int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix.length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    @Test
    void t1() {
        int[][] matrix = Utils.to2dIntMatrix("[[1,2,3],[4,5,6],[7,8,9]]");
        rotate(matrix);
        Assertions.assertEquals("[[7,4,1],[8,5,2],[9,6,3]]", Utils.from2dIntMatrix(matrix));
    }

    @Test
    void t2() {
        int[][] matrix = Utils.to2dIntMatrix("[[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]");
        rotate(matrix);
        Assertions.assertEquals("[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]", Utils.from2dIntMatrix(matrix));
    }
}

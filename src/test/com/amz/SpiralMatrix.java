package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @see "https://leetcode.com/problems/spiral-matrix/"
 */
public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        final List<Integer> output = new ArrayList<>();
        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        int r = -1, c = -1, count = 0;
        while (rowLen > 0 && colLen > 0) {
            r++;
            c++;
            for (int i = 0; rowLen > 0 && i < colLen; i++, count++) {
                output.add(matrix[r][c++]);
            }
            rowLen--;
            r++;
            c--;
            for (int i = 0; i < rowLen && colLen > 0; i++, count++) {
                output.add(matrix[r++][c]);
            }
            colLen--;
            r--;
            c--;
            for (int i = 0; rowLen > 0 && i < colLen; i++, count++) {
                output.add(matrix[r][c--]);
            }
            rowLen--;
            r--;
            c++;
            for (int i = 0; i < rowLen && colLen > 0; i++, count++) {
                output.add(matrix[r--][c]);
            }
            colLen--;
        }
        return output;
    }

    @Test
    void t1() {
        verify("[[1,2,3],[4,5,6],[7,8,9]]", "[1,2,3,6,9,8,7,4,5]");
    }

    @Test
    void t2() {
        verify("[[1,2,3,4],[5,6,7,8],[9,10,11,12]]", "[1,2,3,4,8,12,11,10,9,5,6,7]");
    }

    private void verify(final String input, final String expected) {
        Assertions.assertArrayEquals(Utils.stringToIntArray(expected), spiralOrder(Utils.to2dIntMatrix(input)).stream().mapToInt(i -> i).toArray());
    }
}

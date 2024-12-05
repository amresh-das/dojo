package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

/**
 * @see "https://leetcode.com/problems/range-sum-query-2d-immutable/"
 * @see "https://leetcode.com/problems/range-sum-query-2d-mutable/"
 */
public class RangeSumQuery2D {

    class NumMatrix {
        private final int[][] rowPrefixSum;
        public NumMatrix(int[][] matrix) {
            rowPrefixSum = new int[matrix.length][matrix[0].length + 1];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    rowPrefixSum[i][j + 1] = rowPrefixSum[i][j] + matrix[i][j];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return IntStream.range(row1, row2 + 1)
                    .boxed()
                    .map(r -> rowPrefixSum[r][col2 + 1] - rowPrefixSum[r][col1])
                    .reduce(0, Integer::sum);
        }

        public void update(int row, int col, int val) {
            int original = rowPrefixSum[row][col + 1] - rowPrefixSum[row][col];
            int diff = val - original;
            for (int j = col + 1; j < rowPrefixSum[0].length; j++) {
                rowPrefixSum[row][j] += diff;
            }
        }

    }

    @Test
    public void check1() {
        NumMatrix numMatrix = new NumMatrix(Utils.to2dIntMatrix("[[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]"));
        Assertions.assertEquals(8, numMatrix.sumRegion(2, 1, 4, 3));
        Assertions.assertEquals(11, numMatrix.sumRegion(1, 1, 2, 2));
        Assertions.assertEquals(12, numMatrix.sumRegion(1, 2, 2, 4));
    }

    @Test
    public void check2() {
        NumMatrix numMatrix = new NumMatrix(Utils.to2dIntMatrix("[[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]"));
        Assertions.assertEquals(8, numMatrix.sumRegion(2, 1, 4, 3));
        numMatrix.update(3, 2, 2);
        Assertions.assertEquals(10, numMatrix.sumRegion(2, 1, 4, 3));
    }

    @Test
    public void check3() {
        NumMatrix numMatrix = new NumMatrix(Utils.to2dIntMatrix("[[1]]"));
        Assertions.assertEquals(1, numMatrix.sumRegion(0, 0, 0, 0));
        numMatrix.update(0, 0, -1);
        Assertions.assertEquals(-1, numMatrix.sumRegion(0, 0, 0, 0));
    }

}

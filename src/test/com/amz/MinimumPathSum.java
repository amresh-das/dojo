package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MinimumPathSum {

    public int minPathSum(int[][] grid) {
        final int m = grid.length, n = grid[0].length;
        final int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0 && j > 0) {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
                } else if (i > 0) {
                    dp[i][j] = dp[i-1][j] + grid[i][j];
                } else if (j > 0) {
                    dp[i][j] = dp[i][j-1] + grid[i][j];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    @Test
    void t1() {
        verify("[[1,3,1],[1,5,1],[4,2,1]]", 7);
    }

    @Test
    void t2() {
        verify("[[1,2,3],[4,5,6]]", 12);
    }

    private void verify(final String input, final int expected) {
        Assertions.assertEquals(expected, minPathSum(Utils.to2dIntMatrix(input)));
    }
}

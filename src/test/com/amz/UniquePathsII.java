package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/unique-paths-ii/"
 */
public class UniquePathsII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        final int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                    continue;
                }
                if (i > 0 && j > 0) {
                    dp[i][j] = dp[i][j-1] + dp[i-1][j];
                } else if (i > 0) {
                    dp[i][j] = dp[i-1][j];
                } else if (j > 0) {
                    dp[i][j] = dp[i][j-1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    @Test
    void t1() {
        verify("[[0,0,0],[0,1,0],[0,0,0]]", 2);
    }

    @Test
    void t2() {
        verify("[[0,1],[0,0]]", 1);
    }

    private void verify(final String input, final int expected) {
        Assertions.assertEquals(expected, uniquePathsWithObstacles(Utils.to2dIntMatrix(input)));
    }
}

package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/diagonal-traverse/"
 */
public class DiagonalTraverse {

    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] ans = new int[m * n];
        int ansIndex = 0;
        int r = 0, c = 0;
        while (r < m && c < n) {
            while (r >= 0 && c <= n - 1) {
                ans[ansIndex++] = mat[r][c];
                if (r == m - 1 && c == n - 1) return ans;
                r--;
                c++;
            }
            r++;
            if (c == n) {
                c--;
                r++;
            }
            while (c >= 0 && r <= m - 1) {
                ans[ansIndex++] = mat[r][c];
                if (r == m - 1 && c == n - 1) return ans;
                r++;
                c--;
            }
            if (r == m) {
                r--;
                c++;
            }
            c++;
        }
        return ans;
    }

    @Test
    public void check1() {
        String grid = "[[1,2,3],[4,5,6],[7,8,9]]";
        String expected = "[1,2,4,7,5,3,6,8,9]";
        Assertions.assertEquals(expected, Utils.toString(findDiagonalOrder(Utils.to2dIntMatrix(grid))));
    }

    @Test
    public void check2() {
        String grid = "[[1,2],[3,4]]";
        String expected = "[1,2,3,4]";
        Assertions.assertEquals(expected, Utils.toString(findDiagonalOrder(Utils.to2dIntMatrix(grid))));
    }

}

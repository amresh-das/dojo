package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/search-a-2d-matrix-ii/"
 */
public class SearchA2DMatrixII {

    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length - 1;
        int col = 0;
        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] == target) return true;
            if (matrix[row][col] > target) {
                row--;
            } else {
                col++;
            }
        }
        return false;
    }

    @Test
    public void check1() {
        String grid = "[[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]]";
        int target = 5;
        Assertions.assertEquals(true, searchMatrix(Utils.to2dIntMatrix(grid), target));
    }

    @Test
    public void check2() {
        String grid = "[[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]]";
        int target = 20;
        Assertions.assertEquals(false, searchMatrix(Utils.to2dIntMatrix(grid), target));
    }

    @Test
    public void check3() {
        String grid = "[[1,4],[2,5]]";
        int target = 2;
        Assertions.assertEquals(true, searchMatrix(Utils.to2dIntMatrix(grid), target));
    }

    @Test
    public void check4() {
        String grid = "[[-5]]";
        int target = -2;
        Assertions.assertEquals(false, searchMatrix(Utils.to2dIntMatrix(grid), target));
    }

    @Test
    public void check5() {
        String grid = "[[-1,3]]";
        int target = 3;
        Assertions.assertEquals(true, searchMatrix(Utils.to2dIntMatrix(grid), target));
    }

    @Test
    public void check6() {
        String grid = "[[-1,3]]";
        int target = -1;
        Assertions.assertEquals(true, searchMatrix(Utils.to2dIntMatrix(grid), target));
    }

    @Test
    public void check7() {
        String grid = "[[1,2,3,4,5],[6,7,8,9,10],[11,12,13,14,15],[16,17,18,19,20],[21,22,23,24,25]]";
        int target = 19;
        Assertions.assertEquals(true, searchMatrix(Utils.to2dIntMatrix(grid), target));
    }
}

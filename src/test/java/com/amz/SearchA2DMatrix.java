package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/search-a-2d-matrix/"
 */
public class SearchA2DMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        final int m = matrix.length;
        if (m == 0) return false;
        final int n = matrix[0].length;
        if (n == 0) return false;
        return binarySearch(matrix, target, n, 0, matrix.length * matrix[0].length - 1);
    }

    private boolean binarySearch(final int[][] matrix, final int target, final int n, final int low, final int high) {
        int mid = (low + high) / 2;
        int term = matrix[mid / n][mid % n];
        if (term == target) return true;
        if (term < target) {
            return mid < high && binarySearch(matrix, target, n, mid + 1, high);
        } else {
            return low < mid && binarySearch(matrix, target, n, low, mid - 1);
        }
    }

    @Test
    void t1() {
        verify("[[1,3,5,7],[10,11,16,20],[23,30,34,60]]", 3, true);
    }

    @Test
    void t2() {
        verify("[[1,3,5,7],[10,11,16,20],[23,30,34,60]]", 13, false);
    }

    @Test
    void t3() {
        verify("[[1,1]]", 2, false);
    }

    private void verify(final String matrix, final int searchTerm, final boolean expected) {
        Assertions.assertEquals(expected, searchMatrix(Utils.to2dIntMatrix(matrix), searchTerm));
    }
}

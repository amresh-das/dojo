package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @see "https://leetcode.com/problems/sort-the-matrix-diagonally/"
 */
public class SortTheMatrixDiagonally {

    public int[][] diagonalSort(int[][] mat) {
        final Map<Integer, Queue<Integer>> map = new HashMap<>();
        for (int r = 0; r < mat.length; r++) {
            for (int c = 0; c < mat[0].length; c++) {
                map.computeIfAbsent(r - c, k -> new PriorityQueue<>()).offer(mat[r][c]);
            }
        }
        for (int r = 0; r < mat.length; r++) {
            for (int c = 0; c < mat[0].length; c++) {
                mat[r][c] = map.get(r - c).poll();
            }
        }
        return mat;
    }

    @Test
    public void check1() {
        Assertions.assertEquals("[[1,1,1,1],[1,2,2,2],[1,2,3,3]]", Utils.toString(diagonalSort(Utils.to2dIntMatrix("[[3,3,1,1],[2,2,1,2],[1,1,1,2]]"))));
    }

}

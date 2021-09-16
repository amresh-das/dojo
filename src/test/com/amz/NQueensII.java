package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @see "https://leetcode.com/problems/n-queens-ii/"
 */
public class NQueensII {

    public int totalNQueens(int n) {
        final AtomicInteger solutionCount = new AtomicInteger() ;
        final char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        fillRow(0, board, n, solutionCount);


        return solutionCount.get();
    }

    private void fillRow(final int row, final char[][] board, final int n, final AtomicInteger solutionCount) {
        if (row == n) {
            solutionCount.incrementAndGet();
        } else {
            for (int col = 0; col < n; col++) {
                if (isValidQueenSpot(board, n, row, col)) {
                    board[row][col] = 'Q';
                    fillRow(row + 1, board, n, solutionCount);
                    board[row][col] = '.';
                }
            }
        }
    }

    private boolean isValidQueenSpot(final char[][] board, final int size, final int i, final int j) {
        char n, s, e, w, ne, nw, se, sw;
        for (int k = 1; k < size; k++) {
            n = i - k >= 0 ? board[i - k][j] : '.';
            s = i + k < size ? board[i + k][j] : '.';
            e = j - k >= 0 ? board[i][j - k] : '.';
            w = j + k < size ? board[i][j + k] : '.';
            ne = i - k >= 0 && j - k >= 0 ? board[i - k][j - k] : '.';
            nw = i - k >= 0 && j + k < size ? board[i - k][j + k] : '.';
            se = i + k < size && j - k >= 0 ? board[i + k][j - k] : '.';
            sw = i + k < size && j + k < size ? board[i + k][j + k] : '.';
            if (n == 'Q' || s == 'Q' || e == 'Q' || w == 'Q' || ne == 'Q' || nw == 'Q' || se == 'Q' || sw == 'Q') {
                return false;
            }
        }
        return true;
    }

    @Test
    void t1() {
        verify(1, 1);
    }

    @Test
    void t2() {
        verify(4, 2);
    }

    private void verify(final int input, final int expected) {
        Assertions.assertEquals(expected, totalNQueens(input));
    }

}

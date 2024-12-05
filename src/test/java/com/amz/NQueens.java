package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @see "https://leetcode.com/problems/n-queens/"
 */
public class NQueens {

    public List<List<String>> solveNQueens(final int n) {
        final List<List<String>> solutions = new ArrayList<>();
        final char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        fillRow(0, board, n, solutions);


        return solutions;
    }

    private void fillRow(final int row, final char[][] board, final int n, final List<List<String>> solutions) {
        if (row == n) {
            solutions.add(Arrays.stream(board).map(String::new).collect(Collectors.toList()));
        } else {
            for (int col = 0; col < n; col++) {
                if (isValidQueenSpot(board, n, row, col)) {
                    board[row][col] = 'Q';
                    fillRow(row + 1, board, n, solutions);
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
        verify(1, "[[\"Q\"]]");
    }

    @Test
    void t2() {
        verify(4, "[[\".Q..\",\"...Q\",\"Q...\",\"..Q.\"],[\"..Q.\",\"Q...\",\"...Q\",\".Q..\"]]");
    }

    private void verify(final int input, final String expected) {
        Assertions.assertEquals(expected.replaceAll("\"", ""), Utils.from2dMatrix(solveNQueens(input)));
    }

}

package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SudokuSolver {

    public void solveSudoku(char[][] board) {
        if (board == null || board.length != 9) {
            return;
        }
        solve(board);
    }

    private boolean solve(final char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char d = '1'; d <= '9'; d++) {
                        if (isValid(board, d, i, j)) {
                            board[i][j] = d;
                            if (solve(board)) {
                                return true;
                            } else {
                                board[i][j] = '.';
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(final char[][] board, final char d, final int row, final int col) {
        for (int i = 0; i < 9; i++) {
            final char rowSibling = board[row][i];
            final char colSibling = board[i][col];
            final char gridSibling = board[(row / 3) * 3 + i / 3][(col / 3) * 3 + i % 3];
            if (rowSibling != '.' && rowSibling == d) return false;
            if (colSibling != '.' && colSibling == d) return false;
            if (gridSibling != '.' && gridSibling == d) return false;
        }
        return true;
    }


    @Test
    void shouldSolve() {
        final char[][] board = new char[][] {
                  {'5','3','.','.','7','.','.','.','.'}
                , {'6','.','.','1','9','5','.','.','.'}
                , {'.','9','8','.','.','.','.','6','.'}
                , {'8','.','.','.','6','.','.','.','3'}
                , {'4','.','.','8','.','3','.','.','1'}
                , {'7','.','.','.','2','.','.','.','6'}
                , {'.','6','.','.','.','.','2','8','.'}
                , {'.','.','.','4','1','9','.','.','5'}
                , {'.','.','.','.','8','.','.','7','9'}
        };
        solveSudoku(board);
        final ValidSudoku sudokuValidator = new ValidSudoku();
        Assertions.assertTrue(sudokuValidator.isCompleteSudoku(board) && sudokuValidator.isValidSudoku(board));
    }
}

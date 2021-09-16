package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @see "https://leetcode.com/problems/valid-sudoku/"
 */
public class ValidSudoku {
    private static final int BOARD_SIZE = 9;
    private static final int QUADRANT_SIZE = 3;

    public boolean isValidSudoku(char[][] board) {
        final Map<Integer,Set<Character>> rowDigits = new HashMap<>();
        final Map<Integer,Set<Character>> colDigits = new HashMap<>();
        final Map<Integer,Set<Character>> gridDigits = new HashMap<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                final Set<Character> rowSet = rowDigits.compute(i, (k,v) -> v == null ? new HashSet<>() : v);
                final Set<Character> colSet = colDigits.compute(j, (k,v) -> v == null ? new HashSet<>() : v);
                final Set<Character> gridSet = gridDigits.compute(getGridIndex(i, j), (k,v) -> v == null ? new HashSet<>() : v);
                final Character digit = board[i][j];
                if (isInvalid(rowSet, digit)) return false;
                if (isInvalid(colSet, digit)) return false;
                if (isInvalid(gridSet, digit)) return false;
            }
        }
        return true;
    }

    public boolean isCompleteSudoku(char[][] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == '.') return false;
            }
        }
        return true;
    }

    private int getGridIndex(final int row, final int col) {
        return (row / QUADRANT_SIZE) * QUADRANT_SIZE + col / QUADRANT_SIZE;
    }

    private boolean isInvalid(final Set<Character> set, final Character digit) {
        if (set.contains(digit)) {
            return true;
        }
        if (digit != '.') {
            set.add(digit);
        }
        return false;
    }

    @Test
    void shouldBeValid() {
        final char[][] board = new char[][]{
                  {'5', '3', '.', '.', '7', '.', '.', '.', '.'}
                , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        Assertions.assertTrue(isValidSudoku(board));
    }

    @Test
    void shouldBeInvalidOnRow() {
        final char[][] board = new char[][]{
                  {'8', '3', '.', '.', '7', '.', '.', '.', '.'}
                , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        Assertions.assertFalse(isValidSudoku(board));
    }

    @Test
    void shouldBeInvalidOnCol() {
        final char[][] board = new char[][]{
                  {'5', '3', '.', '.', '5', '.', '.', '.', '.'}
                , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        Assertions.assertFalse(isValidSudoku(board));
    }

    @Test
    void shouldBeInvalidOnGrid() {
        final char[][] board = new char[][]{
                  {'5', '3', '.', '.', '7', '.', '.', '.', '.'}
                , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                , {'.', '9', '5', '.', '.', '.', '.', '6', '.'}
                , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        Assertions.assertFalse(isValidSudoku(board));
    }
}

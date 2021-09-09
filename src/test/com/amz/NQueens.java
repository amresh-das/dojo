package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens {

    public List<List<String>> solveNQueens(final int n) {
        final List<List<String>> solutions = new ArrayList<>();
        for (int row = 0; row < n; row++) {
            
        }
        return solutions;
    }

    private boolean isValid(final char[] board, final int n) {
        for (int p = 0; p < board.length; p++) {
            char c = board[p];
            if (c == 'Q') {
                final int row = p / n;
                final int col = p % n;


            }
        }
        return false;
    }



    @Test
    public void t1() {
        verify(4, "[[\".Q..\",\"...Q\",\"Q...\",\"..Q.\"],[\"..Q.\",\"Q...\",\"...Q\",\".Q..\"]]");
    }

    @Test
    public void t2() {
        verify(1, "[[\"Q\"]]");
    }

    private void verify(final int input, final String expected) {
        Assertions.assertEquals(expected.replaceAll("\"", ""), Utils.from2dMatrix(solveNQueens(input)));
    }

}

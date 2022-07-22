package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * TODO
 * @see "https://leetcode.com/problems/sliding-puzzle/"
 */
public class SlidingPuzzle {
    private final int[][] target = new int[][] {new int[] {1,2,3}, new int[] {4,5,0}};

    public int slidingPuzzle(int[][] board) {
        final Map<String, Integer> visited = new HashMap<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 0) {
                    int moves = slide(board, i, j, toString(target), visited, 0);
                    return moves == Integer.MAX_VALUE ? -1 : moves;
                }
            }
        }
        return -1;
    }

    private int slide(int[][] board, int x, int y, String target, Map<String, Integer> visited, int moves) {
        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length) return -1;
        String str = toString(board);
        if (str.equals(target)) return 0;
        if (visited.containsKey(str)) return Integer.MAX_VALUE;
        int min = Integer.MAX_VALUE;

        //slide left
        if (y > 0) {
            int temp = board[x][y - 1];
            board[x][y] = temp;
            board[x][y - 1] = 0;
            min = Math.min(min, slide(board, x, y - 1, target, visited, moves));
            board[x][y - 1] = temp;
            board[x][y] = 0;
        }

        //slide right

        if (y < board[0].length - 1) {
            int temp = board[x][y + 1];
            board[x][y] = temp;
            board[x][y + 1] = 0;
            min = Math.min(min, slide(board, x, y + 1, target, visited, moves));
            board[x][y + 1] = temp;
            board[x][y] = 0;
        }

        //slide up
        if (x > 0) {
            int temp = board[x - 1][y];
            board[x][y] = temp;
            board[x - 1][y] = 0;
            min = Math.min(min, slide(board, x - 1, y, target, visited, moves));
            board[x - 1][y] = temp;
            board[x][y] = 0;
        }

        //slide down
        if (x < board.length - 1) {
            int temp = board[x + 1][y];
            board[x][y] = temp;
            board[x + 1][y] = 0;
            min = Math.min(min, slide(board, x + 1, y, target, visited, moves));
            board[x + 1][y] = temp;
            board[x][y] = 0;
        }

        visited.put(str, min);
        return moves + min;
    }

    private String toString(int[][] board) {
        return Arrays.stream(board).map(r -> Arrays.stream(r).mapToObj(Integer::toString).collect(Collectors.joining())).collect(Collectors.joining());
    }

    @Test
    public void check1() {
        Assertions.assertEquals(1, slidingPuzzle(Utils.to2dIntMatrix("[[1,2,3],[4,0,5]]")));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(-1, slidingPuzzle(Utils.to2dIntMatrix("[[1,2,3],[5,4,0]]")));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(5, slidingPuzzle(Utils.to2dIntMatrix("[[4,1,2],[5,0,3]]")));
    }
}

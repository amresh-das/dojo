package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @see "https://leetcode.com/problems/minimum-number-of-flips-to-convert-binary-matrix-to-zero-matrix"
 */
public class BinaryMatrixMinFlipsToZero {

    @Test
    void t1() {
        int[][] mat = new int[][] {
            new int[] {0, 0},
            new int[] {0, 1}
        };
        Assertions.assertEquals(3, minFlips(mat));
    }

    @Test
    void t2() {
        int[][] mat = new int[][] {
            new int[] {0}
        };
        Assertions.assertEquals(0, minFlips(mat));
    }

    @Test
    void t3() {
        int[][] mat = new int[][] {
            new int[] {1,1,1},
            new int[] {1,0,1},
            new int[] {0,0,0}
        };
        Assertions.assertEquals(6, minFlips(mat));
    }

    @Test
    void t4() {
        int[][] mat = new int[][] {
            new int[] {1,0,0},
            new int[] {1,0,0}
        };
        Assertions.assertEquals(-1, minFlips(mat));
    }

    class State {
        int[][] matrix;
        int level;

        public State(int[][] matrix, int level) {
            this.matrix = matrix;
            this.level = level;
        }
    }

    public int minFlips(int[][] mat) {
        Set<String> visited = new HashSet<>();
        PriorityQueue<State> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.level));
        queue.offer(new State(mat, 0));
        int m = mat.length;
        int n = mat[0].length;

        while (!queue.isEmpty()) {
            State state = queue.poll();
            int[][] matrix = state.matrix;
            if (isZero(matrix)) return state.level;
            visited.add(toString(matrix));
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    int[][] next = flip(matrix, r, c);
                    if (!visited.contains(toString(next))) {
                        queue.offer(new State(next, state.level + 1));
                    }
                }
            }
        }
        return -1;
    }

    private String toString(int[][] mat) {
        return Arrays.stream(mat).map(row -> Arrays.stream(row).mapToObj(Objects::toString).collect(Collectors.joining(""))).collect(Collectors.joining("\n"));
    }

    private int[][] flip(int[][] mat, int r, int c) {
        int[][] flipped = new int[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (
                        (i == r && j == c) ||
                        (i == r + 1 && j == c) ||
                        (i == r - 1 && j == c) ||
                        (i == r && j == c + 1) ||
                        (i == r && j == c - 1)
                ) {
                    flipped[i][j] = mat[i][j] == 0 ? 1 : 0;
                } else {
                    flipped[i][j] = mat[i][j];
                }
            }
        }
        return flipped;
    }

    private boolean isZero(int[][] mat) {
        for (int[] row : mat) {
            for (int n : row) {
                if (n != 0) return false;
            }
        }
        return true;
    }
}

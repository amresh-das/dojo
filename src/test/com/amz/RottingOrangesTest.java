package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @see "https://leetcode.com/problems/rotting-oranges/?envType=study-plan-v2&envId=leetcode-75"
 */
public class RottingOrangesTest {

    public int orangesRotting(int[][] grid) {
        var m = grid.length;
        var n = grid[0].length;
        var fresh = 0;
        var queue = new PriorityQueue<int[]>(Comparator.comparingInt(a -> a[0]));
        var mins = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) fresh++;
                if (grid[i][j] == 2) {
                    rot(grid, i, j + 1, 1, queue);
                    rot(grid, i, j - 1, 1, queue);
                    rot(grid, i + 1, j, 1, queue);
                    rot(grid, i - 1, j, 1, queue);
                }
            }
        }
        while (!queue.isEmpty() && fresh > 0) {
            var curr = queue.poll();
            final int i = curr[1];
            final int j = curr[2];
            if (grid[i][j] == 1) {
                fresh--;
                grid[i][j] = 2;
                mins = Math.max(curr[0], mins);
                rot(grid, i, j + 1, curr[0] + 1, queue);
                rot(grid, i, j - 1, curr[0] + 1, queue);
                rot(grid, i + 1, j, curr[0] + 1, queue);
                rot(grid, i - 1, j, curr[0] + 1, queue);
            }
        }
        return fresh == 0 ? mins : -1;
    }

    private void rot(final int[][] grid, final int i, final int j, final int min, final PriorityQueue<int[]> queue) {
        if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == 1) {
            queue.offer(new int[] {min, i, j});
        }
    }

    @Test
    void t1() {
        var grid = new int[][] {
            new int[] {2,1,1},
            new int[] {1,1,0},
            new int[] {0,1,1}
        };
        Assertions.assertEquals(4, orangesRotting(grid));
    }

    @Test
    void t2() {
        var grid = new int[][] {
            new int[] {2,1,1},
            new int[] {0,1,1},
            new int[] {1,0,1}
        };
        Assertions.assertEquals(-1, orangesRotting(grid));
    }

    @Test
    void t3() {
        var grid = new int[][] {
            new int[] {0,2}
        };
        Assertions.assertEquals(0, orangesRotting(grid));
    }
}

package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @see "https://leetcode.com/problems/number-of-islands/"
 */
public class NumberOfIslands {

    public int numIslands(char[][] grid) {
        Set<Integer> visited = new HashSet<>();
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int key = i * grid[0].length + j;
                if (grid[i][j] == '1') {
                    if (!visited.contains(key)) {
                        markVisited(grid, i, j, visited);
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private void markVisited(final char[][] grid, final int i, final int j, final Set<Integer> visited) {
        int key = i * grid[0].length + j;
        if (visited.contains(key)) return;
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) return;
        if (grid[i][j] == '1') {
            visited.add(key);
            markVisited(grid, i + 1, j, visited);
            markVisited(grid, i, j + 1, visited);
            markVisited(grid, i - 1, j, visited);
            markVisited(grid, i, j - 1, visited);
        }
    }

    @Test
    public void check1() {
        Assertions.assertEquals(1, numIslands(new char[][] {
            new char[] {'1','1','1','1','0'},
            new char[] {'1','1','0','1','0'},
            new char[] {'1','1','0','0','0'},
            new char[] {'0','0','0','0','0'}
        }));
    }
}

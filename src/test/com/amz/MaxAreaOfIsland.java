package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class MaxAreaOfIsland {

    public int maxAreaOfIsland(int[][] grid) {
        final Set<Integer> visited = new HashSet<>();
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    maxArea = Math.max(maxArea, calculateIslandArea(grid, i, j, visited));
                }
            }
        }
        return maxArea;
    }

    private int calculateIslandArea(final int[][] grid, final int i, final int j, final Set<Integer> visited) {
        int key = toKey(grid, i, j);
        if (visited.contains(key)) return 0;
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) return 0;
        if (grid[i][j] == 0) return 0;
        visited.add(key);
        return 1 +
                calculateIslandArea(grid, i + 1, j, visited) +
                calculateIslandArea(grid, i, j + 1, visited) +
                calculateIslandArea(grid, i - 1, j, visited) +
                calculateIslandArea(grid, i, j - 1, visited);
    }

    private int toKey(final int[][] grid, final int i, final int j) {
        return i * grid[0].length + j;
    }

    @Test
    public void check1() {
        String grid = "[[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]";
        Assertions.assertEquals(6, maxAreaOfIsland(Utils.to2dIntMatrix(grid)));
    }

    @Test
    public void check2() {
        String grid = "[[0,0,0,0,0,0,0,0]]";
        Assertions.assertEquals(0, maxAreaOfIsland(Utils.to2dIntMatrix(grid)));
    }

}

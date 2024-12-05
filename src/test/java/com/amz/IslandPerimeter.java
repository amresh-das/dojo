package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/island-perimeter/"
 */
public class IslandPerimeter {
    public int islandPerimeter(int[][] grid) {
        int perimeterCount = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    if (j == 0 || grid[i][j - 1] == 0) perimeterCount++; // left
                    if (j == grid[0].length - 1 || grid[i][j + 1] == 0) perimeterCount++; // right
                    if (i == 0 || grid[i - 1][j] == 0) perimeterCount++; // up
                    if (i == grid.length - 1 || grid[i + 1][j] == 0) perimeterCount++; // down
                }
            }
        }
        return perimeterCount;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(16, islandPerimeter(Utils.to2dIntMatrix("[[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]")));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(4, islandPerimeter(Utils.to2dIntMatrix("[[1]]")));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(4, islandPerimeter(Utils.to2dIntMatrix("[[1,0]]")));
    }
}

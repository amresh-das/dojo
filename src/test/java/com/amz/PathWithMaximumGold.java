package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @see "https://leetcode.com/problems/path-with-maximum-gold/"
 */
public class PathWithMaximumGold {

    public int getMaximumGold(int[][] grid) {
        int maxGold = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] > 0) {
                    maxGold = Math.max(maxGold, collectGold(grid, i, j, new HashSet<>(), 0));
                }
             }
        }
        return maxGold;
    }

    private int collectGold(final int[][] grid, final int i, final int j, final Set<Integer> visited, int sum) {
        int key = i * grid[0].length + j;
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) return sum;
        if (grid[i][j] == 0) return sum;
        if (visited.contains(key)) return sum;
        visited.add(key);
        int left =collectGold(grid, i, j - 1, visited, sum + grid[i][j]);
        int right = collectGold(grid, i, j + 1, visited, sum + grid[i][j]);
        int down = collectGold(grid, i + 1, j, visited, sum + grid[i][j]);
        int up =  collectGold(grid, i - 1, j, visited, sum + grid[i][j]);
        visited.remove(key);
        return max(left, right, down, up);
    }

    private int max(int ... values) {
        int max = 0;
        for (int v : values) {
            max = Math.max(max, v);
        }
        return max;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(24, getMaximumGold(Utils.to2dIntMatrix("[[0,6,0],[5,8,7],[0,9,0]]")));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(60, getMaximumGold(Utils.to2dIntMatrix("" +
               "[[1,0,7,0,0,0]," +
                "[2,0,6,0,1,0]," +
                "[3,5,6,7,4,2]," +
                "[4,3,1,0,2,0]," +
                "[3,0,5,0,20,0]]")));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(77, getMaximumGold(Utils.to2dIntMatrix("" +
                "[[0,0,19,5,8]," +
                "[11,20,14,1,0]," +
                "[0,0,1,1,1]," +
                "[0,2,0,2,0]]")));
    }

    @Test
    public void check4() {
        Assertions.assertEquals(129, getMaximumGold(Utils.to2dIntMatrix("" +
                "[[0,0,0,0,0,0,32,0,0,20]," +
                "[0,0,2,0,0,0,0,40,0,32]," +
                "[13,20,36,0,0,0,20,0,0,0]," +
                "[0,31,27,0,19,0,0,25,18,0]," +
                "[0,0,0,0,0,0,0,0,0,0]," +
                "[0,0,0,0,0,0,0,18,0,6]," +
                "[0,0,0,25,0,0,0,0,0,0]," +
                "[0,0,0,21,0,30,0,0,0,0]," +
                "[19,10,0,0,34,0,2,0,0,27]," +
                "[0,0,0,0,0,34,0,0,0,0]]")));
    }
}

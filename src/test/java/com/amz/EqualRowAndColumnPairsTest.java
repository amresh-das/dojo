package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @see "https://leetcode.com/problems/equal-row-and-column-pairs/description/?envType=study-plan-v2&envId=leetcode-75"
 */
public class EqualRowAndColumnPairsTest {

    public int equalPairs(int[][] grid) {
        Map<Integer, String> rows = new HashMap<>();
        Map<Integer, String> cols = new HashMap<>();
        Map<String, Integer> rowCounts = new HashMap<>();
        Map<String, Integer> colCounts = new HashMap<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                final String item = Integer.toString(grid[i][j]);
                final String rowStr = rows.compute(i, (k, v) -> v == null ? item : v + ":" + item);
                final String colStr = cols.compute(j, (k, v) -> v == null ? item : v + ":" + item);
                if (i == grid.length - 1) {
                    colCounts.compute(colStr, (k, v) -> v == null ? 1 : v + 1);
                }
                if (j == grid.length - 1) {
                    rowCounts.compute(rowStr, (k, v) -> v == null ? 1 : v + 1);
                }
            }
        }
        return rowCounts.keySet().stream().map(str -> rowCounts.getOrDefault(str, 0) * colCounts.getOrDefault(str, 0)).reduce(0, Integer::sum);
    }

    public int equalPairs2(int[][] grid) {
        List<String> rows = Arrays.stream(grid).map(r -> Arrays.stream(r).mapToObj(Integer::toString).collect(Collectors.joining(":"))).toList();
        Map<String, Integer> cols = new HashMap<>();
        for (int c = 0; c < grid.length; c++) {
            StringBuilder s = new StringBuilder();
            for (int r = 0; r < grid.length; r++) {
                s.append(grid[r][c]);
                if (r < grid.length - 1) {
                    s.append(':');
                }
            }
            cols.compute(s.toString(), (k, v) -> v == null ? 1 : v + 1);
        }
        int result = 0;
        for (String r: rows) {
            result += cols.getOrDefault(r, 0);
        }
        return result;
    }

    @Test
    void check1() {
        int[][] grid = new int[][] {{3,2,1}, {1,7,6}, {2,7,7}};
        Assertions.assertEquals(1, equalPairs(grid));
    }

    @Test
    void check2() {
        int[][] grid = new int[][] {{3,1,2,2},{1,4,4,5},{2,4,2,2},{2,4,2,2}};
        Assertions.assertEquals(3, equalPairs(grid));
    }

    @Test
    void check3() {
        int[][] grid = new int[][] {
                {3,1,2,2},
                {1,4,4,4},
                {2,4,2,2},
                {2,5,2,2}
        };
        Assertions.assertEquals(3, equalPairs(grid));
    }
}

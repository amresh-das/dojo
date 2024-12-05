package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @see "https://leetcode.com/problems/maximum-non-negative-product-in-a-matrix/"
 */
public class MaximumNonNegativeProductInMatrix {

    public int maxProductPath(int[][] grid) {
        final Map<String, Long> memo = new HashMap<>();
        long max = maxProduct(grid, 0, 0, grid.length, grid[0].length, 1, memo);
        return max < 0 ? -1 : (int) (max % (Math.pow(10, 9) + 7));
    }

    private long maxProduct(final int[][] grid, final int i, final int j, final int m, final int n, long product, final Map<String, Long> memo) {
        String key = i+":"+j+":"+product;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        if (i == m - 1 && j == n - 1) {
            memo.put(key, product * grid[i][j]);
            return product * grid[i][j];
        }

        Long right = null, down = null;
        if (j < n - 1) {
            right = maxProduct(grid, i, j + 1, m, n, product * grid[i][j], memo);
        }
        if (i < m - 1) {
            down = maxProduct(grid, i + 1, j, m, n, product * grid[i][j], memo);
        }
        if (down != null && right != null) {
            long max = Math.max(down, right);
            memo.put(key, max);
            return max;
        } else if (down == null) {
            memo.put(key, right);
            return right;
        } else {
            memo.put(key, down);
            return down;
        }
    }

    @Test
    public void check1() {
        Assertions.assertEquals(-1, maxProductPath(Utils.to2dIntMatrix("[[-1,-2,-3],[-2,-3,-3],[-3,-3,-2]]")));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(8, maxProductPath(Utils.to2dIntMatrix("[[1,-2,1],[1,-2,1],[3,-4,1]]")));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(0, maxProductPath(Utils.to2dIntMatrix("[[1,3],[0,-4]]")));
    }

    @Test
    public void check4() {
        Assertions.assertEquals(768, maxProductPath(Utils.to2dIntMatrix("[[-1,-4,2],[4,3,-1],[2,-4,4],[1,-1,-4]]")));
    }

    @Test
    public void check5() {
        Assertions.assertEquals(354091247, maxProductPath(Utils.to2dIntMatrix("[[-1,-4,-1,0,2,0,-1,-2,1,-1,-1,-3,1,-1,-2],[1,-1,-1,1,0,0,-1,4,2,3,0,-4,1,0,-2],[2,3,4,-2,2,-2,-4,-3,-4,-2,-4,-1,0,-4,2],[2,1,1,-2,1,-2,4,0,2,0,-2,-3,-4,4,-2],[0,1,-1,-3,1,1,-2,-4,-3,1,2,2,-3,0,0],[-3,3,0,3,-3,2,3,3,-2,-2,2,-3,-3,3,0],[-1,3,-2,0,1,-1,4,3,2,3,2,1,-4,-1,0],[0,-2,-2,-1,1,-4,-1,4,-2,4,0,-4,-1,3,0],[-3,-3,-3,2,3,-1,1,0,2,-4,4,0,-2,0,2],[1,4,3,3,0,-1,-1,-4,2,-4,1,3,-1,-3,3],[-1,2,-3,-2,-2,-4,-4,1,-3,0,-4,-1,2,0,-3],[-1,0,-1,3,2,3,0,1,-4,-2,2,-3,3,-1,0],[0,1,0,3,-1,-4,3,1,0,1,0,-1,-4,-1,-4],[1,1,2,-2,3,0,0,-4,-1,4,-2,2,0,2,-1],[0,1,-2,4,1,2,3,3,1,3,1,4,0,4,-4]]")));
    }
}

package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @see "https://leetcode.com/problems/out-of-boundary-paths/"
 */
public class OutOfBoundaryPaths {
    private static final int MODULO = 1000000007;

    private int pathCounts(int m, int n, int maxMove, int startRow, int startColumn, final Map<String, Integer> memo) {
        if (startRow < 0 || startColumn < 0 || startRow == m || startColumn == n) return 1;
        if (maxMove == 0) return 0;
        String key = startRow + "." + startColumn + "." + maxMove;
        if (memo.containsKey(key)) return memo.get(key);

        int count =
                ( (pathCounts(m, n, maxMove - 1, startRow, startColumn + 1, memo) + pathCounts(m, n, maxMove - 1, startRow + 1, startColumn, memo)) % MODULO
                + (pathCounts(m, n, maxMove - 1, startRow, startColumn - 1, memo) + pathCounts(m, n, maxMove - 1, startRow - 1, startColumn, memo)) % MODULO) % MODULO;
        memo.put(key, count);
        return count;
    }

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        return pathCounts(m, n, maxMove, startRow, startColumn, new HashMap<>());
    }

    @Test
    public void check1() {
        Assertions.assertEquals(6, findPaths(2,2,2,0,0));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(12, findPaths(1,3,3,0,1));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(102984580, findPaths(8,7,16,1,5));
    }

    @Test
    public void check4() {
        Assertions.assertEquals(914783380, findPaths(8,50,23,5,26));
    }

    @Test
    public void check5() {
        Assertions.assertEquals(390153306, findPaths(36,5,50,15,3));
    }

}

package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @see "https://leetcode.com/problems/nearest-exit-from-entrance-in-maze/?envType=study-plan-v2&envId=leetcode-75"
 */
public class NearestExitFromEntranceInMazeTest {

    public int nearestExit(char[][] maze, int[] entrance) {
        int m = maze.length;
        int n = maze[0].length;
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        queue.offer(new int[] {0, entrance[0], entrance[1]});
        while (!queue.isEmpty()) {
            var curr = queue.poll();
            var steps = curr[0]; int i = curr[1]; int j = curr[2];
            if ((i == 0 || i == m - 1 || j == 0 || j == n - 1) && !(i == entrance[0] && j == entrance[1])) return steps;
            var next = steps + 1;
            move(i, j + 1, m, n, next, maze, queue);
            move(i, j - 1, m, n, next, maze, queue);
            move(i + 1, j, m, n, next, maze, queue);
            move(i - 1, j, m, n, next, maze, queue);
        }
        return -1;
    }

    private void move(final int i, final int j, int m, int n, int steps, char[][] maze, final PriorityQueue<int[]> queue) {
        if (i >= 0 && i < m && j >= 0 && j < n && maze[i][j] != '+') {
            queue.offer(new int[] {steps, i, j});
            maze[i][j] = '+';
        }
    }

    @Test
    void t1() {
        var maze = new char[][] {
                "++.+".toCharArray(),
                "...+".toCharArray(),
                "+++.".toCharArray()
        };
        Assertions.assertEquals(1, nearestExit(maze, new int[] {1, 2}));
    }

    @Test
    void t2() {
        var maze = new char[][] {
                "+++".toCharArray(),
                "...".toCharArray(),
                "+++".toCharArray()
        };
        Assertions.assertEquals(2, nearestExit(maze, new int[] {1, 0}));
    }
}

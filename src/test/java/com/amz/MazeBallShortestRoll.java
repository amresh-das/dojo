package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * @see "https://leetcode.com/problems/the-maze/"
 */
public class MazeBallShortestRoll {

    @Test
    void t1() {
        final String maze =
                "0 0 1 0 0\n" +
                "0 0 0 0 0\n" +
                "0 0 0 1 0\n" +
                "1 1 0 1 1\n" +
                "0 0 0 0 0";
        int[] start = {0, 4};
        int[] destination = {4, 4};
        int expected = 12;
        verify(maze, start, destination, expected);
    }

    @Test
    void t2() {
        final String maze =
                "0 0 1 0 0\n" +
                "0 0 0 0 0\n" +
                "0 0 0 1 0\n" +
                "1 1 0 1 1\n" +
                "0 0 0 0 0";
        int[] start = {0, 4};
        int[] destination = {3, 2};
        int expected = -1;
        verify(maze, start, destination, expected);
    }

    private void verify(final String maze, final int[] start, final int[] destination, final int expected) {
        final List<String[]> rows = new ArrayList<>();
        for (String s: maze.split("\n")) {
            rows.add(s.split(" "));
        }
        int[][] input = new int[rows.size()][rows.get(0).length];
        for (int i = 0; i < rows.size(); i++) {
            for (int j = 0; j < rows.get(i).length; j++) {
                input[i][j] = Integer.parseInt(rows.get(i)[j]);
            }
        }
        Assertions.assertEquals(expected, shortestDistance(input, start, destination));
    }

    private static enum Dir {
        U(-1, 0),
        D(1, 0),
        L(0, -1),
        R(0, 1);

        public final int x;
        public final int y;

        Dir(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Coordinate apply(Coordinate start) {
            return new Coordinate(start.x + this.x, start.y + this.y);
        }
    }

    private static class Coordinate {
        int x;
        int y;

        public Coordinate(final int... c) {
            this.x = c[0];
            this.y = c[1];
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Coordinate that = (Coordinate) o;
            return x == that.x &&
                    y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return String.format("{%d,%d}", x, y);
        }

        public boolean isValid(int[][] maze) {
            return x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0;
        }

    }

    private int shortestDistance1(final int[][] maze, final int[] start, final int[] destination) {
        int[][] distances = new int[maze.length][maze[0].length];
        for (int[] rows : distances) {
            Arrays.fill(rows, Integer.MAX_VALUE);
        }
        distances[start[0]][start[1]] = 0;
        dfs(maze, new Coordinate(start), distances);
        return distances[destination[0]][destination[1]] == Integer.MAX_VALUE ? - 1 : distances[destination[0]][destination[1]];
    }

    private void dfs(final int[][] maze, final Coordinate start, final int[][] distances) {
        for (Dir d : Dir.values()) {
            Coordinate next = d.apply(start);
            int count = 0;
            while (next.isValid(maze)) {
                count++;
                next = d.apply(next);
            }
            if (distances[start.x][start.y] + count < distances[next.x - d.x][next.y - d.y]) {
                distances[next.x - d.x][next.y - d.y] = distances[start.x][start.y] + count;
                dfs(maze, new Coordinate(next.x - d.x, next.y - d.y), distances);
            }
        }
    }

    private int shortestDistance(final int[][] maze, final int[] start, final int[] destination) {
        int[][] distances = new int[maze.length][maze[0].length];
        for (int[] rows : distances) {
            Arrays.fill(rows, Integer.MAX_VALUE);
        }
        Coordinate origin = new Coordinate(start);
        distances[origin.x][origin.y] = 0;
        dijkstra(maze, origin, distances);
        return distances[destination[0]][destination[1]] == Integer.MAX_VALUE ? - 1 : distances[destination[0]][destination[1]];
    }

    private void dijkstra(final int[][] maze, final Coordinate start, final int[][] distances) {
        final PriorityQueue<Pair<Coordinate, Integer>> queue = new PriorityQueue <> ((a, b) -> a.y - b.y);
        queue.offer(Pair.of(start, 0));
        while (!queue.isEmpty()) {
            Pair<Coordinate, Integer> origin = queue.poll();
            if (distances[origin.x.x][origin.x.y] < origin.y) continue;
            for (Dir d : Dir.values()) {
                Coordinate next = d.apply(origin.x);
                int count = 0;
                while (next.isValid(maze)) {
                    count++;
                    next = d.apply(next);
                }
                if (distances[origin.x.x][origin.x.y] + count < distances[next.x - d.x][next.y - d.y]) {
                    distances[next.x - d.x][next.y - d.y] = distances[origin.x.x][origin.x.y] + count;
                    queue.offer(Pair.of(new Coordinate(next.x - d.x, next.y - d.y), distances[next.x - d.x][next.y - d.y]));
                }
            }
        }
    }

}

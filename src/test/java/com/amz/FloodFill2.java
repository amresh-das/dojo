package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class FloodFill2 {

    @Test
    void t1() {
        int[][] image = new int[][] {
            new int[] {1,1,1},
            new int[] {1,1,0},
            new int[] {1,0,1}
        };
        int[][] expected = new int[][] {
                new int[] {2,2,2},
                new int[] {2,2,0},
                new int[] {2,0,1}
        };
        Assertions.assertEquals(toList(expected), toList(floodFill(image, 1, 1, 2)));
    }

    @Test
    void t2() {
        int[][] image = new int[][] {
            new int[] {0,0,0},
            new int[] {0,1,1}
        };
        int[][] expected = new int[][] {
                new int[] {0,0,0},
                new int[] {0,1,1}
        };
        Assertions.assertEquals(toList(expected), toList(floodFill(image, 1, 1, 1)));
    }

    private List<List<Integer>> toList(final int[][] actual) {
        List<List<Integer>> result = new ArrayList<>();
        for (int[] row : actual) {
            result.add(Arrays.stream(row).boxed().collect(Collectors.toList()));
        }
        return result;
    }

    enum Direction {
        U(-1, 0),
        D(1, 0),
        L(0, -1),
        R(0, 1);

        private final int x;
        private final int y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Coordinate apply(Coordinate coordinate) {
            return new Coordinate(coordinate.x + this.x, coordinate.y + this.y);
        }
    }

    private static class Coordinate {
        int x;
        int y;

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean isValid(int[][] image) {
            return x >= 0 && x < image.length && y >= 0 && y < image[0].length;
        }

        @Override
        public String toString() {
            return String.format("{%d,%d}", x, y);
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
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int oldColor = image[sr][sc];
        final Set<Coordinate> visited = new HashSet<>();
        replaceColor(image, new Coordinate(sr, sc), oldColor, newColor, visited);
        return image;
    }

    private void replaceColor(final int[][] image, final Coordinate c, final int oldColor, final int newColor, final Set<Coordinate> visited) {
        if (image[c.x][c.y] == oldColor) {
            visited.add(c);
            image[c.x][c.y] = newColor;
            for (Direction d : Direction.values()) {
                Coordinate newCoordinate = d.apply(c);
                if (newCoordinate.isValid(image) && !visited.contains(newCoordinate)) {
                    replaceColor(image, newCoordinate, oldColor, newColor, visited);
                }
            }
        }
    }
}

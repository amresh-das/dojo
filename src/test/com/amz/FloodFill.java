package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class FloodFill {

    @Test
    void t1() {
        final int[][] input = new int[][] {
            new int[] {1,1,1},
            new int[] {1,1,0},
            new int[] {1,0,1}
        };
        final int[][] expected = new int[][] {
                new int[] {2,2,2},
                new int[] {2,2,0},
                new int[] {2,0,1}
        };
        int[][] actual = floodFill(input, 1, 1, 2);
        Assertions.assertEquals(
            Arrays.stream(expected).map(a -> Arrays.stream(a).mapToObj(n -> n + "").collect(Collectors.joining(","))).collect(Collectors.joining("\n")),
            Arrays.stream(actual).map(a -> Arrays.stream(a).mapToObj(n -> n + "").collect(Collectors.joining(","))).collect(Collectors.joining("\n"))
        );
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        Set<Integer> visited = new HashSet<>();
        fill(image, sr, sc, image[sr][sc], newColor, visited);
        return image;
    }

    private void fill(final int[][] image, final int sr, final int sc, final int oldColor, final int newColor, final Set<Integer> visited) {
        if (sr >= 0 && sr < image.length && sc >= 0 && sc < image[0].length && image[sr][sc] == oldColor && !visited.contains(sr * 100 + sc)) {
            image[sr][sc] = newColor;
            visited.add(sr * 100 + sc);
            fill(image, sr - 1, sc, oldColor, newColor, visited);
            fill(image, sr + 1, sc, oldColor, newColor, visited);
            fill(image, sr, sc - 1, oldColor, newColor, visited);
            fill(image, sr, sc + 1, oldColor, newColor, visited);
        }
    }

}

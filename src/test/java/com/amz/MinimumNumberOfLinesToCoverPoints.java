package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class MinimumNumberOfLinesToCoverPoints {
    class Point {
        final int x;
        final int y;

        Point(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object other) {
            return other instanceof Point && ((Point) other).x == this.x && ((Point) other).y == this.y;
        }

        @Override
        public int hashCode() {
            return x * y;
        }

        @Override
        public String toString() {
            return "[" + x + "," + y + "]";
        }
    }

    public int minimumLines(int[][] points) {
        Map<String, Set<Point>> originPoints = new HashMap<>();

        final List<Point> pointList = Arrays.stream(points).map(r -> new Point(r[0], r[1])).collect(Collectors.toList());

        for (int p1 = 0; p1 < points.length; p1++) {
            for (int p2 = 0; p2 < points.length; p2++) {
                if (p1 != p2) {
                    Point point1 = pointList.get(p1);
                    Point point2 = pointList.get(p2);

                    double slope = ((double) point2.y - point1.y) / ((double) point2.x - point1.x);
                    String key1 = point1 + "->" + slope;
                    String key2 = point2 + "->" + slope;
                    originPoints.computeIfPresent(key1, (k, v) -> {
                        v.add(point2);
                        return v;
                    });
                    originPoints.computeIfPresent(key2, (k, v) -> {
                        v.add(point1);
                        return v;
                    });
                    originPoints.computeIfAbsent(key1, (k) -> {
                        Set<Point> set = new HashSet<>();
                        set.add(point1);
                        set.add(point2);
                        return set;
                    });
                }
            }
        }
        return 0;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(2, minimumLines(Utils.to2dIntMatrix("[[0,1],[2,3],[4,5],[4,3]]")));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(1, minimumLines(Utils.to2dIntMatrix("[[0,2],[-2,-2],[1,4]]")));
    }
}

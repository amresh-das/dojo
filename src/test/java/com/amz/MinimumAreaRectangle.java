package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @see "https://leetcode.com/problems/minimum-area-rectangle/"
 */
public class MinimumAreaRectangle {

    class Point {
        final int x;
        final int y;

        Point(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return x * 40000 + y;
        }

        @Override
        public boolean equals(final Object obj) {
            return obj instanceof Point && ((Point) obj).x == this.x && ((Point) obj).y == this.y;
        }

        @Override
        public String toString() {
            return "[" + x + "," + y + "]";
        }
    }

    public int minAreaRect(int[][] points) {
        final List<Point> pointList = Arrays.stream(points).map(p -> new Point(p[0], p[1])).collect(Collectors.toList());
        final Set<Point> used = new HashSet<>();
        final Point[] shape = new Point[4];
        int area = minAreaRect(pointList, 0, shape, used);
        return area == Integer.MAX_VALUE ? 0 : area;
    }

    private int minAreaRect(final List<Point> pointList, final int count, final Point[] shape, final Set<Point> used) {
        if (count == 4) {
            return getRectangleAreaParallelToXAndYAxis(shape);
        }
        int minArea = Integer.MAX_VALUE;
        for (Point p : pointList) {
            if (!used.contains(p)) {
                used.add(p);
                shape[count] = p;
                int area = minAreaRect(pointList, count + 1, shape, used);
                minArea = Math.min(minArea, area);
                used.remove(p);
            }
        }
        return minArea;
    }

    private int getRectangleAreaParallelToXAndYAxis(final Point[] shape) {
        Map<Integer, Integer> xCoordinateCount = new HashMap<>();
        Map<Integer, Integer> yCoordinateCount = new HashMap<>();
        for (Point p : shape) {
            xCoordinateCount.compute(p.x, (k, v) -> v == null ? 1 : v + 1);
            yCoordinateCount.compute(p.y, (k, v) -> v == null ? 1 : v + 1);
        }
        return xCoordinateCount.values().stream().allMatch(count -> count % 2 == 0) && yCoordinateCount.values().stream().allMatch(count -> count % 2 == 0) ? getArea(shape) : Integer.MAX_VALUE;
    }

    private int getArea(final Point[] shape) {
        double l = Math.sqrt((shape[1].x - shape[0].x) * (shape[1].x - shape[0].x) + (shape[1].y - shape[0].y) * (shape[1].y - shape[0].y));
        double b = Math.sqrt((shape[1].x - shape[2].x) * (shape[1].x - shape[2].x) + (shape[1].y - shape[2].y) * (shape[1].y - shape[2].y));
        int area = (int) (l * b);
        return area == 0 ? Integer.MAX_VALUE : area;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(4, minAreaRect(Utils.to2dIntMatrix("[[1,1],[1,3],[3,1],[3,3],[2,2]]")));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(2, minAreaRect(Utils.to2dIntMatrix("[[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]")));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(0, minAreaRect(Utils.to2dIntMatrix("[[3,2],[3,1],[4,4],[1,1],[4,3],[0,3],[0,2],[4,0]]")));
    }

    @Test
    public void check4() {
        Assertions.assertEquals(0, minAreaRect(Utils.to2dIntMatrix("[[1,2],[1,3],[3,3],[4,4],[2,1],[1,4],[2,2],[1,0],[0,2]]")));
    }

    @Test
    public void check5() {
        Assertions.assertEquals(14016300, minAreaRect(Utils.to2dIntMatrix("[[39973,30270],[13301,37647],[13854,37647],[13301,1045],[13854,1045],[11401,37647],[13854,10339],[11401,1045],[11401,10339],[13301,30270],[39973,37647],[11401,30270],[39973,1045],[39973,10339]]")));
    }

    @Test
    public void check6() {
        Assertions.assertEquals(1145144, minAreaRect(Utils.to2dIntMatrix("[[15820,1698],[9679,1698],[35142,6689],[35142,37888],[28856,1698],[15820,37387],[22478,11266],[30715,39747],[22478,37387],[31331,11266],[28856,23432],[35142,23432],[9679,11266],[15820,37888],[9679,6689],[35142,1698],[28856,37888],[22478,23432],[15820,39747],[30715,37888],[30715,6689],[31331,39747],[31331,23432],[28856,39747],[35142,11266],[30715,23432],[30715,11266],[15820,11266],[15820,6689],[31331,37888],[31331,6689],[31331,37387],[9679,37387],[22478,39747],[28856,37387],[22478,1698]]")));
    }

}

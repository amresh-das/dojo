package com.amz;

import com.amz.util.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Xi @ Google
 */
public class DetectSquare {

    class SquareDetector {
        private final Set<Point> points = new HashSet<>();
        private final Map<Integer, Set<Point>> xPoints = new HashMap<>();

        public boolean add(final int x, final int y) {
            Point p = new Point(x, y);
            boolean hasSquare = points.size() > 3 && detectSquare(x, y);
            xPoints.compute(x, (k, v) -> {
                Set<Point> set = v == null ? new HashSet<>() : v;
                set.add(p);
                return set;
            });
            points.add(p);
            return hasSquare;
        }

        private boolean detectSquare(int x, int y) {
            for (Point p2 : xPoints.getOrDefault(x, new HashSet<>())) {
                int distance = Math.abs(x - p2.getX());
                if ((points.contains(new Point(x, y + distance)) && points.contains(new Point(p2.getX(), y + distance)))
                        || (points.contains(new Point(x, y - distance)) && points.contains(new Point(p2.getX(), y - distance)))) {
                    return true;
                }
            }
            return false;
        }
    }

    @Test
    public void check1() {
        SquareDetector detector = new SquareDetector();
        Assertions.assertFalse(detector.add(1,2));
        Assertions.assertFalse(detector.add(3,5));
        Assertions.assertFalse(detector.add(1,5));
        Assertions.assertFalse(detector.add(4,2));
        Assertions.assertTrue(detector.add(4,5));
    }
}

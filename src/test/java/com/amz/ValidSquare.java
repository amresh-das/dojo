package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @see "https://leetcode.com/problems/valid-square/"
 */
public class ValidSquare {

    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[][] points = {p1, p2, p3, p4};
        Arrays.sort(points, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        double s1 = distance(points[0], points[1]);
        double s2 = distance(points[1], points[3]);
        double s3 = distance(points[3], points[2]);
        double s4 = distance(points[2], points[0]);

        double d1 = distance(points[0], points[3]);
        double d2 = distance(points[1], points[2]);

        return s1 != 0 && d1 == d2 && s1 == s2 && s2 == s3 && s3 == s4;
    }

    private double distance(int[] a, int[] b) {
        return (b[0] - a[0]) * (b[0] - a[0]) + (b[1] - a[1]) * (b[1] - a[1]);
    }

    @Test
    public void check1() {
        int[] p1 = {0,0}, p2 = {1,1}, p3 = {1,0}, p4 = {0,1};
        Assertions.assertTrue(validSquare(p1, p2, p3, p4));
    }

    @Test
    public void check2() {
        int[] p1 = {0,0}, p2 = {1,1}, p3 = {1,0}, p4 = {0,12};
        Assertions.assertFalse(validSquare(p1, p2, p3, p4));
    }

    @Test
    public void check3() {
        int[] p1 = {1,0}, p2 = {-1,0}, p3 = {0,1}, p4 = {0,-1};
        Assertions.assertTrue(validSquare(p1, p2, p3, p4));
    }

}

package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/rectangle-area/"
 */
public class RectangleArea {

    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int area1 = area(ax1, ay1, ax2, ay2);
        int area2 = area(bx1, by1, bx2, by2);
        int cx1 = Math.max(ax1, bx1);
        int cx2 = Math.min(ax2, bx2);
        int cy1 = Math.max(ay1, by1);
        int cy2 = Math.min(ay2, by2);
        int intersection = 0;
        if (cx1 < cx2 && cy1 < cy2) {
            intersection = area(cx1, cy1, cx2, cy2);
        }
        return area1 + area2 - intersection;
    }

    private int area(int x1, int y1, int x2, int y2) {
        return Math.abs(x2 - x1) * Math.abs(y2 - y1);
    }

    @Test
    public void check1() {
        Assertions.assertEquals(45, computeArea(-3, 0, 3, 4, 0, -1, 9, 2));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(16, computeArea(-2, -2, 2, 2, -2, -2, 2, 2));
    }

}

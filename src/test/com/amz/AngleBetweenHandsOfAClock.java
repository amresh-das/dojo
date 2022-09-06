package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/angle-between-hands-of-a-clock/"
 */
public class AngleBetweenHandsOfAClock {

    public double angleClock(int hour, int minutes) {
        if (hour == 12) hour = 0;
        if (minutes == 60) {
            minutes = 0;
            hour++;
        }
        double minuteAngle = 6 * minutes;
        double hourAngle = 30 * (hour + minutes / 60.0);
        double angle = Math.abs(hourAngle - minuteAngle);
        return angle > 180.0 ? 360.0 - angle : angle;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(165.0, angleClock(12, 30));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(75.0, angleClock(3, 30));
    }

    @Test
    public void check5() {
        Assertions.assertEquals(7.5, angleClock(3, 15));
    }

    @Test
    public void check6() {
        Assertions.assertEquals(76.5, angleClock(1, 57));
    }

}

package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @see "https://leetcode.com/problems/my-calendar-ii/"
 */
class MyCalendarTwo {
    List<int[]> calendar;
    List<int[]> overlaps;

    MyCalendarTwo() {
        overlaps = new ArrayList();
        calendar = new ArrayList();
    }

    public boolean book(int start, int end) {
        for (int[] iv: overlaps) {
            if (iv[0] < end && start < iv[1]) return false;
        }
        for (int[] iv: calendar) {
            if (iv[0] < end && start < iv[1])
                overlaps.add(new int[]{Math.max(start, iv[0]), Math.min(end, iv[1])});
        }
        calendar.add(new int[]{start, end});
        return true;
    }

    @Test
    public void check1() {
        MyCalendarTwo cal = new MyCalendarTwo();
        String input = "[[10,20],[50,60],[10,40],[5,15],[5,10],[25,55]]";
        int[][] ip = Utils.to2dIntMatrix(input);
        final List<String> output = new ArrayList<>();
        for (int[] in : ip) {
            output.add("" + cal.book(in[0], in[1]));
        }
        Assertions.assertEquals("true,true,true,false,true,true", String.join(",", output));
    }
}
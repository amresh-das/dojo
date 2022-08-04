package com.amz;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/**
 * @see "https://leetcode.com/problems/my-calendar-i/"
 */
public class MyCalendarI {
    class MyCalendar {

        private TreeMap<Integer, Integer> bookings = new TreeMap<>();

        public boolean book(int start, int end) {
            if (!bookings.isEmpty()) {
                final List<Integer> starts = new ArrayList<>(bookings.keySet());
                int index;
                if (start < starts.get(0)) {
                    index = 0;
                } else if (start > starts.get(starts.size() - 1)) {
                    index = starts.size() - 1;
                } else {
                    index = binarySearch(starts, start, 0, starts.size() - 1);
                }
                if (isColliding(start, end, starts, index - 1)
                        || isColliding(start, end, starts, index)
                        || isColliding(start, end, starts, index + 1)) return false;
            }
            bookings.put(start, end - 1);
            return true;
        }

        private int binarySearch(List<Integer> list, int item, int lo, int hi) {
            if (lo > hi) return -1;
            int mid = (lo + hi) / 2;
            if (list.get(mid) == item) return mid;
            if (mid < list.size() - 2 && list.get(mid) < item && list.get(mid + 1) > item) return mid;
            int index;
            if (list.get(mid) < item) {
                index = binarySearch(list, item, mid + 1, hi);
            } else {
                index = binarySearch(list, item, lo, mid - 1);
            }
            if (index == -1) {
                return mid;
            }
            return index;
        }

        private boolean isColliding(int start, int end, List<Integer> starts, int checkIndex) {
            if (checkIndex == starts.size() || checkIndex < 0) return false;
            int prevStart = starts.get(checkIndex);
            int prevEnd = bookings.get(prevStart);
            return start <= prevEnd && prevStart <= end - 1;
        }
    }

    @Test
    public void check1() {
        MyCalendar cal = new MyCalendar();
        List<Boolean> output = new ArrayList<>();
        output.add(cal.book(10, 20));
        output.add(cal.book(15, 25));
        output.add(cal.book(20, 30));
        Assertions.assertEquals(Lists.newArrayList(true, false, true), output);
    }

    @Test
    public void check2() {
        MyCalendar cal = new MyCalendar();
        List<Boolean> output = new ArrayList<>();
        output.add(cal.book(47,50));//true
        output.add(cal.book(33,41));//true
        output.add(cal.book(39,45));//false
        output.add(cal.book(33,42));//false
        output.add(cal.book(25,32));//true
        output.add(cal.book(26,35));//false
        output.add(cal.book(19,25));//true
        output.add(cal.book(3,8));//true
        output.add(cal.book(8,13));//true
        output.add(cal.book(18,27));//false
        Assertions.assertEquals(Lists.newArrayList(true,true,false,false,true,false,true,true,true,false), output);
    }

    @Test
    public void check3() {
        MyCalendar cal = new MyCalendar();
        List<Boolean> output = new ArrayList<>();
        output.add(cal.book(20,29));//t
        output.add(cal.book(13,22));//f
        output.add(cal.book(44,50));//t
        output.add(cal.book(1,7));//t
        output.add(cal.book(2,10));//f
        output.add(cal.book(14,20));//t
        output.add(cal.book(19,25));//f
        output.add(cal.book(36,42));//t
        output.add(cal.book(45,50));//f
        output.add(cal.book(47,50));//f
        output.add(cal.book(39,45));
        output.add(cal.book(44,50));
        output.add(cal.book(16,25));
        output.add(cal.book(45,50));
        output.add(cal.book(45,50));
        output.add(cal.book(12,20));
        output.add(cal.book(21,29));
        output.add(cal.book(11,20));
        output.add(cal.book(12,17));
        output.add(cal.book(34,40));
        output.add(cal.book(10,18));
        output.add(cal.book(38,44));
        output.add(cal.book(23,32));
        output.add(cal.book(38,44));
        output.add(cal.book(15,20));
        output.add(cal.book(27,33));
        output.add(cal.book(34,42));
        output.add(cal.book(44,50));
        output.add(cal.book(35,40));
        output.add(cal.book(24,31));
        Assertions.assertEquals(Lists.newArrayList(true,false,true,true,false,true,false,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false), output);
    }

}

package com.amz;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @see "https://leetcode.com/problems/exam-room/"
 */
public class ExamRoomSeating {
    private ExamRoom examRoom;
    private List<Integer> output;

    class ExamRoom {
        final SortedSet<Integer> seats;
        final int maxSeat;
        private final int n;

        public ExamRoom(int n) {
            seats = new TreeSet<>();
            maxSeat = n - 1;
            this.n = n;
        }

        public int seat() {
            int seatNum;
            if (seats.isEmpty()) {
                seatNum = 0;
            } else if (seats.size() == 1) {
                int assigned = seats.first();
                if (assigned == 0) {
                    seatNum = n - 1;
                } else if (assigned == n - 1) {
                    seatNum = 0;
                } else {
                    if (assigned < n / 2) {
                        seatNum = n - 1;
                    } else {
                        seatNum = 0;
                    }
                }
            } else {
                int maxDiff = -1;
                seatNum = 0;
                int start = seats.contains(0) ? 0 : -1;
                for (int end : seats) {
                    int diff = (end - start) / 2;
                    if (diff > maxDiff) {
                        maxDiff = diff;
                        seatNum = start == -1 ? 0 : start + diff;
                    }
                    start = end;
                }
                if (seats.last() < n - 1) {
                    start = seats.last();
                    int diff = (n - start) / 2;
                    if (diff > maxDiff) {
                        seatNum = start + diff;
                    }
                }
            }
            seats.add(seatNum);
            return seatNum;
        }

        public void leave(Integer p) {
            seats.remove(p);
        }
    }

    @Test
    public void check1() {
        init(10);
        seat();
        seat();
        seat();
        seat();
        leave(4);
        seat();
        Assertions.assertEquals(Lists.newArrayList(null, 0, 9, 4, 2, null, 5), output);
    }

    @Test
    public void check2() {
        init(10);
        seat();
        seat();
        seat();
        leave(0);
        leave(4);
        seat();
        seat();
        seat();
        seat();
        seat();
        seat();
        seat();
        seat();
        seat();
        leave(0);
        Assertions.assertEquals(Lists.newArrayList(null, 0, 9, 4, null, null, 0, 4, 2, 6, 1, 3, 5, 7, 8, null), output);
    }

    @Test
    public void check3() {
        init(10);
        seat();
        seat();
        seat();
        leave(0);
        leave(4);
        seat();
        seat();
        seat();
        seat();
        seat();
        seat();
        seat();
        seat();
        seat();
        leave(0);
        leave(4);
        seat();
        seat();
        leave(7);
        seat();
        leave(3);
        seat();
        leave(3);
        seat();
        leave(9);
        seat();
        leave(0);
        leave(8);
        seat();
        seat();
        leave(0);
        leave(8);
        seat();
        seat();
        leave(2);

        Assertions.assertEquals(Lists.newArrayList(null,0,9,4,null,null,0,4,2,6,1,3,5,7,8,null,null,0,4,null,7,null,3,null,3,null,9,null,null,0,8,null,null,0,8,null), output);
    }

    @Test
    public void check4() {
        init(4);
        seat();
        seat();
        seat();
        seat();
        leave(1);
        leave(3);
        seat();
        Assertions.assertEquals(Lists.newArrayList(null,0,3,1,2,null,null,1), output);
    }

    @Test
    public void check5() {
        init(8);
        seat();
        seat();
        seat();
        leave(0);
        leave(7);
        seat();
        seat();
        seat();
        seat();
        seat();
        seat();
        seat();
        Assertions.assertEquals(Lists.newArrayList(null,0,7,3,null,null,7,0,5,1,2,4,6), output);
    }

    private void init(int capacity) {
        examRoom = new ExamRoom(capacity);
        output = new ArrayList<>();
        output.add(null);
        System.out.printf("%-20s %s\t%s%n", "Started", examRoom.seats, output);
    }

    private void seat() {
        output.add(examRoom.seat());
        System.out.printf("%-20s %s\t%s%n", "Seat", examRoom.seats, output);
    }

    private void leave(int seatNum) {
        examRoom.leave(seatNum);
        output.add(null);
        System.out.printf("%-10s-%-10d %s\t%s%n", "Leave", seatNum, examRoom.seats, output);
    }
}

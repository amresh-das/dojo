package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @see "https://leetcode.com/problems/number-of-recent-calls/?envType=study-plan-v2&envId=leetcode-75"
 */
public class NumberOfRecentCallsTest {

    class RecentCounter {
        private Queue<Integer> msgs;

        public RecentCounter() {
            msgs = new ArrayDeque<>();
        }

        public int ping(int t) {
            msgs.offer(t);
            while (!msgs.isEmpty() && msgs.peek() < t - 3000) msgs.remove();
            return msgs.size();
        }
    }

    @Test
    void check1() {
        final RecentCounter c = new RecentCounter();
        Assertions.assertEquals(1, c.ping(1));
        Assertions.assertEquals(2, c.ping(100));
        Assertions.assertEquals(3, c.ping(3001));
        Assertions.assertEquals(3, c.ping(3002));
    }
}

package com.amz;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @see "https://leetcode.com/problems/design-hit-counter/"
 */
public class DesignHitCounter {

    class HitCounter {
        private final Deque<Integer> queue = new LinkedList<>();

        public void hit(int timestamp) {
            queue.offer(timestamp);
        }

        public int getHits(int timestamp) {
            cleanup(timestamp);
            return queue.size();
        }

        private void cleanup(int timestamp) {
            int initial = timestamp - 300;
            while (!queue.isEmpty() && queue.peekFirst() <= initial) {
                queue.removeFirst();
            }
        }
    }

    @Test
    public void check1() {
        HitCounter c = new HitCounter();
        List<Integer> output = new ArrayList<>();
        output.add(null);
	    output.add(null); c.hit(1);
	    output.add(null); c.hit(2);
	    output.add(null); c.hit(3);
	    output.add(c.getHits(4));
        output.add(null); c.hit(300);
	    output.add(c.getHits(300));
	    output.add(c.getHits(301));
        Assertions.assertEquals(Lists.newArrayList(null, null, null, null, 3, null, 4, 3), output);
    }

}

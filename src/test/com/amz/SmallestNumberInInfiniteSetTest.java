package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

/**
 * @see "https://leetcode.com/problems/smallest-number-in-infinite-set/?envType=study-plan-v2&envId=leetcode-75"
 */
public class SmallestNumberInInfiniteSetTest {

    @Test
    void t1() {
        SmallestInfiniteSet set = new SmallestInfiniteSet();
        set.addBack(2);
        Assertions.assertEquals(1, set.popSmallest());
        Assertions.assertEquals(2, set.popSmallest());
        Assertions.assertEquals(3, set.popSmallest());
        set.addBack(1);
        Assertions.assertEquals(1, set.popSmallest());
        Assertions.assertEquals(4, set.popSmallest());
        Assertions.assertEquals(5, set.popSmallest());
    }

}

class SmallestInfiniteSet {

    PriorityQueue<Integer> pq;
    int removedUntil;

    public SmallestInfiniteSet() {
        pq = new PriorityQueue<>();
        removedUntil = 0;
    }

    public int popSmallest() {
        if (pq.isEmpty()) {
            return ++removedUntil;
        } else {
            return pq.poll();
        }
    }

    public void addBack(int num) {
        if (num <= removedUntil && !pq.contains(num)) {
            pq.offer(num);
        }
    }
}
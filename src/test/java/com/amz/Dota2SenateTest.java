package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @see "https://leetcode.com/problems/dota2-senate/description/?envType=study-plan-v2&envId=leetcode-75"
 */
public class Dota2SenateTest {

    public String predictPartyVictory(String senate) {
        Queue<Integer> rQ = new ArrayDeque<>();
        Queue<Integer> dQ = new ArrayDeque<>();
        int n = 0;
        for (char c : senate.toCharArray()) {
            (c == 'R' ? rQ : dQ).offer(n++);
        }
        while (true) {
            if (rQ.isEmpty()) return "Dire";
            if (dQ.isEmpty()) return "Radiant";
            (rQ.poll() < dQ.poll() ? rQ : dQ).offer(n++);
        }
    }

    @Test
    void check1() {
        Assertions.assertEquals("Radiant", predictPartyVictory("RD"));
    }

    @Test
    void check2() {
        Assertions.assertEquals("Dire", predictPartyVictory("RDD"));
    }
}

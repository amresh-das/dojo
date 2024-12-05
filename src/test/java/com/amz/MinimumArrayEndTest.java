package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @see "https://leetcode.com/problems/minimum-array-end/description/?envType=daily-question&envId=2024-11-09"
 */
public class MinimumArrayEndTest {

    public long minEnd(int n, int x) {
        StringBuilder bits = new StringBuilder(Integer.toBinaryString(x));
        List<Integer> bitPositions = new LinkedList<>();
        for (int i = 0; i < bits.length(); i++) {
            if (bits.charAt(i) == '0') {
                bitPositions.add(i);
            }
        }
        String nBits = Integer.toBinaryString(n - 1);
        final int extraBitsNeeded = nBits.length() - bitPositions.size();
        for (int i = 0; i < extraBitsNeeded; i++) {
            bitPositions.addFirst(-1);
        }
        if (extraBitsNeeded < 0) {
            nBits = "0".repeat(-extraBitsNeeded) + nBits;
        }
        for (int i = nBits.length() - 1; i >= 0; i--) {
            final int position = bitPositions.get(i);
            final char c = nBits.charAt(i);
            if (position == -1) {
                bits.insert(0, c);
            } else {
                bits.setCharAt(position, c);
            }
        }
        return Long.parseLong(bits.toString(), 2);
    }

    @Test
    void t1() {
        Assertions.assertEquals(6, minEnd(3, 4));
    }

    @Test
    void t2() {
        Assertions.assertEquals(15, minEnd(2, 7));
    }

    @Test
    void t3() {
        Assertions.assertEquals(5, minEnd(3, 1));
    }

    @Test
    void t4() {
        Assertions.assertEquals(6, minEnd(3, 2));
    }

    @Test
    void t5() {
        Assertions.assertEquals(5, minEnd(2, 4));
    }

    @Test
    void t6() {
        Assertions.assertEquals(55012476815L, minEnd(6715154, 7193485));
    }
}

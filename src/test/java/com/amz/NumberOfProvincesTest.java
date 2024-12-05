package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/number-of-provinces/description/?envType=study-plan-v2&envId=leetcode-75"
 */
public class NumberOfProvincesTest {

    public int findCircleNum(int[][] isConnected) {
        int[] owners = new int[isConnected.length];
        int[] counts = new int[isConnected.length];
        for (int i = 0; i < isConnected.length; i++) {
            owners[i] = i;
            counts[i] = 1;
        }
        for (int i = 0; i < isConnected.length; i++) {
            for (int j = 0; j < isConnected.length; j++) {
                if (i == j) continue;
                if (isConnected[i][j] == 1) {
                    int a = i, b = j;
                    while (owners[a] != a) {
                        a = owners[a];
                    }
                    while (owners[b] != b) {
                        b = owners[b];
                    }
                    if (a == b) continue;
                    int owner, other;
                    if (counts[a] >= counts[b]) {
                        owner = a; other = b;
                    } else {
                        owner = b; other = a;
                    }
                    owners[other] = owner;
                    counts[owner] += counts[other];
                    counts[other] = 0;
                }
            }
        }
        int count = 0;
        for (int i = 0; i < owners.length; i++) {
            if (owners[i] == i) count++;
        }
        return count;
    }

    @Test
    void t1() {
        Assertions.assertEquals(2, findCircleNum(new int[][] {
                new int[] {1,1,0},
                new int[] {1,1,0},
                new int[] {0,0,1}
        }));
    }

    @Test
    void t2() {
        Assertions.assertEquals(3, findCircleNum(new int[][] {
                new int[] {1,0,0},
                new int[] {0,1,0},
                new int[] {0,0,1}
        }));
    }
}

package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @see "https://leetcode.com/problems/the-number-of-weak-characters-in-the-game/"
 */
public class TheNumberOfWeakCharactersInTheGame {

    public int numberOfWeakCharacters(int[][] properties) {
        Arrays.sort(properties, (a, b) -> b[0] == a[0] ? b[1] - a[1] : a[0] - b[0]);
        int count = 0;
        int maxDefense = 0;
        for (int i = properties.length - 1; i >= 0; i--) {
            if (properties[i][1] < maxDefense) count++;
            maxDefense = Math.max(maxDefense, properties[i][1]);
        }
        return count;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(0, numberOfWeakCharacters(Utils.to2dIntMatrix("[[5,5],[6,3],[3,6]]")));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(1, numberOfWeakCharacters(Utils.to2dIntMatrix("[[2,2],[3,3]]")));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(1, numberOfWeakCharacters(Utils.to2dIntMatrix("[[1,5],[10,4],[4,3]]")));
    }

    @Test
    public void check4() {
        Assertions.assertEquals(1, numberOfWeakCharacters(Utils.to2dIntMatrix("[[1,1],[2,1],[2,2],[1,2]]")));
    }
}

package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @see "https://leetcode.com/problems/matchsticks-to-square/"
 */
public class MatchsticksToSquare {

    public boolean makesquare(int[] matchsticks) {
        int sum = Arrays.stream(matchsticks).sum();
        if (sum % 4 != 0) return false;
        int side = sum / 4;
        int matchedCount = 0;
        final Set<Integer> used =new HashSet<>();
        for (int i = 0; i < 4; i++) {
            matchedCount += allocateMatches(side, 0, matchsticks, used);
        }
        return matchedCount == 4 && used.size() == matchsticks.length;
    }

    private int allocateMatches(final int side, final int sum, final int[] matches, Set<Integer> used) {
        if (sum == side) {
            return 1;
        }
        if (sum > side) {
            return 0;
        }
        for (int i = 0; i < matches.length; i++) {
            if (!used.contains(i)) {
                used.add(i);
                if (allocateMatches(side, sum + matches[i], matches, used) == 0) {
                    used.remove(i);
                } else {
                    return 1;
                }
            }
        }
        return 0;
    }

    private boolean isSquare2(final int[] matchsticks, final int side, final int index, final int[] sideTotals, final Map<String, Boolean> memo) {
        String key = index + "." + Arrays.stream(sideTotals).mapToObj(Integer::toString).collect(Collectors.joining("."));
        if (memo.containsKey(key)) return memo.get(key);
        if (index == matchsticks.length) {
            boolean isSquare = Arrays.stream(sideTotals).allMatch(t -> t == side);
            memo.put(key, isSquare);
            return isSquare;
        }
        for (int i = 0; i < 4; i++) {
            sideTotals[i] += matchsticks[index];
            if (isSquare2(matchsticks, side, index + 1, sideTotals, memo)) {
                memo.put(key, true);
                return true;
            }
            sideTotals[i] -= matchsticks[index];
        }
        memo.put(key, false);
        return false;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(true, makesquare(Utils.stringToIntArray("[1,1,2,2,2]")));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(false, makesquare(Utils.stringToIntArray("[3,3,3,3,4]")));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(false, makesquare(Utils.stringToIntArray("[1,2,3,4,5,6,7,8,9,10,5,4,3,2,1]")));
    }

    @Test
    public void check4() {
        Assertions.assertEquals(true, makesquare(Utils.stringToIntArray("[5,5,5,5,4,4,4,4,3,3,3,3]")));
    }

    @Test
    public void check5() {
        Assertions.assertEquals(true, makesquare(Utils.stringToIntArray("[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15]")));
    }

}

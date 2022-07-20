package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @see "https://leetcode.com/problems/campus-bikes-ii/"
 */
public class CampusBikesII {

    public int assignBikes(int[][] workers, int[][] bikes) {
        int[][] manhattan = new int[workers.length][bikes.length];
        for (int i = 0; i < workers.length; i++) {
            int[] worker = workers[i];
            for (int j = 0; j < bikes.length; j++) {
                int[] bike = bikes[j];
                manhattan[i][j] = manhattanDist(worker, bike);
            }
        }
        return minManhattan(manhattan, new HashSet<>(), 0, new HashMap<>());
    }

    private int minManhattan(final int[][] manhattan, final Set<Integer> bikesUsed, final int workerIndex, final Map<String, Integer> memo) {
        if (workerIndex == manhattan.length) return 0;
        final String key = workerIndex + "" + bikesUsed.toString();
        if (memo.containsKey(key)) return memo.get(key);
        int min = Integer.MAX_VALUE;
        for (int bikeId = 0; bikeId < manhattan[0].length; bikeId++) {
            if (!bikesUsed.contains(bikeId)) {
                bikesUsed.add(bikeId);
                min = Math.min(min, manhattan[workerIndex][bikeId] + minManhattan(manhattan, bikesUsed, workerIndex + 1, memo));
                bikesUsed.remove(bikeId);
            }
        }
        memo.put(key, min);
        return min;
    }

    private int manhattanDist(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }

    @Test
    public void check1() {
        String workers = "[[0,0],[2,1]]";
        String bikes = "[[1,2],[3,3]]";
        int expected = 6;
        verify(workers, bikes, expected);
    }

    @Test
    public void check2() {
        String workers = "[[0,0],[1,1],[2,0]]";
        String bikes = "[[1,0],[2,2],[2,1]]";
        int expected = 4;
        verify(workers, bikes, expected);
    }

    @Test
    public void check3() {
        String workers = "[[0,0],[1,0],[2,0],[3,0],[4,0]]";
        String bikes = "[[0,999],[1,999],[2,999],[3,999],[4,999]]";
        int expected = 4995;
        verify(workers, bikes, expected);
    }

    @Test
    public void check4() {
        String workers = "[[0,0],[1,0],[2,0],[3,0],[4,0],[5,0],[6,0],[7,0],[8,0],[9,0]]";
        String bikes = "[[0,999],[1,999],[2,999],[3,999],[4,999],[5,999],[6,999],[7,999],[8,999],[9,999]]";
        int expected = 9990;
        verify(workers, bikes, expected);
    }

    @Test
    public void check5() {
        String workers = "[[778,180],[117,786],[991,906],[287,895],[987,658],[607,288],[218,692],[430,358],[567,42]]";
        String bikes = "[[918,524],[375,331],[992,612],[876,889],[607,730],[571,603],[867,591],[638,668],[168,87]]";
        int expected = 3322;
        verify(workers, bikes, expected);
    }

    private void verify(final String workers, final String bikes, final int expected) {
        Assertions.assertEquals(expected, assignBikes(Utils.to2dIntMatrix(workers), Utils.to2dIntMatrix(bikes)));
    }

}

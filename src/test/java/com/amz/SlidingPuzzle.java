package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @see "https://leetcode.com/problems/sliding-puzzle/"
 */
public class SlidingPuzzle {
    private final int[][] target = new int[][] {new int[] {1,2,3}, new int[] {4,5,0}};

    public int slidingPuzzle(int[][] board) {
        final String srcStr = toString(board);
        final String tgtStr = toString(target);
        final Set<String> visited = new HashSet<>();
        final PriorityQueue<Object[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> (int) a[0]));
        pq.offer(new Object[] {0, srcStr});

        while (!pq.isEmpty()) {
            Object[] next = pq.poll();
            String src = (String) next[1];
            if (visited.contains(src)) continue;
            visited.add(src);
            int level = (int) next[0];
            if (src == null) continue;
            if (src.equals(tgtStr)) return level;

            pq.offer(new Object[] {level + 1, up(src)});
            pq.offer(new Object[] {level + 1, down(src)});
            pq.offer(new Object[] {level + 1, left(src)});
            pq.offer(new Object[] {level + 1, right(src)});
        }
        return -1;
    }

    private String up(String src) {
        int index = src.indexOf('0');
        if (index > 2) {
            char[] t = src.toCharArray();
            t[3 + index % 3] = t[index % 3];
            t[index % 3] = '0';
            return new String(t);
        }
        return null;
    }

    private String down(String src) {
        int index = src.indexOf('0');
        if (index < 3) {
            char[] t = src.toCharArray();
            t[index % 3] = t[3 + index % 3];
            t[3 + index % 3] = '0';
            return new String(t);
        }
        return null;
    }

    private String left(String src) {
        int index = src.indexOf('0');
        if (index % 3 > 0) {
            char[] t = src.toCharArray();
            t[index] = t[index - 1];
            t[index - 1] = '0';
            return new String(t);
        }
        return null;
    }

    private String right(String src) {
        int index = src.indexOf('0');
        if (index % 3 < 2) {
            char[] t = src.toCharArray();
            t[index] = t[index + 1];
            t[index + 1] = '0';
            return new String(t);
        }
        return null;
    }

    private String toString(int[][] board) {
        return Arrays.stream(board).map(r -> Arrays.stream(r).mapToObj(Integer::toString).collect(Collectors.joining())).collect(Collectors.joining());
    }

    @Test
    public void check1() {
        Assertions.assertEquals(1, slidingPuzzle(Utils.to2dIntMatrix("[[1,2,3],[4,0,5]]")));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(-1, slidingPuzzle(Utils.to2dIntMatrix("[[1,2,3],[5,4,0]]")));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(5, slidingPuzzle(Utils.to2dIntMatrix("[[4,1,2],[5,0,3]]")));
    }
}

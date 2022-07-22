package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @see "https://leetcode.com/problems/alphabet-board-path/"
 */
public class AlphabetBoardPath {
    private final String[] board = new String[]{"abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"};

    public String alphabetBoardPath(String target) {
        final PriorityQueue<Object[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> (int) a[3]));
        int matchedIndex = 0;
        pq.offer(new Object[]{0, 0, "", 0, ""});
        Set<Character> visited = new HashSet<>();
        while (!pq.isEmpty()) {
            Object[] pos = pq.poll();
            int x = (int) pos[0];
            int y = (int) pos[1];
            String path = (String) pos[2];
            int length = (int) pos[3];
            String str = (String) pos[4];
            if (x < 0 || y < 0 || x >= board.length || y >= board[x].length()) continue;
            if (visited.contains(board[x].charAt(y))) continue;
            visited.add(board[x].charAt(y));
            if (board[x].charAt(y) == target.charAt(matchedIndex)) {
                matchedIndex++;
                path = path + "!";
                if (matchedIndex == target.length()) return path;
                pq.clear();
                visited.clear();
                pq.offer(new Object[] {x, y, path, length, str});
            } else {
                pq.offer(new Object[]{x - 1, y, path + "U", length + 1, str + board[x].charAt(y)});
                pq.offer(new Object[]{x + 1, y, path + "D", length + 1, str + board[x].charAt(y)});
                pq.offer(new Object[]{x, y - 1, path + "L", length + 1, str + board[x].charAt(y)});
                pq.offer(new Object[]{x, y + 1, path + "R", length + 1, str + board[x].charAt(y)});
            }
        }
        return null;
    }

    @Test
    public void check1() {
        Assertions.assertEquals("DDR!UURRR!!DDD!", alphabetBoardPath("leet"));
    }
}

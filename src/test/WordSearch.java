import com.amz.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @see "https://leetcode.com/problems/word-search/"
 */
public class WordSearch {

    public boolean exist(final char[][] board, final String word) {
        print(board);
        char startingChar = word.charAt(0);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == startingChar && visit(board, i, j, 0, word, new HashSet<>())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean visit(final char[][] board, final int i, final int j, final int wordPos, final String word, final HashSet<Index> visited) {
        if (wordPos == word.length()) return true;
        Index key = new Index(i, j);
        if (visited.contains(key)) return false;
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) return false;
        if (board[i][j] == word.charAt(wordPos)) {
            visited.add(key);
            if (
                    visit(board, i + 1, j, wordPos + 1, word, visited) ||
                    visit(board, i, j + 1, wordPos + 1, word, visited) ||
                    visit(board, i - 1, j, wordPos + 1, word, visited) ||
                    visit(board, i, j - 1, wordPos + 1, word, visited)
            ) {
                return true;
            } else {
                visited.remove(key);
            }
        }
        return false;
    }

    private class Index {
        final int i;
        final int j;
        Index(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object other) {
            return other instanceof Index && (((Index) other).i == this.i) && (((Index) other).j == this.j);
        }

        @Override
        public int hashCode() {
            return i * 10000 + j;
        }

        public String toString() {
            return i + ":" + j;
        }
    }

    public boolean exist1(final char[][] board, final String word) {
        print(board);
        Map<Character, List<Index>> indices = new HashMap<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                final Index index = new Index(i, j);
                indices.compute(board[i][j], (k, v) -> {
                   final List<Index> list = v == null ? new ArrayList<>() : v;
                   list.add(index);
                   return list;
                });
            }
        }
        Map<Character, Integer> required = new HashMap<>();
        for (char c : word.toCharArray()) {
            required.compute(c, (k, v) -> v == null ? 1 : v + 1);
        }
        for (Map.Entry<Character, Integer> entry : required.entrySet()) {
            if (indices.getOrDefault(entry.getKey(), new ArrayList<>()).size() < entry.getValue()) return false;
        }
        return indices.getOrDefault(word.charAt(0), new ArrayList<>()).stream().anyMatch(origin -> explore(word, indices, null, 0, new HashSet<>()));
    }

    private boolean explore(final String word, final Map<Character, List<Index>> indices, final Index prev, final int position, final Set<Index> visited) {
        if (position == word.length()) return true;
        char c = word.charAt(position);
        if (indices.containsKey(c)) {
            for (Index pos : indices.get(c)) {
                if (!visited.contains(pos) && distance(prev, pos) == 1) {
                    visited.add(pos);
                    if (explore(word, indices, pos, position + 1, visited)) {
                        return true;
                    } else {
                        visited.remove(pos);
                    }
                }
            }
        }
        return false;
    }

    private int distance(final Index prev, final Index pos) {
        return prev == null ? 1 : Math.abs(prev.i - pos.i) + Math.abs(prev.j - pos.j);
    }

    private void print(final char[][] board) {
        System.out.println("Board:");
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    @Test
    public void check1() {
        String grid = "[[\"A\",\"B\",\"C\",\"E\"],[\"S\",\"F\",\"C\",\"S\"],[\"A\",\"D\",\"E\",\"E\"]]";
        Assertions.assertEquals(true, exist(Utils.to2dCharMatrix(grid), "ABCCED"));
    }

    @Test
    public void check2() {
        String grid = "[[\"A\",\"B\",\"C\",\"E\"],[\"S\",\"F\",\"C\",\"S\"],[\"A\",\"D\",\"E\",\"E\"]]";
        Assertions.assertEquals(true, exist(Utils.to2dCharMatrix(grid), "SEE"));
    }

    @Test
    public void check3() {
        String grid = "[[\"A\",\"B\",\"C\",\"E\"],[\"S\",\"F\",\"C\",\"S\"],[\"A\",\"D\",\"E\",\"E\"]]";
        Assertions.assertEquals(false, exist(Utils.to2dCharMatrix(grid), "ABCB"));
    }

    @Test
    public void check4() {
        String grid = "[[\"a\"]]";
        Assertions.assertEquals(false, exist(Utils.to2dCharMatrix(grid), "ab"));
    }

    @Test
    public void check5() {
        String grid = "[[\"A\",\"A\",\"A\",\"A\",\"A\",\"A\"],[\"A\",\"A\",\"A\",\"A\",\"A\",\"A\"],[\"A\",\"A\",\"A\",\"A\",\"A\",\"A\"],[\"A\",\"A\",\"A\",\"A\",\"A\",\"A\"],[\"A\",\"A\",\"A\",\"A\",\"A\",\"A\"],[\"A\",\"A\",\"A\",\"A\",\"A\",\"A\"]]";
        Assertions.assertEquals(false, exist(Utils.to2dCharMatrix(grid), "AAAAAAAAAAAAAAB"));
    }

    @Test
    public void check6() {
        String grid = "[[\"A\",\"A\",\"A\",\"A\",\"A\",\"A\"],[\"A\",\"A\",\"A\",\"A\",\"A\",\"A\"],[\"A\",\"A\",\"A\",\"A\",\"A\",\"A\"],[\"A\",\"A\",\"A\",\"A\",\"A\",\"A\"],[\"A\",\"A\",\"A\",\"A\",\"A\",\"B\"],[\"A\",\"A\",\"A\",\"A\",\"B\",\"A\"]]";
        Assertions.assertEquals(false, exist(Utils.to2dCharMatrix(grid), "AAAAAAAAAAAAABB"));
    }

    @Test
    public void check7() {
        String grid = "[[\"A\",\"B\",\"C\",\"E\"],[\"S\",\"F\",\"E\",\"S\"],[\"A\",\"D\",\"E\",\"E\"]]";
        Assertions.assertEquals(true, exist(Utils.to2dCharMatrix(grid), "ABCESEEEFS"));
    }

}

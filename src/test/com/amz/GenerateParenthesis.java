package com.amz;

import com.google.common.collect.Sets;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @see "https://leetcode.com/problems/generate-parentheses/"
 */
public class GenerateParenthesis {

    @Test
    void t1() {
        Set<String> result = Sets.newHashSet("()");
        org.assertj.core.api.Assertions.assertThat(generateParenthesis(1)).containsAll(result);
    }

    @Test
    void t2() {
        Set<String> result = Sets.newHashSet(
            "(())",
            "()()"
        );
        org.assertj.core.api.Assertions.assertThat(generateParenthesis(2)).containsAll(result);

    }

    @Test
    void t3() {
        Set<String> result = Sets.newHashSet(
            "((()))",
            "(()())",
            "(())()",
            "()(())",
            "()()()"
        );
        org.assertj.core.api.Assertions.assertThat(generateParenthesis(3)).containsAll(result);

    }

    @Test
    void t4() {
        Set<String> result = Sets.newHashSet(
            "(((())))",
            "((()()))",
            "((())())",
            "((()))()",
            "(()(()))",
            "(()()())",
            "(()())()",
            "(())(())",
            "(())()()",
            "()((()))",
            "()(()())",
            "()(())()",
            "()()(())",
            "()()()()"
        );
        // "(())(())"
        Assertions.assertThat(generateParenthesis(4)).containsAll(result);
    }

    public List<String> generateParenthesis(int n) {
        List<String> permutations = new ArrayList<>();
        char[] base = new char[n * 2];
        fill(base, 0, n, n, permutations);
        return permutations;
    }

    private void fill(final char[] base, final int current, final int remainingOpen, final int remainingClosed, final List<String> permutations) {
        if (remainingOpen == 0 && remainingClosed == 0) {
            permutations.add(new String(base));
        } else {
            // Open
            if (remainingOpen > 0) {
                base[current] = '(';
                fill(base, current + 1, remainingOpen - 1, remainingClosed, permutations);
            }
            // Close
            if (remainingClosed > 0 && remainingClosed > remainingOpen ) {
                base[current] = ')';
                fill(base, current + 1, remainingOpen, remainingClosed - 1, permutations);
            }
        }
    }

}

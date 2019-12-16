package com.amz;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @see "https://leetcode.com/problems/generate-parentheses/"
 */
public class GenerateParenthesis {

    @Test
    void t1() {
        Set<String> result = Sets.newHashSet(
            "((()))",
            "(()())",
            "(())()",
            "()(())",
            "()()()"
        );
        Assertions.assertEquals(result, new HashSet<>(generateParenthesis(3)));
    }

    @Test
    void t2() {
        Set<String> result = Sets.newHashSet(
            "(())",
            "()()"
        );
        Assertions.assertEquals(result, new HashSet<>(generateParenthesis(2)));
    }

    @Test
    void t3() {
        Set<String> result = Sets.newHashSet("()");
        Assertions.assertEquals(result, new HashSet<>(generateParenthesis(1)));
    }

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generate(new StringBuilder(), n, result);
        return result;
    }

    private void generate(StringBuilder s, int remaining, List<String> result) {
        if (remaining == 0) {
            result.add(s.toString());
        }

    }

}

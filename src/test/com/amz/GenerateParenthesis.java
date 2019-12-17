package com.amz;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @see "https://leetcode.com/problems/generate-parentheses/"
 */
public class GenerateParenthesis {

    @Test
    void t1() {
        Set<String> result = Sets.newHashSet("()");
        Assertions.assertEquals(result, new HashSet<>(generateParenthesis(1)));
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
        Assertions.assertEquals(result, new HashSet<>(generateParenthesis(4)));
    }

    public List<String> generateParenthesis(int n) {
        Set<String> result = new HashSet<>();
        if (n <= 0) return new ArrayList<>();
        result.add(new String("()"));
        for (int i = 2; i <= n; i++) {
            inject(i ,"()", result);
        }
        return new ArrayList<>(result);
    }

    private void inject(int i, String s, Set<String> result) {
        Set<String> temp = new HashSet<>();
        temp.addAll(before(result, s));
        temp.addAll(after(result, s));
        temp.addAll(between(result, s));
        result.addAll(temp);
        result.removeIf(t -> t.length() != i * 2);
    }

    private List<String> between(Set<String> result, String s) {
        List<String> temp = new ArrayList<>();
        for (String t: result) {
            temp.add(s.substring(0, 1) + t + s.substring(1));
        }
        return temp;
    }

    private List<String> after(Set<String> result, String s) {
        List<String> temp = new ArrayList<>();
        for (String t: result) {
            temp.add(t + s);
        }
        return temp;
    }

    private List<String> before(Set<String> result, String s) {
        List<String> temp = new ArrayList<>();
        for (String t: result) {
            temp.add(s + t);
        }
        return temp;
    }

}

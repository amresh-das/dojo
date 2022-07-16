package com.amz;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * @see "https://leetcode.com/problems/brace-expansion/"
 */
public class BraceExpansion {

    public String[] expand(String s) {
        final List<List<Character>> options = getOptions(s);
        final List<String> output = new ArrayList<>();
        permute(options, output, 0, options.size(), new StringBuilder(options.size()));
        return output.stream().sorted().toArray(String[]::new);
    }

    private void permute(final List<List<Character>> options, final List<String> output, final int position, final int max, final StringBuilder str) {
        if (position == max) {
            output.add(str.toString());
        } else {
            for (Character option : options.get(position)) {
                str.append(option);
                permute(options, output, position + 1, max, str);
                str.deleteCharAt(str.length() - 1);
            }
        }
    }

    private List<List<Character>> getOptions(final String s) {
        final List<List<Character>> options = new ArrayList<>();
        final Stack<Character> stack = new Stack<>();
        boolean isConstant = true;
        for (char c : s.toCharArray()) {
            if (c == '{') {
                isConstant = false;
            } else if (c == '}') {
                if (!isConstant) {
                    options.add(new ArrayList<>(stack));
                    stack.clear();
                }
                isConstant = true;
            } else if (c == ',') {
            } else {
                if (isConstant) {
                    List<Character> option = new ArrayList<>();
                    option.add(c);
                    options.add(option);
                } else {
                    stack.push(c);
                }
            }
        }
        return options;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(Lists.newArrayList("acdf","acef","bcdf","bcef"), Arrays.stream(expand("{a,b}c{d,e}f")).collect(Collectors.toList()));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(Lists.newArrayList("abcd"), Arrays.stream(expand("abcd")).collect(Collectors.toList()));
    }
}

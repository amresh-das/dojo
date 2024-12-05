package com.amz;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class FreqStackTest {

    FreqStack stack;;

    @Test
    void t1() {
        stack = new FreqStack();
        stack.push(5);
        stack.push(7);
        stack.push(5);
        stack.push(7);
        stack.push(4);
        stack.push(5);

        List<Integer> expected = Lists.newArrayList(5,7,5,4);
        List<Integer> actual = new ArrayList<>();
        expected.forEach(k -> actual.add(stack.pop()));

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void t2() {
        stack = new FreqStack();
        List<Integer> expected = Arrays.asList(4, 6, 1, 1, 4, 2, 3, 9, 0, 4);
        List<Integer> actual = new ArrayList<>();

        stack.push(4);
        stack.push(0);
        stack.push(9);
        stack.push(3);
        stack.push(4);
        stack.push(2);
        actual.add(stack.pop());
        stack.push(6);
        actual.add(stack.pop());
        stack.push(1);
        actual.add(stack.pop());
        stack.push(1);
        actual.add(stack.pop());
        stack.push(4);
        actual.add(stack.pop());

        int remaining = expected.size() - actual.size();
        for (int i = 0; i < remaining; i++) {
            actual.add(stack.pop());
        }

        Assertions.assertEquals(expected, actual);
    }
}
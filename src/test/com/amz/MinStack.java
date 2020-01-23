package com.amz;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @see "https://leetcode.com/problems/min-stack/"
 */
class MinStack {
    private Deque<Integer> store;
    private int min;

    public MinStack() {
        store = new LinkedList<>();
        min = Integer.MAX_VALUE;
    }

    public void push(int x) {
        store.push(x);
        min = Math.min(min, x);
    }

    public void pop() {
        Integer popped = store.pop();
        if (popped == min) {
            min = Integer.MAX_VALUE;
            for (int n : store) {
                min = Math.min(n, min);
            }
        }
    }

    public int top() {
        return store.peek();
    }

    public int getMin() {
        return min;
    }
}
package com.amz;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

class FreqStack {
    private Map<Integer, Integer> itemFrequency;
    private TreeMap<Integer, Stack<Integer>> frequencyStack;

    public FreqStack() {
        frequencyStack = new TreeMap<>(Comparator.reverseOrder());
        itemFrequency = new HashMap<>();
    }

    public void push(int x) {
        Integer frequency = itemFrequency.compute(x, (k, v) -> v == null ? 1 : v + 1);
        frequencyStack.compute(frequency, (k, v) -> {
            Stack<Integer> stack = v == null ? new Stack<>() : v;
            stack.push(x);
            return stack;
        });
    }


    public int pop() {
        int frequency = frequencyStack.firstKey();
        Integer item = frequencyStack.get(frequency).pop();
        if (frequencyStack.get(frequency).isEmpty()) {
            frequencyStack.remove(frequency);
        }
        if (frequency == 0) {
            itemFrequency.remove(item);
        } else {
            itemFrequency.put(item, frequency - 1);
        }
        return item;
    }

}

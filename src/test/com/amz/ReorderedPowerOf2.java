package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ReorderedPowerOf2 {

    public boolean reorderedPowerOf2(int n) {
        String input = sort(Integer.toString(n));
        int powOf2 = 1;
        Set<String> set = new HashSet<>();
        while (true) {
            String s = Integer.toString(powOf2);
            if (s.length() > input.length()) break;
            if (s.length() == input.length()) {
                set.add(sort(s));
            }
            powOf2 *= 2;
        }
        return set.contains(input);
    }

    public boolean reorderedPowerOf2A(int n) {
        return permuteAndCheck(Integer.toString(n), 0, 0, new HashSet<>());
    }

    private boolean permuteAndCheck(String num, int position, int current, Set<Integer> usedIndices) {
        if (position == num.length()) return Integer.toString(current).length() == position && (current & (current - 1)) == 0;
        for (int i = 0; i < num.length(); i++) {
            if (usedIndices.contains(i)) continue;
            usedIndices.add(i);
            char c = num.charAt(i);
            if (permuteAndCheck(num, position + 1, current * 10 + (c - '0'), usedIndices)) {
                return true;
            }
            usedIndices.remove(i);
        }
        return false;
    }

    private String sort(String n) {
        char[] arr = n.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }

    @Test
    public void check1() {
        Assertions.assertTrue(reorderedPowerOf2(1));
        Assertions.assertTrue(reorderedPowerOf2(2));
        Assertions.assertFalse(reorderedPowerOf2(10));
    }

    @Test
    public void check2() {
        Assertions.assertFalse(reorderedPowerOf2(268341));
    }

    @Test
    public void check3() {
        Assertions.assertTrue(reorderedPowerOf2(4802));
    }
}

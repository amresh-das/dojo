package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @see "https://leetcode.com/problems/group-shifted-strings/"
 */
public class GroupShiftedStrings {

    public List<List<String>> groupStrings(String[] strings) {
        final List<List<String>> output = new ArrayList<>();
        Map<Integer, List<String>> byLength = new HashMap<>();
        for (String s: strings) {
            byLength.putIfAbsent(s.length(), new ArrayList<>());
            byLength.get(s.length()).add(s);
        }
        for (int len: byLength.keySet()) {
            if (len == 1) {
                output.add(byLength.get(len));
            }
            else {
                Map<String, List<String>> byDiff = new HashMap<>();
                byLength.get(len).forEach(s -> {
                    StringBuilder str = new StringBuilder();
                    for (int i = 1; i < s.length(); i++) {
                        int diff = s.charAt(i) - s.charAt(i - 1);
                        if (diff < 0) {
                            diff = 26 + diff;
                        }
                        str.append(diff);
                        str.append('.');
                    }
                    String key = str.toString();
                    System.out.println(s +"->" + key);
                    byDiff.putIfAbsent(key, new ArrayList<>());
                    byDiff.get(key).add(s);
                });
                output.addAll(byDiff.values());
            }
        }
        return output;
    }

    @Test
    public void check1() {
        String input = "[abc,bcd,acef,xyz,az,ba,a,z]";
        String expected = "[[acef],[a,z],[abc,bcd,xyz],[az,ba]]";
        Assertions.assertEquals(expected, Utils.from2dMatrix(groupStrings(Utils.toStringArray(input))));
    }

    @Test
    public void check2() {
        String input = "[a]";
        String expected = "[[a]]";
        Assertions.assertEquals(expected, Utils.from2dMatrix(groupStrings(Utils.toStringArray(input))));
    }

}

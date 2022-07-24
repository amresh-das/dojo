package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @see "https://leetcode.com/problems/android-unlock-patterns/"
 */
public class AndroidUnlockPatterns {
    final Set<Character> keys = IntStream.range(1, 10).mapToObj(i -> Character.forDigit(i, 10)).collect(Collectors.toSet());
    final Map<String, Set<Character>> passesThrough = new HashMap<>();
    {
        passesThrough.put("13", Stream.of('2').collect(Collectors.toSet()));
        passesThrough.put("31", Stream.of('2').collect(Collectors.toSet()));
        passesThrough.put("17", Stream.of('4').collect(Collectors.toSet()));
        passesThrough.put("71", Stream.of('4').collect(Collectors.toSet()));
        passesThrough.put("39", Stream.of('6').collect(Collectors.toSet()));
        passesThrough.put("93", Stream.of('6').collect(Collectors.toSet()));
        passesThrough.put("79", Stream.of('8').collect(Collectors.toSet()));
        passesThrough.put("97", Stream.of('8').collect(Collectors.toSet()));
        passesThrough.put("19", Stream.of('5').collect(Collectors.toSet()));
        passesThrough.put("91", Stream.of('5').collect(Collectors.toSet()));
        passesThrough.put("28", Stream.of('5').collect(Collectors.toSet()));
        passesThrough.put("82", Stream.of('5').collect(Collectors.toSet()));
        passesThrough.put("46", Stream.of('5').collect(Collectors.toSet()));
        passesThrough.put("64", Stream.of('5').collect(Collectors.toSet()));
        passesThrough.put("37", Stream.of('5').collect(Collectors.toSet()));
        passesThrough.put("73", Stream.of('5').collect(Collectors.toSet()));
    }

    public int numberOfPatterns(int min, int max) {
        Set<String> patterns = new HashSet<>();
        countPatterns("", 0, min, max, patterns);
        return patterns.size();
    }

    private void countPatterns(String pattern, int position, int min, int max, Set<String> patterns) {
        if (position >= min) {
            patterns.add(pattern);
        }
        if (position == max) return;
        for (Character key : keys) {
            if (isValid(pattern, key)) {
                countPatterns(pattern + key, position + 1, min, max, patterns);
            }
        }
    }

    private boolean isValid(String pattern, char next) {
        if (pattern.isEmpty()) return true;
        if (pattern.contains("" + next)) return false;
        char prev = pattern.charAt(pattern.length() - 1);
        Set<Character> keys = passesThrough.get("" + prev + next);
        if (keys == null) return true;
        for (char c : keys) {
            if (!pattern.contains("" + c)) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(9, numberOfPatterns(1, 1));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(65, numberOfPatterns(1, 2));
    }

}

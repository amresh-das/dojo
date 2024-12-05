package com.amz;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordSubsets {

    public List<String> wordSubsets(final String[] words1, final String[] words2) {
        final List<String> subsets = new ArrayList<>();
        final Map<Character, Integer> requiredCounts = new HashMap<>();
        for (final String word: words2) {
            final Map<Character, Integer> counts = countChars(null, word);
            counts.forEach((c, n) -> requiredCounts.compute(c, (k, v) -> v == null ? n : Math.max(v, n)));
        }
        for (final String word: words1) {
            final Map<Character, Integer> counts = countChars(requiredCounts, word);
            if (requiredCounts.entrySet().stream().allMatch(c -> counts.getOrDefault(c.getKey(), 0) >= c.getValue())) {
                subsets.add(word);
            }
        }
        return subsets;
    }

    private Map<Character, Integer> countChars(final Map<Character, Integer> requiredCharCounts, String word) {
        final Map<Character, Integer> counts = new HashMap<>();
        for (final char c : word.toCharArray()) {
            if (requiredCharCounts == null || requiredCharCounts.containsKey(c)) {
                counts.compute(c, (k, v) -> v == null ? 1 : v + 1);
            }
        }
        return counts;
    }

    @Test
    public void check1() {
        List<String> expected = Lists.newArrayList("facebook", "google", "leetcode");
        String words1 = "[\"amazon\",\"apple\",\"facebook\",\"google\",\"leetcode\"]";
        String words2 = "[\"e\",\"o\"]";
        Assertions.assertEquals(expected, wordSubsets(Utils.toStringArray(words1), Utils.toStringArray(words2)));
    }

    @Test
    public void check2() {
        List<String> expected = Lists.newArrayList("apple","google","leetcode");
        String words1 = "[\"amazon\",\"apple\",\"facebook\",\"google\",\"leetcode\"]";
        String words2 = "[\"l\",\"e\"]";
        Assertions.assertEquals(expected, wordSubsets(Utils.toStringArray(words1), Utils.toStringArray(words2)));
    }
}

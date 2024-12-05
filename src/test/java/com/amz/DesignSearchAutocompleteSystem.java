package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @see "https://leetcode.com/problems/design-search-autocomplete-system/"
 */
public class DesignSearchAutocompleteSystem {

    class AutocompleteSystem {
        private final Map<String, Set<String>> index = new HashMap<>();
        private Map<String, Integer> rank = new HashMap<>();
        private final StringBuilder input = new StringBuilder();
        private ArrayList<String> emptyAns = new ArrayList<>();

        public AutocompleteSystem(String[] sentences, int[] times) {
            for (int i = 0; i < sentences.length; i++) {
                String sentence = sentences[i];
                int count = times[i];
                rank.put(sentence, count);
                updateIndexes(sentence);
            }
        }

        public List<String> input(char c) {
            if (c == '#') {
                String sentence = input.toString();
                rank.compute(sentence, (k, v) -> v == null ? 1 : v + 1);
                updateIndexes(sentence);
                input.delete(0, input.length());
                return emptyAns;
            } else {
                input.append(c);
                Set<String> matches = index.getOrDefault(input.toString(), new HashSet<>());
                return matches.stream().sorted((a,b) -> {
                    int bRank = rank.getOrDefault(b, 0);
                    int aRank = rank.getOrDefault(a, 0);
                    return bRank == aRank ? a.compareTo(b) : bRank - aRank;
                }).limit(3).collect(Collectors.toList());
            }
        }

        private void updateIndexes(String sentence) {
            StringBuilder indexStr = new StringBuilder();
            for (char c : sentence.toCharArray()) {
                indexStr.append(c);
                index.compute(indexStr.toString(), (k, v) -> {
                    Set<String> set = v == null ? new HashSet<>() : v;
                    set.add(sentence);
                    return set;
                });
            }
        }
    }

    @Test
    public void check1() {
        final List<List<String>> output = new ArrayList<>();
        String[] sentences = Utils.toStringArray("[\"i love you\",\"island\",\"iroman\",\"i love leetcode\"]");
        int[] times = Utils.stringToIntArray("[5,3,2,2]");
        AutocompleteSystem s = new AutocompleteSystem(sentences, times);
        output.add(s.input('i'));
        output.add(s.input(' '));
        output.add(s.input('a'));
        output.add(s.input('#'));
        Assertions.assertEquals("[[\"i love you\",\"island\",\"i love leetcode\"],[\"i love you\",\"i love leetcode\"],[],[]]",
                "[" + output.stream().map(l -> "[" + l.stream().map(x -> "\"" + x + "\"").collect(Collectors.joining(",")) + "]").collect(Collectors.joining(",")) + "]");
    }
}

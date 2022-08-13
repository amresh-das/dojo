package com.amz;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @see "https://leetcode.com/problems/substring-with-concatenation-of-all-words/"
 */
public class SubstringWithConcatenationOfAllWords {

    public List<Integer> findSubstring(final String s, final String[] words) {
        final int wordCount = words.length;
        final int wordLength = words[0].length();
        if (wordLength == 0 || wordCount == 0) return new ArrayList<>();

        final Map<String, String> dictionary = new HashMap<>();

        final String requiredString = Arrays.stream(words).sorted().map(word -> {
            dictionary.putIfAbsent(word, Character.toString((char) (dictionary.size() + 65)));
            return dictionary.get(word);
        }).collect(Collectors.joining());

        final int sentenceLength = wordCount * wordLength;
        return IntStream.range(0, s.length() - sentenceLength + 1).filter(
                i -> {
                    final String sentence = s.substring(i, i + sentenceLength);

                    final String converted = IntStream.range(0, wordCount).mapToObj(j -> {
                        final String token = sentence.substring(j * wordLength, j * wordLength + wordLength);
                        return dictionary.getOrDefault(token, " ");
                    }).sorted().collect(Collectors.joining());

                    return converted.equals(requiredString);
                }
        ).boxed().collect(Collectors.toList());
    }

    public List<Integer> findSubstring2(String s, String[] words) {
        int wordLen = words[0].length();
        final List<Integer> ans = new ArrayList<>();

        final Map<String, Integer> wordCounts = getWordCounts(words);

        final Map<Integer, String> found = new HashMap<>();
        for (int offset = 0; offset < wordLen; offset++) {
            int foundIndex = -1;
            reset(wordCounts, found);
            for (int c = 0; c < (s.length() - offset) / wordLen; c++) {
                int index = c * wordLen + offset;
                if (foundIndex == -1) foundIndex = index;
                final String current = s.substring(index, index + wordLen);
                int count = wordCounts.getOrDefault(current, 0);
                if (count == 0) {
                    c -= found.size();
                    reset(wordCounts, found);
                    foundIndex = -1;
                    continue;
                }
                if (count == 1) {
                    wordCounts.remove(current);
                } else {
                    wordCounts.put(current, count - 1);
                }
                found.put(index, current);
                if (wordCounts.isEmpty()) {
                    ans.add(foundIndex);
                    foundIndex = -1;
                    c -= found.size() - 1;
                    reset(wordCounts, found);
                }
            }
        }
        return ans;
    }

    private Map<String, Integer> getWordCounts(String[] words) {
        final Map<String, Integer> wordCounts = new HashMap<>();
        for (String word : words) {
            wordCounts.compute(word, (k, v) -> v == null ? 1 : v + 1);
        }
        return wordCounts;
    }

    private void reset(Map<String, Integer> wordCounts, Map<Integer, String> found) {
        found.values().forEach(word -> wordCounts.compute(word, (k, v) -> v == null ? 1 : v + 1));
        found.clear();
    }

    @Test
    public void check1() {
        String input = "barfoothefoobarman";
        String[] words = Utils.toStringArray("[\"foo\",\"bar\"]");
        List<Integer> expected = Lists.newArrayList(0,9);
        Assertions.assertEquals(expected, findSubstring(input, words));
    }

    @Test
    public void check2() {
        String input = "wordgoodgoodgoodbestword";
        String[] words = Utils.toStringArray("[\"word\",\"good\",\"best\",\"word\"]");
        List<Integer> expected = Lists.newArrayList();
        Assertions.assertEquals(expected, findSubstring(input, words));
    }

    @Test
    public void check3() {
        String input = "barfoofoobarthefoobarman";
        String[] words = Utils.toStringArray("[\"bar\",\"foo\",\"the\"]");
        List<Integer> expected = Lists.newArrayList(6,9,12);
        Assertions.assertEquals(expected, findSubstring(input, words));
    }

    @Test
    public void check4() {
        String input = "lingmindraboofooowingdingbarrwingmonkeypoundcake";
        String[] words = Utils.toStringArray("[\"fooo\",\"barr\",\"wing\",\"ding\",\"wing\"]");
        List<Integer> expected = Lists.newArrayList(13);
        Assertions.assertEquals(expected, findSubstring(input, words));
    }

}

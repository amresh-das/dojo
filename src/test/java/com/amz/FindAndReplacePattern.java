package com.amz;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @see "https://leetcode.com/problems/find-and-replace-pattern/"
 */
public class FindAndReplacePattern {

    public List<String> findAndReplacePattern(String[] words, String pattern) {
        final List<String> matches = new ArrayList<>();
        final Set<Character> wordChars = new HashSet<>();
        final Map<Character, Character> mapping = new HashMap<>();
        for (String word: words) {
            boolean isMatchingSingleChar = true;
            if (pattern.length() != word.length()) continue;
            wordChars.clear();
            mapping.clear();
            for (int i = 0; i < pattern.length(); i++) {
                char patternChar = pattern.charAt(i);
                char wordChar = word.charAt(i);
                if (mapping.containsKey(patternChar)) {
                    if (wordChar != mapping.get(patternChar)) {
                        isMatchingSingleChar = false;
                        break;
                    }
                } else {
                    if (wordChars.contains(wordChar)) {
                        isMatchingSingleChar = false;
                        break;
                    }
                    mapping.put(patternChar, wordChar);
                }
                wordChars.add(wordChar);
            }
            if (isMatchingSingleChar && wordChars.size() < 26) matches.add(word);
        }
        return matches;
    }

    @Test
    public void check1() {
        String[] input = Utils.toStringArray("[\"abc\",\"deq\",\"mee\",\"aqq\",\"dkd\",\"ccc\"]");
        String pattern = "abb";
        List<String> expected = Lists.newArrayList("mee","aqq");
        Assertions.assertEquals(expected, findAndReplacePattern(input, pattern));
    }

    @Test
    public void check2() {
        String[] input = Utils.toStringArray("[\"a\",\"b\",\"c\"]");
        String pattern = "a";
        List<String> expected = Lists.newArrayList("a","b","c");
        Assertions.assertEquals(expected, findAndReplacePattern(input, pattern));
    }
}

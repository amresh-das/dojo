package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @see "https://leetcode.com/problems/number-of-matching-subsequences/"
 */
public class NumberOfMatchingSubsequences {

    public int numMatchingSubseq(String s, String[] words) {
        final Map<String, Integer> matchedUpTo = new HashMap<>();
        matchedUpTo.put(s, s.length());
        int matchCount = 0;
        for (String word: words) {
            if (word.length() > s.length()) continue;
            if (lastMatchedIndex(s, word, matchedUpTo) > -1) matchCount++;
        }
        return matchCount;
    }

    private int lastMatchedIndex(final String source, final String word, final Map<String, Integer> matchedUpTo) {
        if (matchedUpTo.containsKey(word)) return matchedUpTo.get(word);
        int wordLen = word.length();
        if (wordLen == 1) {
            return matchedUpTo.compute(word, (k, v) -> source.indexOf(word.charAt(0)));
        }
        int prevIndex = lastMatchedIndex(source, word.substring(0, wordLen - 1), matchedUpTo);
        if (prevIndex >= 0) {
            char ch = word.charAt(wordLen - 1);
            int index = source.indexOf(ch, prevIndex + 1);
            if (index != -1) {
                return matchedUpTo.compute(word, (k, v) -> index);
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    @Test
    public void check1() {
        Assertions.assertEquals(3, numMatchingSubseq("abcde", Utils.toStringArray("[\"a\",\"bb\",\"acd\",\"ace\"]")));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(2, numMatchingSubseq("dsahjpjauf", Utils.toStringArray("[\"ahjpjau\",\"ja\",\"ahbwzgqnuk\",\"tnmlanowax\"]")));
    }
}

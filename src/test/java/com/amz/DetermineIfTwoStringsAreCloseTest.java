package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @see "https://leetcode.com/problems/determine-if-two-strings-are-close/description/?envType=study-plan-v2&envId=leetcode-75"
 */
public class DetermineIfTwoStringsAreCloseTest {

    public boolean closeStrings(String word1, String word2) {
        final Map<Character, Integer> fp1 = fprint(word1);
        final Map<Character, Integer> fp2 = fprint(word2);
        return fp1.keySet().equals(fp2.keySet())
                && fp1.values().stream().sorted().toList().equals(fp2.values().stream().sorted().toList());
    }

    private Map<Character, Integer> fprint(final String word) {
        final Map<Character, Integer> freq = new HashMap<>();
        for (char c : word.toCharArray()) {
            freq.compute(c, (k, v) -> v == null ? 1 : v + 1);
        }
        return freq;
    }

    @Test
    void check1() {
        Assertions.assertTrue(closeStrings("abc", "bca"));
    }

    @Test
    void check2() {
        Assertions.assertFalse(closeStrings("a", "aa"));
    }

    @Test
    void check3() {
        Assertions.assertTrue(closeStrings("cabbba", "abbccc"));
    }
}

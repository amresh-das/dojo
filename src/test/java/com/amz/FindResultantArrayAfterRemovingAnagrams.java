package com.amz;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Stream;

/**
 * @see "https://leetcode.com/problems/find-resultant-array-after-removing-anagrams/"
 */
public class FindResultantArrayAfterRemovingAnagrams {

    public List<String> removeAnagrams(String[] words) {
        final List<String> result = new ArrayList<>();
        String prev = null;
        for (String word : words) {
            String sorted = sort(word);
            if (sorted.equals(prev)) continue;
            result.add(word);
            prev = sorted;
        }
        return result;
    }

    private String sort(String word) {
        char[] arr = word.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }

    @Test
    public void check1() {
        Assertions.assertEquals(Lists.newArrayList("abba","cd"), removeAnagrams(new String[] {"abba","baba","bbaa","cd","cd"}));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(Lists.newArrayList("a","b","c","d","e"), removeAnagrams(new String[] {"a","b","c","d","e"}));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(Lists.newArrayList("a","b","a"), removeAnagrams(new String[] {"a","b","a"}));
    }

}

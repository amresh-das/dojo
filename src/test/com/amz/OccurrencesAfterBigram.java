package com.amz;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @see "https://leetcode.com/problems/occurrences-after-bigram/"
 */
public class OccurrencesAfterBigram {

    public String[] findOcurrences(String text, String first, String second) {
        final List<String> answer = new ArrayList<>();
        final String[] words = text.split(" ");
        if (words.length > 2) {
            String s1 = words[0];
            String s2 = words[1];
            for (int i = 2; i < words.length; i++) {
                String word = words[i];
                if (s1.equals(first) && s2.equals(second)) answer.add(word);
                s1 = s2;
                s2 = word;
            }
        }
        return answer.toArray(new String[0]);
    }

    @Test
    public void check1() {
        String text = "alice is a good girl she is a good student";
        String first = "a";
        String second = "good";
        Assertions.assertEquals(Lists.newArrayList("girl", "student"), Arrays.asList(findOcurrences(text, first, second)));
    }

    @Test
    public void check2() {
        String text = "we will we will rock yo";
        String first = "we";
        String second = "will";
        Assertions.assertEquals(Lists.newArrayList("we", "rock"), Arrays.asList(findOcurrences(text, first, second)));
    }
}

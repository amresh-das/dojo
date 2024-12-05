package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @see "https://leetcode.com/problems/stream-of-characters/"
 */
public class StreamOfCharacters {

    private class StreamChecker {
        private final StringBuilder buffer;
        private final List<String> words;
        private int bufferSize;

        public StreamChecker(String[] words) {
            bufferSize = -1;
            for (String word : words) {
                bufferSize = Math.max(bufferSize, word.length());
            }
            this.buffer = new StringBuilder(bufferSize);
            this.words = Arrays.asList(words);
        }

        public boolean query(char letter) {
            if (buffer.length() == bufferSize) buffer.deleteCharAt(0);
            buffer.append(letter);
            String bufferStr = buffer.toString();
            return words.stream().anyMatch(bufferStr::endsWith);
        }
    }

    @Test
    public void check1() {
        final StreamChecker checker = new StreamChecker(new String[]{"cd", "f", "kl"});
        final char[] queries = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l'};
        final List<String> output = new ArrayList<>();
        output.add(null);
        for (char c : queries) {
            output.add(checker.query(c) + "");
        }
        Assertions.assertEquals("[null, false, false, false, true, false, true, false, false, false, false, false, true]", output.toString());
    }

    @Test
    public void check2() {
        final StreamChecker checker = new StreamChecker(new String[]{"abaa", "abaab", "aabbb", "bab", "ab"});
        final char[] queries = new char[]{'a', 'a', 'b', 'b', 'b', 'a', 'a', 'b', 'b', 'a', 'a', 'a', 'a', 'b', 'a', 'b', 'b', 'b', 'a', 'b', 'b', 'b', 'a', 'a', 'a', 'a', 'a', 'b', 'a', 'b', 'b', 'b', 'a', 'a', 'b', 'b', 'b', 'a', 'b', 'a'};
        final List<String> output = new ArrayList<>();
        output.add(null);
        String in = "";
        for (int i = 0; i < queries.length; i++) {
            char c = queries[i];
            in = in + c;
            output.add(checker.query(c) + "");
            System.out.println(in);
            System.out.println(output.toString().replaceAll(" ", ""));
        }
        Assertions.assertEquals("[null,false,false,true,false,true,false,false,true,false,false,false,false,false,true,false,true,false,false,false,true,false,false,false,false,false,false,false,true,false,true,false,false,false,false,true,false,true,false,true,false]", output.toString().replaceAll(" ", ""));
    }
}

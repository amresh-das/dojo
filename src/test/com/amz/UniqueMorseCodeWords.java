package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @see "https://leetcode.com/problems/unique-morse-code-words/"
 */
public class UniqueMorseCodeWords {
    private String[] MORSE_CODE = new String[] {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};

    public int uniqueMorseRepresentations(String... words) {
        final Set<String> transformations = new HashSet<>();
        final StringBuilder s = new StringBuilder();
        for (String word : words) {
            s.delete(0, s.length());
            for (char c : word.toCharArray()) {
                s.append(MORSE_CODE[c - 'a']);
            }
            transformations.add(s.toString());
        }
        return transformations.size();
    }

    @Test
    public void check1() {
        Assertions.assertEquals(2, uniqueMorseRepresentations("gin","zen","gig","msg"));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(1, uniqueMorseRepresentations("a"));
    }

}

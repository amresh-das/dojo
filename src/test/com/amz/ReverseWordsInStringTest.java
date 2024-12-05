package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/reverse-words-in-a-string/description/?envType=study-plan-v2&envId=leetcode-75"
 */
public class ReverseWordsInStringTest {

    @Test
    public void check1() {
        Assertions.assertEquals("blue is sky the", reverseWords("the sky is blue"));
    }

    @Test
    public void check2() {
        Assertions.assertEquals("example good a", reverseWords("a good   example"));
    }

    @Test
    public void check3() {
        Assertions.assertEquals("b a", reverseWords("a b"));
    }

    @Test
    public void check4() {
        Assertions.assertEquals("EPY2giL", reverseWords("EPY2giL"));
    }

    @Test
    public void check5() {
        Assertions.assertEquals("", reverseWords("   "));
    }

    @Test
    public void check6() {
        Assertions.assertEquals("world hello", reverseWords("  hello world  "));
    }

    public String reverseWords(String s) {
        final StringBuilder output = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            final char c = s.charAt(i);
            if (c != ' ') {
                int end = i + 1;
                while (i > 0 && s.charAt(i - 1) != ' ') i--;
                if (!output.isEmpty()) {
                    output.append(' ');
                }
                output.append(s, i, end);
            }
        }
        return output.toString();
    }

    public String reverseWords1(String s) {
        final StringBuilder output = new StringBuilder();
        final StringBuilder word = new StringBuilder();
        char prev = ' ';
        for (char c : (s + " ").toCharArray()) {
            if (c != ' ') word.append(c);
            if (c == ' ') {
                if (prev != ' ') {
                    output.insert(0, ' ');
                }
                if (!word.isEmpty()) {
                    output.insert(0, word);
                    word.setLength(0);
                }
            }
            prev = c;
        }
        return output.substring(0, output.length() - 1);
    }
}

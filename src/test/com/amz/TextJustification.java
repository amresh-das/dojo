package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @see "https://leetcode.com/problems/text-justification/"
 */
public class TextJustification {

    public List<String> fullJustify(final String[] words, final int maxWidth) {
        final List<String> result = new ArrayList<>();
        int rowLength = 0;
        final List<String> rowWords = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            final String word = words[i];
            if (rowLength + word.length() + rowWords.size() - 1 < maxWidth) {
                rowWords.add(word);
                rowLength += word.length();
            } else {
                result.add(justifyRow(rowWords, rowLength, maxWidth));
                rowWords.clear();
                rowLength = 0;
                i--;
            }
        }
        if (!rowWords.isEmpty()) {
            result.add(justifyLastRow(rowWords, maxWidth));
        }
        return result;
    }

    private String justifyRow(final List<String> rowWords, final int rowLength, final int maxWidth) {
        final int spacesNeeded = maxWidth - rowLength;
        final int spacePerWord = rowWords.size() > 1 ? spacesNeeded / (rowWords.size() - 1) : maxWidth - spacesNeeded;
        final String spacing = fillSpaces(spacePerWord);
        final StringBuilder rowText = new StringBuilder(maxWidth);
        int remainingSpaces = rowWords.size() > 1 ? spacesNeeded % (rowWords.size() - 1) : 0;
        for (int i = 0; i < rowWords.size(); i++) {
            final String word = rowWords.get(i);
            rowText.append(word);
            if (i < rowWords.size() - 1) {
                rowText.append(spacing);
            }
            if (remainingSpaces > 0) {
                rowText.append(" ");
                remainingSpaces--;
            }
        }
        if (rowText.length() < maxWidth) {
            rowText.append(fillSpaces(maxWidth - rowText.length()));
        }
        return rowText.toString();
    }

    private String justifyLastRow(final List<String> rowWords, final int maxWidth) {
        final String rowText = String.join(" ", rowWords);
        return rowText.length() < maxWidth ? rowText + fillSpaces(maxWidth - rowText.length()) : rowText;
    }

    private String fillSpaces(final int spacePerWord) {
        return new String(new char[spacePerWord]).replace("\0", " ");
    }

    @Test
    void t1() {
        verify("[\"This\", \"is\", \"an\", \"example\", \"of\", \"text\", \"justification.\"]", 16,
            "This    is    an",
                "example  of text",
                "justification.  "
        );
    }

    @Test
    void t2() {
        verify("[\"What\",\"must\",\"be\",\"acknowledgment\",\"shall\",\"be\"]", 16,
            "What   must   be",
                "acknowledgment  ",
                "shall be        "
        );
    }

    @Test
    void t3() {
        verify("[\"Science\",\"is\",\"what\",\"we\",\"understand\",\"well\",\"enough\",\"to\",\"explain\",\"to\",\"a\",\"computer.\",\"Art\",\"is\",\"everything\",\"else\",\"we\",\"do\"]", 20,
            "Science  is  what we",
                "understand      well",
                "enough to explain to",
                "a  computer.  Art is",
                "everything  else  we",
                "do                  "
        );
    }

    private void verify(final String input, final int maxWidth, final String... expected) {
        final String[] words = Utils.toStringArray(input.replaceAll(" ", ""));
        final List<String> actual = fullJustify(words, maxWidth);
        Assertions.assertEquals(Stream.of(expected).collect(Collectors.toList()), actual);
    }
}

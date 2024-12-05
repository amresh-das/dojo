package com.amz;

/**
 * @see "https://leetcode.com/problems/merge-strings-alternately/?envType=study-plan-v2&envId=leetcode-75"
 */
public class MergeStringsAlternatelyTest {

    public String mergeAlternately(String word1, String word2) {
        int n = Math.max(word1.length(), word2.length());
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i == word1.length()) {
                output.append(word2.substring(i));
                return output.toString();
            }
            if (i == word2.length()) {
                output.append(word1.substring(i));
                return output.toString();
            }
            output.append(word1.charAt(i));
            output.append(word2.charAt(i));
        }
        return output.toString();
    }

}

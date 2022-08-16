package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @see "https://leetcode.com/problems/first-unique-character-in-a-string/"
 */
public class FirstUniqueCharacterInAString {
    public int firstUniqChar(String s) {
        final Map<Character, Integer> indices = new HashMap<>();
        final Set<Character> uniques = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int prevIndex = indices.getOrDefault(c, -1);
            indices.put(c, i);
            if (prevIndex == -1) {
                uniques.add(c);
            } else {
                uniques.remove(c);
            }
        }
        return uniques.stream()
                .mapToInt(x -> indices.getOrDefault(x, -1))
                .min().orElse(-1);
    }

    @Test
    public void check1() {
        Assertions.assertEquals(0, firstUniqChar("leetcode"));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(2, firstUniqChar("loveleetcode"));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(-1, firstUniqChar("aabb"));
    }

}

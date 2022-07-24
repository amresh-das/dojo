package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @see "https://leetcode.com/problems/sentence-similarity-ii/"
 */
public class SentenceSimilarityII {

    public boolean areSentencesSimilarTwo(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        if (sentence1.length != sentence2.length) return false;
        final Map<String, Integer> ids = new HashMap<>();
        final DSU dsu = new DSU(2 * similarPairs.size());
        for (List<String> pair : similarPairs) {
            for (String p : pair) {
                if (!ids.containsKey(p)) {
                    ids.put(p, ids.size());
                }
            }
            dsu.union(ids.get(pair.get(0)), ids.get(pair.get(1)));
        }
        for (int i = 0; i < sentence1.length; i++) {
            String a = sentence1[i];
            String b = sentence2[i];
            if (a.equals(b)) continue;
            if (!ids.containsKey(a) || !ids.containsKey(b) || dsu.find(ids.get(a)) != dsu.find(ids.get(b))) return false;
        }
        return true;
    }

    class DSU {
        int[] parent;

        DSU(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int n) {
            while (parent[n] != n) {
                n = parent[n];
            }
            return n;
        }

        public void union(int x, int y) {
            parent[find(x)] = find(y);
        }
    }

    @Test
    public void check1() {
        String[] sentence1 = new String[]{"great", "acting", "skills"};
        String[] sentence2 = new String[]{"fine", "drama", "talent"};
        String[][] similar = Utils.to2dStringMatrix("[[\"great\",\"good\"],[\"fine\",\"good\"],[\"drama\",\"acting\"],[\"skills\",\"talent\"]]");
        Assertions.assertEquals(true, areSentencesSimilarTwo(sentence1, sentence2, Arrays.stream(similar).map(a -> Arrays.stream(a).collect(Collectors.toList())).collect(Collectors.toList())));
    }

    @Test
    public void check2() {
        String[] sentence1 = new String[]{"I", "love", "leetcode"};
        String[] sentence2 = new String[]{"I", "love", "onepiece"};
        String[][] similar = Utils.to2dStringMatrix("[[\"manga\",\"onepiece\"],[\"platform\",\"anime\"],[\"leetcode\",\"platform\"],[\"anime\",\"manga\"]]");
        Assertions.assertEquals(true, areSentencesSimilarTwo(sentence1, sentence2, Arrays.stream(similar).map(a -> Arrays.stream(a).collect(Collectors.toList())).collect(Collectors.toList())));
    }

    @Test
    public void check3() {
        String[] sentence1 = new String[]{"I","love","leetcode"};
        String[] sentence2 = new String[]{"I","love","onepiece"};
        String[][] similar = Utils.to2dStringMatrix("[[\"manga\",\"hunterXhunter\"],[\"platform\",\"anime\"],[\"leetcode\",\"platform\"],[\"anime\",\"manga\"]]");
        Assertions.assertEquals(false, areSentencesSimilarTwo(sentence1, sentence2, Arrays.stream(similar).map(a -> Arrays.stream(a).collect(Collectors.toList())).collect(Collectors.toList())));
    }

}

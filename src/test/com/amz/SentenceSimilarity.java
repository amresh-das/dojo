package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @see "https://leetcode.com/problems/sentence-similarity/"
 */
public class SentenceSimilarity {

    public boolean areSentencesSimilar(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        if (sentence1.length != sentence2.length) return false;
        Map<String, Set<String>> map = new HashMap<>();
        for (final List<String> pair : similarPairs) {
            map.compute(pair.get(0), (k,v) -> {
               Set<String> set = v == null ? new HashSet<>() : v;
                if (v == null) set.add(k);
                set.add(pair.get(1));
                return set;
            });
            map.compute(pair.get(1), (k,v) -> {
                Set<String> set = v == null ? new HashSet<>() : v;
                if (v == null) set.add(k);
                set.add(pair.get(0));
                return set;
            });
        }
        for (int i = 0; i < sentence1.length; i++) {
            if (sentence1[i].equals(sentence2[i])) continue;
            if (!map.containsKey(sentence1[i])) return false;
            if (!map.get(sentence1[i]).contains(sentence2[i])) return false;
        }
        return true;
    }

    @Test
    public void check1() {
        String[] sentence1 = new String[] {"great","acting","skills"};
        String[] sentence2 = new String[] {"fine","drama","talent"};
        String[][] similar = Utils.to2dStringMatrix("[[\"great\",\"fine\"],[\"drama\",\"acting\"],[\"skills\",\"talent\"]]");
        Assertions.assertEquals(true, areSentencesSimilar(sentence1, sentence2, Arrays.stream(similar).map(a -> Arrays.stream(a).collect(Collectors.toList())).collect(Collectors.toList())));
    }

    @Test
    public void check2() {
        String[] sentence1 = new String[] {"great"};
        String[] sentence2 = new String[] {"great"};
        Assertions.assertEquals(true, areSentencesSimilar(sentence1, sentence2, new ArrayList<>()));
    }

    @Test
    public void check3() {
        String[] sentence1 = new String[] {"great"};
        String[] sentence2 = new String[] {"doubleplus","good"};
        String[][] similar = Utils.to2dStringMatrix("[[\"great\",\"doubleplus\"]]");
        Assertions.assertEquals(false, areSentencesSimilar(sentence1, sentence2, Arrays.stream(similar).map(a -> Arrays.stream(a).collect(Collectors.toList())).collect(Collectors.toList())));
    }

}

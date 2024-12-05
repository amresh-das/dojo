package com.amz;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @see "https://leetcode.com/problems/group-anagrams/"
 */
public class GroupAnagrams {

    @Test
    void t1() {
        List<String> input = Lists.newArrayList("eat", "tea", "tan", "ate", "nat", "bat");
        Assertions.assertEquals(Sets.newHashSet(
                Sets.newHashSet("ate","eat","tea"),
                Sets.newHashSet("nat","tan"),
                Sets.newHashSet("bat")
        ), groupAnagrams(input.toArray(new String[0])).stream().map(HashSet::new).collect(Collectors.toSet()));
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> group = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            group.compute(String.valueOf(chars), (k, v) -> {
                List<String> values = v == null ? new ArrayList<>() : v;
                values.add(s);
                return values;
            });
        }
        return new ArrayList<>(group.values());
    }

}

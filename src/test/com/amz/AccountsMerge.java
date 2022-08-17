package com.amz;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @see "https://leetcode.com/problems/accounts-merge/"
 */
public class AccountsMerge {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Integer> counts = new HashMap<>();
        Map<String, SortedSet<String>> map = new HashMap<>();
        Set<String> primary = new HashSet<>();

        for (List<String> list : accounts) {
            String name = list.get(0);
            int count = counts.getOrDefault(name, 0);
            List<String> matches = new ArrayList<>();
            for (int id = 1; id <= count; id++) {
                SortedSet<String> emails = map.get(id + "." + name);
                for (int i = 1; i < list.size(); i++) {
                    if (emails.contains(list.get(i))) {
                        matches.add(id + "." + name);
                        break;
                    }
                }
            }
            String pId = (count + 1) + "." + list.get(0);
            SortedSet<String> emails = new TreeSet<>(list);
            map.put(pId, emails);
            counts.put(list.get(0), count + 1);
            primary.add(pId);
            if (!matches.isEmpty()) {
                for (String match : matches) {
                    primary.remove(match);
                    List<String> prevEmails = new ArrayList<>(map.get(match));
                    emails.addAll(prevEmails.subList(1, prevEmails.size()));
                    map.get(match).clear();
                }
            }
        }
        return primary.stream().map(map::get).map(ArrayList::new).collect(Collectors.toList());
    }

    @Test
    public void check1() {
        final List<List<String>> input = Lists.newArrayList(
                Lists.newArrayList("John","johnsmith@mail.com","john_newyork@mail.com"),
                Lists.newArrayList("John","johnsmith@mail.com","john00@mail.com"),
                Lists.newArrayList("Mary","mary@mail.com"),
                Lists.newArrayList("John","johnnybravo@mail.com")
        );

        List<List<String>> actual = accountsMerge(input);
        Assertions.assertTrue(actual.contains(Lists.newArrayList("John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com")));
        Assertions.assertTrue(actual.contains(Lists.newArrayList("Mary","mary@mail.com")));
        Assertions.assertTrue(actual.contains(Lists.newArrayList("John","johnnybravo@mail.com")));
        System.out.println(actual);
    }

    @Test
    public void check2() {
        final List<List<String>> input = Lists.newArrayList(
                Lists.newArrayList("David","David0@m.co","David1@m.co"),
                Lists.newArrayList("David","David3@m.co","David4@m.co"),
                Lists.newArrayList("David","David4@m.co","David5@m.co"),
                Lists.newArrayList("David","David2@m.co","David3@m.co"),
                Lists.newArrayList("David","David1@m.co","David2@m.co")
        );

        List<List<String>> actual = accountsMerge(input);
        Assertions.assertTrue(actual.contains(Lists.newArrayList("David","David0@m.co","David1@m.co","David2@m.co","David3@m.co","David4@m.co","David5@m.co")));
        System.out.println(actual);
    }
}

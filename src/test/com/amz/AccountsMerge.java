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
        Map<String, Integer> emailAccounts = new HashMap<>();
        Map<Integer, String> names = new HashMap<>();
        DSU dsu = new DSU(accounts.size());
        for (int i = 0; i < accounts.size(); i++) {
            List<String> list = accounts.get(i);
            final String name = list.get(0);
            names.put(i, name);
            List<String> emails = list.subList(1, list.size());
            for (String email : emails) {
                if (emailAccounts.containsKey(email)) {
                    int other = emailAccounts.get(email);
                    dsu.union(i, other);
                } else {
                    emailAccounts.put(email, i);
                }
            }
        }
        Map<Integer, SortedSet<String>> accountEmails = new HashMap<>();
        emailAccounts.forEach((email, id) -> {
            int accountId = dsu.find(id);
            accountEmails.computeIfAbsent(accountId, k -> new TreeSet<>()).add(email);
        });
        return accountEmails.entrySet().stream().map(e -> {
            final List<String> x = new ArrayList<>();
            x.add(names.get(e.getKey()));
            x.addAll(e.getValue());
            return x;
        }).collect(Collectors.toList());
    }

    class DSU {
        private final int[] parents;
        private final int[] sizes;

        DSU(int size) {
            this.parents = new int[size];
            for (int i = 0; i < size; i++) {
                parents[i] = i;
            }
            this.sizes = new int[size];
            Arrays.fill(sizes, 1);
        }

        public void union(int a, int b) {
            int aParent = find(a);
            int bParent = find(b);
            if (sizes[aParent] > sizes[bParent]) {
                parents[bParent] = aParent;
                sizes[aParent] += sizes[bParent];
            } else {
                parents[aParent] = bParent;
                sizes[bParent] += sizes[aParent];
            }
        }

        public int find(int a) {
            int parent = a;
            while (parents[parent] != parent) {
                parents[parent] = parents[parents[parent]];
                parent = parents[parent];
            }
            return parent;
        }

        public boolean isDisjoint(int a, int b) {
            return find(a) != find(b);
        }
    }

    public List<List<String>> accountsMerge2(List<List<String>> accounts) {
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
                Lists.newArrayList("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                Lists.newArrayList("John", "johnsmith@mail.com", "john00@mail.com"),
                Lists.newArrayList("Mary", "mary@mail.com"),
                Lists.newArrayList("John", "johnnybravo@mail.com")
        );

        List<List<String>> actual = accountsMerge(input);
        Assertions.assertTrue(actual.contains(Lists.newArrayList("John", "john00@mail.com", "john_newyork@mail.com", "johnsmith@mail.com")));
        Assertions.assertTrue(actual.contains(Lists.newArrayList("Mary", "mary@mail.com")));
        Assertions.assertTrue(actual.contains(Lists.newArrayList("John", "johnnybravo@mail.com")));
        System.out.println(actual);
    }

    @Test
    public void check2() {
        final List<List<String>> input = Lists.newArrayList(
                Lists.newArrayList("David", "David0@m.co", "David1@m.co"),
                Lists.newArrayList("David", "David3@m.co", "David4@m.co"),
                Lists.newArrayList("David", "David4@m.co", "David5@m.co"),
                Lists.newArrayList("David", "David2@m.co", "David3@m.co"),
                Lists.newArrayList("David", "David1@m.co", "David2@m.co")
        );

        List<List<String>> actual = accountsMerge(input);
        System.out.println(actual);
        Assertions.assertTrue(actual.contains(Lists.newArrayList("David", "David0@m.co", "David1@m.co", "David2@m.co", "David3@m.co", "David4@m.co", "David5@m.co")));
        System.out.println(actual);
    }
}

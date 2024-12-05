package com.amz;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @see "https://leetcode.com/problems/smallest-common-region/"
 */
public class SmallestCommonRegion {
    final Map<String, Set<String>> contains = new HashMap<>();
    final Map<String, String> parents = new HashMap<>();

    public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
        if (region1.equals(region2)) return region1;
        for (final List<String> region : regions) {
            final String parent = region.get(0);
            final List<String> constituents = region.subList(1, region.size());
            contains.put(parent, new HashSet<>(constituents));
            constituents.forEach(c -> parents.put(c, parent));
        }
        List<String> region1Parents = findParents(region1);
        List<String> region2Parents = findParents(region2);
        String parent = null;
        for (int i = 0; i < Math.min(region1Parents.size(), region2Parents.size()); i++) {
            if (region1Parents.get(i).equals(region2Parents.get(i))) {
                parent = region1Parents.get(i);
            } else {
                break;
            }
        }
        return parent;
    }

    private List<String> findParents(final String region) {
        if (region == null) return new ArrayList<>();
        List<String> list = findParents(parents.get(region));
        list.add(region);
        return list;
    }

    @Test
    public void check1() {
        List<List<String>> regions = Lists.newArrayList(
            Lists.newArrayList("Earth","North America","South America"),
            Lists.newArrayList("North America","United States","Canada"),
            Lists.newArrayList("United States","New York","Boston"),
            Lists.newArrayList("Canada","Ontario","Quebec"),
            Lists.newArrayList("South America","Brazil")
        );
        String region1 = "Quebec";
        String region2 = "New York";
        Assertions.assertEquals("North America", findSmallestRegion(regions, region1, region2));
    }

    @Test
    public void check2() {
        List<List<String>> regions = Lists.newArrayList(
            Lists.newArrayList("Earth","North America","South America"),
            Lists.newArrayList("North America","United States","Canada"),
            Lists.newArrayList("United States","New York","Boston"),
            Lists.newArrayList("Canada","Ontario","Quebec"),
            Lists.newArrayList("South America","Brazil")
        );
        String region1 = "Canada";
        String region2 = "South America";
        Assertions.assertEquals("Earth", findSmallestRegion(regions, region1, region2));
    }

    @Test
    public void check3() {
        List<List<String>> regions = Lists.newArrayList(
            Lists.newArrayList("Earth","North America","South America"),
            Lists.newArrayList("North America","United States","Canada"),
            Lists.newArrayList("United States","New York","Boston"),
            Lists.newArrayList("Canada","Ontario","Quebec"),
            Lists.newArrayList("South America","Brazil")
        );
        String region1 = "North America";
        String region2 = "Quebec";
        Assertions.assertEquals("North America", findSmallestRegion(regions, region1, region2));
    }

    @Test
    public void check4() {
        List<List<String>> regions = Lists.newArrayList(
            Lists.newArrayList("Earth","North America","South America"),
            Lists.newArrayList("North America","United States","Canada"),
            Lists.newArrayList("United States","New York","Boston"),
            Lists.newArrayList("Canada","Ontario","Quebec"),
            Lists.newArrayList("South America","Brazil")
        );
        String region1 = "Boston";
        String region2 = "North America";
        Assertions.assertEquals("North America", findSmallestRegion(regions, region1, region2));
    }

}

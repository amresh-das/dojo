package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Permutations {

    public List<List<Integer>> permute(int[] nums) {
        final ArrayList<List<Integer>> result = new ArrayList<>();
        permute(result, new ArrayList<>(), Arrays.stream(nums).boxed().collect(Collectors.toList()));
        return result;
    }

    private void permute(final List<List<Integer>> result, final List<Integer> current, final List<Integer> remaining) {
        if (remaining.isEmpty()) {
            result.add(current);
        } else {
            int n = remaining.get(0);
            final List<Integer> remainingNext = cloneListAndRemove(remaining, 0);
            for (int i = 0; i <= current.size(); i++) {
                permute(result, cloneListAndAdd(current, n, i), remainingNext);
            }
        }
    }

    private List<Integer> cloneListAndAdd(final List<Integer> current, final int n, final int i) {
        List<Integer> next = new ArrayList<>(current);
        next.add(i, n);
        return next;
    }

    private List<Integer> cloneListAndRemove(final List<Integer> current, final int pos) {
        List<Integer> next = new ArrayList<>(current);
        next.remove(pos);
        return next;
    }

    @Test
    void t1() {
        Assertions.assertEquals("[[2,1],[1,2]]", permute("[1,2]"));
    }

    @Test
    void t2() {
        Assertions.assertEquals("[[3,2,1],[2,3,1],[2,1,3],[3,1,2],[1,3,2],[1,2,3]]", permute("[1,2,3]"));
    }

    private String permute(final String input) {
        int[] in = Arrays.stream(input.replaceAll("[\\[\\]]", "").split(",")).mapToInt(Integer::parseInt).toArray();
        final List<List<Integer>> output = permute(in);
        return "[" + output.stream().map(i -> "[" + i.stream().map(Object::toString).collect(Collectors.joining(",")) + "]").collect(Collectors.joining(",")) + "]";
    }
}

package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PermutationsII {
    public List<List<Integer>> permuteUnique(int[] nums) {
        final ArrayList<List<Integer>> result = new ArrayList<>();
        permute(result, new ArrayList<>(), Arrays.stream(nums).boxed().collect(Collectors.toList()));
        return result;
    }

    private void permute(final List<List<Integer>> result, final List<Integer> current, final List<Integer> remaining) {
        if (remaining.isEmpty()) {
            if (!result.contains(current)) {
                result.add(current);
            }
        } else {
            int term = remaining.get(0);
            remaining.remove(0);
            for (int i = 0; i <= current.size(); i++) {
                List<Integer> next = new ArrayList<>(current);
                next.add(i, term);
                permute(result, next, remaining);
            }
            remaining.add(0, term);
        }
    }

    @Test
    void t1() {
        Assertions.assertEquals("[[2,1],[1,2]]", permute("[1,2]"));
    }

    @Test
    void t2() {
        Assertions.assertEquals("[[2,1,1],[1,2,1],[1,1,2]]", permute("[1,1,2]"));
    }

    @Test
    void t3() {
        Assertions.assertEquals("[[3,2,1],[2,3,1],[2,1,3],[3,1,2],[1,3,2],[1,2,3]]", permute("[1,2,3]"));
    }

    private String permute(final String input) {
        int[] in = Utils.stringToIntArray(input);
        final List<List<Integer>> output = permuteUnique(in);
        return Utils.listOfIntegerListToString(output);
    }

}

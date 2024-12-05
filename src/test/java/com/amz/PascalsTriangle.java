package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @see "https://leetcode.com/problems/pascals-triangle/"
 */
public class PascalsTriangle {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>(numRows);
        if (numRows > 0) {
            List<Integer> prev = Arrays.asList(1);
            ans.add(prev);
            for (int i = 2; i <= numRows; i++) {
                final List<Integer> next = new ArrayList<>();
                next.add(1);
                for (int j = 0; j < prev.size() - 1; j++) {
                    next.add(prev.get(j) + prev.get(j + 1));
                }
                next.add(1);
                ans.add(next);
                prev = next;
            }
        }
        return ans;
    }

    @Test
    public void check1() {
        Assertions.assertEquals("[[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]", Utils.listOfIntegerListToString(generate(5)));
    }

    @Test
    public void check2() {
        Assertions.assertEquals("[[1]]", Utils.listOfIntegerListToString(generate(1)));
    }


}

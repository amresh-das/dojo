package com.amz;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/**
 * @see "https://leetcode.com/problems/count-of-smaller-numbers-after-self/"
 */
public class CountOfSmallerNumbersAfterSelf {
    public List<Integer> countSmaller(int[] nums) {
        int offset = 10000;
        int size = 2 * offset + 1;
        int[] tree = new int[size * 2];
        List<Integer> ans = new ArrayList<>();

        for (int i = nums.length - 1; i >= 0; i--) {
            int count = query(0, nums[i] + offset, tree, size);
            ans.add(count);
            update(nums[i] + offset, 1, tree, size);
        }
        Collections.reverse(ans);
        return ans;
    }

    private void update(int index, int value, int[] tree, int size) {
        index += size;
        tree[index] += value;
        while (index > 1) {
            index /= 2;
            tree[index] = tree[index * 2] + tree[index * 2 + 1];
        }
    }

    private int query(int left, int right, int[] tree, int size) {
        int result = 0;
        left += size;
        right += size;
        while (left < right) {
            if (left % 2 == 1) {
                result += tree[left++];
            }
            left /= 2;
            if (right % 2 == 1) {
                result += tree[--right];
            }
            right /= 2;
        }
        return result;
    }

    public List<Integer> countSmaller1(int[] nums) {
        final List<Integer> ans = new ArrayList<>(nums.length);
        final TreeMap<Integer, Integer> counts = new TreeMap<>();
        for (int i = nums.length - 1; i >= 0 ; i--) {
            int n = nums[i];
            int count = 0;
            for (int x : counts.keySet()) {
                if (x >= n) break;
                count += counts.get(x);
            }
            counts.compute(n, (k, v) -> v == null ? 1 : v + 1);
            ans.add(0, count);
        }
        return ans;
    }

    @Test
    public void check1() {
        Assertions.assertEquals(Lists.newArrayList(2,1,1,0), countSmaller(Utils.stringToIntArray("5,2,6,1")));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(Lists.newArrayList(0), countSmaller(Utils.stringToIntArray("-1")));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(Lists.newArrayList(0,0), countSmaller(Utils.stringToIntArray("-1,-1")));
    }


}

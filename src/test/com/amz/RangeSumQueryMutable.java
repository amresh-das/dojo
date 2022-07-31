package com.amz;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @see "https://leetcode.com/problems/range-sum-query-mutable/"
 */
public class RangeSumQueryMutable {

    class NumArray {
        private final int[] tree;
        private final int n;

        public NumArray(int[] nums) {
            n = nums.length;
            tree = new int[2 * n];
            buildTree(nums);
        }

        private void buildTree(int[] nums) {
            for (int i = n, j = 0; i < 2 * n; i++, j++) {
                tree[i] = nums[j];
            }
            for (int i = n - 1; i >= 0; i--) {
                tree[i] = tree[2 * i] + tree[2 * i + 1];
            }
        }

        public void update(int index, int val) {
            int pos = n + index;
            tree[pos] = val;
            while (pos > 0) {
                int left = pos % 2 == 0 ? pos : pos - 1;
                int right = pos % 2 == 0 ? pos + 1 : pos;
                tree[pos / 2] = tree[left] + tree[right];
                pos /= 2;
            }
        }

        public int sumRange(int left, int right) {
            int l = left + n;
            int r = right + n;
            int sum = 0;
            while (l <= r) {
                if (l % 2 == 1) sum += tree[l++];
                if (r % 2 == 0) sum += tree[r--];
                l /= 2;
                r /= 2;
            }
            return sum;
        }
    }

    @Test
    public void check1() {
        NumArray numArray = new NumArray(new int[] {1, 3, 5});
        final List<Integer> actual = new ArrayList<>();
        actual.add(null);
        actual.add(numArray.sumRange(0, 2));
        numArray.update(1, 2);
        actual.add(null);
        actual.add(numArray.sumRange(0, 2));
        Assertions.assertEquals(Lists.newArrayList(null, 9, null, 8), actual);
    }

}

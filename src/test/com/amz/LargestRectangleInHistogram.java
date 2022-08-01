package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * @see "https://leetcode.com/problems/largest-rectangle-in-histogram/"
 */
public class LargestRectangleInHistogram {

    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        final Stack<Integer> idxStack = new Stack<>();
        idxStack.push(-1);
        for (int i = 0; i < heights.length; i++) {
            int current = heights[i];
            while (idxStack.peek() != -1 && heights[idxStack.peek()] >= current) {
                int currentMax = heights[idxStack.pop()];
                int width = i - idxStack.peek() - 1;
                maxArea = Math.max(maxArea, currentMax * width);
            }
            idxStack.push(i);
        }
        while (idxStack.peek() != -1) {
            int currentMax = heights[idxStack.pop()];
            int width = heights.length - idxStack.peek() - 1;
            maxArea = Math.max(maxArea, currentMax * width);
        }
        return maxArea;
    }

    public int largestRectangleArea2(int[] heights) {
        int[] segmentTree = new int[heights.length * 2];
        buildTree(heights, segmentTree);
        return largestRectangleArea(heights, segmentTree, 0, heights.length - 1);
    }

    private int largestRectangleArea(int[] heights, int[] segmentTree, int lo, int hi) {
        if (lo > hi) return 0;
        if (lo == hi) return heights[lo];
        int minHeightIndex = query(segmentTree, heights, lo, hi);
        int left = largestRectangleArea(heights, segmentTree, lo, minHeightIndex - 1);
        int right = largestRectangleArea(heights, segmentTree, minHeightIndex + 1, hi);
        return Math.max(Math.max(heights[minHeightIndex] * (hi - lo + 1), left), right);
    }

    private void buildTree(int[] heights, int[] tree) {
        int n = heights.length;
        for (int i = n, j = 0; i < 2 * n; i++, j++) {
            tree[i] = j;
        }
        for (int i = n - 1; i >= 0; i--) {
            tree[i] = heights[tree[2 * i]] < heights[tree[2 * i + 1]] ? tree[2 * i] : tree[2 * i + 1];
        }
    }

    private int query(int[] segmentTree, int[] heights, int lo, int hi) {
        int n = heights.length;
        int l = lo + n;
        int r = hi + n;
        int min = Integer.MAX_VALUE;
        int index = -1;
        while(l <= r) {
            if(l % 2 == 1) {
                int idx = segmentTree[l++];
                if(heights[idx] < min) {
                    min = heights[idx];
                    index = idx;
                }
            }
            if(r % 2 == 0) {
                int idx = segmentTree[r--];
                if(heights[idx] < min) {
                    min = heights[idx];
                    index = idx;
                }
            }
            l /= 2;
            r /= 2;
        }
        return index;

    }

    @Test
    public void check1() {
        Assertions.assertEquals(10, largestRectangleArea(Utils.stringToIntArray("[2,1,5,6,2,3]")));
    }

    @Test
    public void check2() {
        Assertions.assertEquals(4, largestRectangleArea(Utils.stringToIntArray("[2,4]")));
    }

    @Test
    public void check3() {
        Assertions.assertEquals(4, largestRectangleArea(Utils.stringToIntArray("[2,4]")));
    }

    @Test
    public void check4() {
        Assertions.assertEquals(2, largestRectangleArea(Utils.stringToIntArray("[2,0,2]")));
    }

    @Test
    public void check5() {
        Assertions.assertEquals(9, largestRectangleArea(Utils.stringToIntArray("[1,2,3,4,5]")));
    }

}

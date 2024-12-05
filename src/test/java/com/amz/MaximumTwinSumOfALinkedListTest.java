package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * @see "https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/description/?envType=study-plan-v2&envId=leetcode-75"
 */
public class MaximumTwinSumOfALinkedListTest {

    public int pairSum(ListNode head) {
        if (head == null) return 0;
        ListNode fastPtr = head;
        ListNode slowPtr = head;
        final Stack<Integer> stack = new Stack<>();
        while (fastPtr != null) {
            stack.push((Integer) slowPtr.val);
            fastPtr = fastPtr.next.next;
            slowPtr = slowPtr.next;
        }
        int maxSum = stack.pop() + (Integer) slowPtr.val;
        while (true) {
            slowPtr = slowPtr.next;
            if (slowPtr == null) break;
            int sum = stack.pop() + (Integer) slowPtr.val;
            if (sum > maxSum) maxSum = sum;
        }
        return maxSum;
    }

    @Test
    void check1() {
        Assertions.assertEquals(6, pairSum(ListNode.create("5,4,2,1")));
    }

    @Test
    void check2() {
        Assertions.assertEquals(7, pairSum(ListNode.create("4,2,2,3")));
    }

    @Test
    void check3() {
        Assertions.assertEquals(100001, pairSum(ListNode.create("1,100000")));
    }

    @Test
    void check4() {
        Assertions.assertEquals(11, pairSum(ListNode.create("4,6,5,3")));
    }
}

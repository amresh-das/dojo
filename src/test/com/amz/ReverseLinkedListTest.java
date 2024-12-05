package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * @see "https://leetcode.com/problems/reverse-linked-list/description/?envType=study-plan-v2&envId=leetcode-75"
 */
public class ReverseLinkedListTest {
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            ListNode next = head.next;
            head.next = null;
            stack.push(head);
            head = next;
        }
        ListNode newHead = stack.pop();
        ListNode ptr = newHead;
        while (!stack.isEmpty()) {
            ptr.next = stack.pop();
            ptr = ptr.next;
        }
        return newHead;
    }

    @Test
    void check1() {
        Assertions.assertEquals("[5,4,3,2,1]", reverseList(ListNode.create(1,2,3,4,5)).toString());
    }

    @Test
    void check2() {
        Assertions.assertEquals("[2,1]", reverseList(ListNode.create(1,2)).toString());
    }

    @Test
    void check3() {
        Assertions.assertEquals(null, reverseList(ListNode.create()));
    }
}

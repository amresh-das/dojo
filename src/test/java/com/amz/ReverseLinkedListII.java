package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * @see "https://leetcode.com/problems/reverse-linked-list-ii/"
 */
public class ReverseLinkedListII {

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) return head;
        final Stack<ListNode> stack = new Stack<>();
        ListNode node = head, beforeLeft = null, afterRight = null;
        int index = 1;
        while (node != null) {
            if (index < left) beforeLeft = node;
            if (index >= left) stack.push(node);
            if (index == right) {
                afterRight = node.next;
                break;
            }
            node = node.next;
            index++;
        }
        if (left == 1) {
            head = stack.pop();
            beforeLeft = head;
        }
        if (beforeLeft != null) {
            node = beforeLeft;
            while (!stack.isEmpty()) {
                node.next = stack.pop();
                node = node.next;
            }
            node.next = afterRight;
        }
        return head;
    }

    @Test
    public void check1() {
        verify("[1,4,3,2,5]", "[1,2,3,4,5]", 2, 4);
    }

    @Test
    public void check2() {
        verify("[5]", "[5]", 1, 1);
    }

    @Test
    public void check3() {
        verify("[5,3]", "[3,5]", 1, 2);
    }

    private void verify(final String expected, final String input, final int left, final int right) {
        Assertions.assertEquals(ListNode.create(expected), reverseBetween(ListNode.create(input), left, right));
    }

}

package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * @see "https://leetcode.com/problems/reverse-linked-list/"
 */
public class ReverseSinglyLinkedList {

    @Test
    void t1() {
        String input = "1->2->3->4->5->NULL";
        String expected = "5->4->3->2->1->NULL";

        Assertions.assertEquals(new ListNode(expected), reverseList(new ListNode(input)));
    }

    @Test
    void t2() {
        Assertions.assertEquals(null, reverseList(null));
    }

    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        Stack<ListNode> nodes = new Stack<>();
        ListNode node = head;
        while (node != null) {
            nodes.push(node);
            node = node.next;
        }
        node = nodes.pop();
        ListNode newHead = node;
        while (!nodes.isEmpty()) {
            node.next = new ListNode(nodes.pop().val);
            node = node.next;
        }
        return newHead;
    }

}

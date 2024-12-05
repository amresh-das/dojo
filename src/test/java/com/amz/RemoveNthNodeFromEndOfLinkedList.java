package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/remove-nth-node-from-end-of-list/"
 */
public class RemoveNthNodeFromEndOfLinkedList {

    @Test
    void t1() {
        ListNode head = new ListNode("1->2->3->4->5->NULL");
        ListNode expected = new ListNode("1->2->3->5->NULL");
        Assertions.assertEquals(expected, removeNthFromEnd(head, 2));
    }

    @Test
    void t2() {
        ListNode head = new ListNode("1->NULL");
        Assertions.assertEquals(null, removeNthFromEnd(head, 1));
    }

    @Test
    void t3() {
        ListNode head = new ListNode("1->2->NULL");
        Assertions.assertEquals(new ListNode("2->NULL"), removeNthFromEnd(head, 2));
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int count = -n;
        if (head == null) return null;
        int length = 1;
        ListNode curr = head;
        ListNode previousFromOneToDelete = null;
        while ((curr = curr.next) != null) {
            count++;
            length++;
            if (count == 0) {
                previousFromOneToDelete = head;
            } else {
                previousFromOneToDelete = previousFromOneToDelete == null ? null : previousFromOneToDelete.next;
            }
        }
        if (n == length) return head.next;
        if (previousFromOneToDelete == null) return null;
        previousFromOneToDelete.next = previousFromOneToDelete.next.next;
        return head;
    }
}

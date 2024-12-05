package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/swap-nodes-in-pairs/"
 */
public class SwapNodePairs {

    @Test
    void t1() {
        ListNode in = new ListNode("1->2->3->4->NULL");
        ListNode expected = new ListNode("2->1->4->3->NULL");
        Assertions.assertEquals(expected, swapPairs(in));
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;
        ListNode nxt = head.next;
        head.next = head.next.next;
        nxt.next = head;
        if (head.next != null) {
            head.next = swapPairs(head.next);
        }
        return nxt;
    }
}

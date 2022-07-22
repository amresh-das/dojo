package com.amz;

import com.amz.leet.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/partition-list/"
 */
public class PartitionList {

    public ListNode partition(ListNode head, int x) {
        if (head == null) return null;
        ListNode alteredHead = head;
        while (alteredHead != null) {
            alteredHead = partition(null, alteredHead, x);
            if (alteredHead != null) head = alteredHead;
        }
        return head;
    }

    private ListNode partition(ListNode parent, ListNode current, int x) {
        if (current == null) return null;
        if (current.val < x && parent != null && parent.val >= x) {
            parent.next = current.next;
            current.next = null;
            return current;
        }
        ListNode node = partition(current, current.next, x);
        if (node != null) {
            if (parent == null) {
                node.next = current;
                return node;
            }
            if (parent.val >=x) return node;
            node.next = current;
            parent.next = node;
            return partition(node, node.next, x);
        }
        return null;
    }

    @Test
    public void check1() {
        verify("[1,2,2,4,3,5]", "[1,4,3,2,5,2]", 3);
    }

    @Test
    public void check2() {
        verify("[1,2]", "[2,1]", 2);
    }

    private void verify(String expected, String input, int x) {
        ListNode initial = ListNode.create(input);
        System.out.println("Initial: " + initial);
        Assertions.assertEquals(ListNode.create(expected).toString(), partition(initial, x).toString());
    }


}

package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @see "https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/description/?envType=study-plan-v2&envId=leetcode-75"
 */
public class DeleteTheMiddleNodeOfALinkedListTest {

    public ListNode deleteMiddle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode fast = head;
        ListNode slow = null;
        while (fast != null) {
            if (fast.next == null)  break;
            fast = fast.next.next;
            slow = slow == null ? head : slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    public ListNode deleteMiddle2(ListNode head) {
        if (head == null || head.next == null) return null;
        List<ListNode> nodes = new ArrayList<>();
        ListNode p = head;
        while (p != null) {
            nodes.add(p);
            p = p.next;
        }
        ListNode prevToMid = nodes.get(nodes.size() / 2 - 1);
        prevToMid.next = prevToMid.next.next;
        return head;
    }

    @Test
    void check1() {
        Assertions.assertEquals("[1,3,4,1,2,6]", deleteMiddle(ListNode.create(1,3,4,7,1,2,6)).toString());
    }

    @Test
    void check2() {
        Assertions.assertEquals("[1,2,4]", deleteMiddle(ListNode.create(1,2,3,4)).toString());
    }

    @Test
    void check3() {
        Assertions.assertEquals(null, deleteMiddle(ListNode.create(1)));
    }

    @Test
    void check4() {
        Assertions.assertEquals(null, deleteMiddle(ListNode.create()));
    }
}

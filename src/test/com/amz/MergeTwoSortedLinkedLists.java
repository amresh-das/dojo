package com.amz;

import com.amz.leet.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MergeTwoSortedLinkedLists {

    @Test
    void t1() {
        ListNode l1 = new ListNode("1->2->4->NULL");
        ListNode l2 = new ListNode("1->3->4->NULL");
        ListNode expected = new ListNode("1->1->2->3->4->4->NULL");

        Assertions.assertEquals(expected, mergeTwoLists(l1, l2));
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode output;
        if (l1.val < l2.val) {
            output = new ListNode(l1.val);
            l1 = l1.next;
        } else {
            output = new ListNode(l2.val);
            l2 = l2.next;
        }
        ListNode link = output;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                link.next = new ListNode(l1.val);
                l1 = l1.next;
                link = link.next;
            } else {
                link.next = new ListNode(l2.val);
                l2 = l2.next;
                link = link.next;
            }
        }
        if (l1 == null) {
            link.next = l2;
        } else {
            link.next = l1;
        }
        return output;
    }
}

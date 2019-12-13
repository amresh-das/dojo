package com.amz;

import com.amz.leet.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/merge-k-sorted-lists/"
 */
public class MergeKSortedLinkedLists {

    @Test
    void t1() {
        ListNode[] lists = new ListNode[] {
                new ListNode("1->4->5->NULL"),
                new ListNode("1->3->4->NULL"),
                new ListNode("2->6->NULL")
        };
        ListNode expected = new ListNode("1->1->2->3->4->4->5->6->NULL");
        Assertions.assertEquals(expected, mergeKLists(lists));
    }

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode output = new ListNode(0);
        ListNode link = output;
        boolean hasRemaining = true;

        while (hasRemaining) {
            int nextMin = Integer.MAX_VALUE;
            int nexMinListIndex = -1;
            for (int i = 0; i < lists.length; i++) {
                ListNode t = lists[i];
                if (t != null && nextMin > t.val) {
                    nextMin = t.val;
                    nexMinListIndex = i;
                }
            }
            if (nexMinListIndex == -1) {
                hasRemaining = false;
            } else {
                link.next = lists[nexMinListIndex];
                link = link.next;
                lists[nexMinListIndex] = lists[nexMinListIndex].next;
            }
        }
        return output.next;
    }
}

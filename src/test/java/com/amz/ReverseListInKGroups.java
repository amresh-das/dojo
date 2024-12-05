package com.amz;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/reverse-nodes-in-k-group/"
 */
public class ReverseListInKGroups {

    private ListNode input;

    @BeforeEach
    public void init() {
        input = new ListNode("1->2->3->4->5->NULL");
    }

    @Test
    void t1() {
        Assertions.assertThat(reverseKGroup(input, 2)).isEqualTo(new ListNode("2->1->4->3->5->NULL"));
    }

    @Test
    void t2() {
        Assertions.assertThat(reverseKGroup(input, 3)).isEqualTo(new ListNode("3->2->1->4->5->NULL"));
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode[] segment = new ListNode[k];
        ListNode temp = head;
        for (int i = 0; i < k; i++) {
            if (temp == null) return head;
            segment[k - i - 1] = temp;
            temp = temp.next;
        }
        ListNode startOfNextSegment = segment[0].next;
        for (int i = 0; i < k - 1; i++) {
            ListNode curr = segment[i];
            ListNode next = segment[i + 1];
            next.next = null;
            curr.next = next;
        }
        segment[k - 1].next = reverseKGroup(startOfNextSegment, k);
        return segment[0];
    }

}

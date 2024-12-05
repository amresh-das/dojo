package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/rotate-list/"
 */
public class RotateList {

    public ListNode rotateRight(ListNode head, int k) {
        int size = 0;
        if (head == null) return null;
        if (k == 0) return head;
        ListNode node = head, end = head;
        while (node != null) {
            if (node.next == null) end = node;
            node = node.next;
            size++;
        }
        int toRotate = k % size;
        if (toRotate == 0) return head;
        node = head;
        for (int i = 0; i < size - toRotate - 1; i++) {
            node = node.next;
        }
        ListNode newHead;
        newHead = node.next;
        node.next = null;
        end.next = head;
        return newHead;
    }

    public ListNode rotateRight2(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) return head;
        int t = 1;
        ListNode temp = head;
        ListNode end = null;
        ListNode trailing = head;
        ListNode newEnd = null;
        while(true) {
            if (temp.next == null) {
                if (t < k) {
                    temp = head;
                } else {
                    end = temp;
                    break;
                }
            } else {
                temp = temp.next;
            }
            if (t >= k) {
                newEnd = trailing;
                trailing = trailing.next;
            }
            t++;
        }
        if (newEnd == null) {
            return head;
        } else {
            newEnd.next = null;
            end.next = head;
            return trailing;
        }
    }

    @Test
    void t1() {
        verify("[1,2,3,4,5]", 2, "[4,5,1,2,3]");
    }

    @Test
    void t2() {
        verify("[0,1,2]", 4, "[2,0,1]");
    }

    @Test
    void t3() {
        verify("[]", 0, "[]");
    }

    @Test
    void t4() {
        verify("[1,2]", 2, "[1,2]");
    }

    @Test
    void t5() {
        verify("[1]", 0, "[1]");
    }

    @Test
    void t6() {
        verify("[1,2]", 1, "[2,1]");
    }

    @Test
    void t7() {
        verify("[1,2,3,4,5]", 1, "[5,1,2,3,4]");
    }

    private void verify(final String initial, final int k, final String expected) {
        final ListNode actual = rotateRight(Utils.toListNode(initial), k);
        Assertions.assertEquals(expected, Utils.toString(actual));
    }
}

package com.amz;

import java.util.Objects;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public static ListNode create(final String input) {
        int[] in = Utils.stringToIntArray(input);
        ListNode head = new ListNode(in[0]);
        ListNode current = head;
        for (int i = 1; i < in.length; i++) {
            current.next = new ListNode(in[i]);
            current = current.next;
        }
        return head;
    }

    public ListNode(String x) {
        String valStr = x.substring(0, x.indexOf('-'));
        this.val = Integer.parseInt(valStr);
        String nxt = x.substring(valStr.length() + 2);
        if ("NULL".equals(nxt)) {
            this.next = null;
        } else {
            this.next = new ListNode(nxt);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ListNode listNode = (ListNode) o;
        return val == listNode.val &&
                Objects.equals(next, listNode.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, next);
    }

    @Override
    public String toString() {
        return val +"->" + (next == null ? "NULL" : next.toString());
    }
}

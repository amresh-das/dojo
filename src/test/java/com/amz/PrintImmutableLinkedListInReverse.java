package com.amz;

/**
 * @see "https://leetcode.com/problems/print-immutable-linked-list-in-reverse/"
 */
public class PrintImmutableLinkedListInReverse {

    public void printLinkedListInReverse(ImmutableListNode head) {
        if (head == null) return;
        printLinkedListInReverse(head.getNext());
        head.printValue();
    }

    interface ImmutableListNode {
        public void printValue();
        public ImmutableListNode getNext();
    };
}

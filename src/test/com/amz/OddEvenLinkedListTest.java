package com.amz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @see "https://leetcode.com/problems/odd-even-linked-list/description/?envType=study-plan-v2&envId=leetcode-75"
 */
public class OddEvenLinkedListTest {

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode oddPtr = head;
        ListNode evenHead = head == null ? null : head.next;
        ListNode evenPtr = evenHead;
        while (evenPtr != null) {
            ListNode nextOdd = oddPtr.next == null ? null : oddPtr.next.next;
            ListNode nextEven = evenPtr.next == null ? null : evenPtr.next.next;
            oddPtr.next = nextOdd;
            evenPtr.next = nextEven;
            if (nextOdd != null) {
                oddPtr = nextOdd;
            }
            evenPtr = nextEven;
        }
        if (oddPtr.next != null) {
            oddPtr = oddPtr.next;
        }
        oddPtr.next = evenHead;
        return head;
    }

    @Test
    void check1() {
        Assertions.assertEquals("[1,3,5,2,4]", oddEvenList(ListNode.create(1,2,3,4,5)).toString());
    }

    @Test
    void check2() {
        Assertions.assertEquals("[2,3,6,7,1,5,4]", oddEvenList(ListNode.create(2,1,3,5,6,4,7)).toString());
    }

    @Test
    void check3() {
        Assertions.assertEquals("[1,3,5,7,2,4,6,8]", oddEvenList(ListNode.create(1,2,3,4,5,6,7,8)).toString());
    }

    @Test
    void check4() {
        Assertions.assertEquals("[1,3,5,7,2,4,6,8]", oddEvenList(ListNode.create(1,2,3,4,5,6,7,8)).toString());
    }
}

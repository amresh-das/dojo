package com.amz;

import com.amz.leet.ListNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @see "https://leetcode.com/problems/palindrome-linked-list/"
 */
public class PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while(head != null) {
            list.add(head.val);
            head = head.next;
        }
        int l, r = list.size() / 2;
        if (list.size() % 2 == 0) {
            l = r - 1;
        } else {
            l = r;
        }
        for (; l >= 0 && r < list.size(); l--, r++) {
            if (list.get(l) != list.get(r)) return false;
        }
        return true;
    }

    @Test
    public void check1() {
        Assertions.assertTrue(isPalindrome(ListNode.create("1,2,2,1")));
    }

    @Test
    public void check2() {
        Assertions.assertFalse(isPalindrome(ListNode.create("1,2")));
    }
}

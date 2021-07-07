package com.dll.offer;

import javax.print.attribute.standard.NumberUp;

public class Offer24 {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            this.val = x;
        }
    }
    class Solution {
        public ListNode reverseList(ListNode head) {
            ListNode prev = null;
            ListNode current = head;
            ListNode next = null;

            while(current != null) {
                next = current.next;
                current.next = prev;
                prev = current;
                current = next;
            }
            return prev;
        }
    }
}

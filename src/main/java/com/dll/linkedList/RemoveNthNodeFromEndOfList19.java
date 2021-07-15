package com.dll.linkedList;

public class RemoveNthNodeFromEndOfList19 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode dummy = new ListNode(-1, head);
            ListNode p = head;
            ListNode preOfPren = null;
            ListNode pren = null;
            while(p != null && n > 0) {
                n--;
                p = p.next;
            }
            if (n > 0) {
                return null;
            }
            preOfPren = dummy;
            pren = head;
            while(p != null) {
                preOfPren = pren;
                pren = pren.next;
                p = p.next;
            }
            preOfPren.next = pren.next;
            return dummy.next;
        }
    }
}

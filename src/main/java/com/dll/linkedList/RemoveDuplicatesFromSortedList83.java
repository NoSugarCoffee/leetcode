package com.dll.linkedList;

public class RemoveDuplicatesFromSortedList83 {
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    class Solution {
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode pre = head;
            ListNode cur = head.next;
            while (cur != null) {
                if(cur.val == pre.val) {
                    cur = cur.next;
                    pre.next = cur;
                } else {
                    pre = cur;
                    cur = cur.next;
                }
            }
            return head;
        }
    }
}

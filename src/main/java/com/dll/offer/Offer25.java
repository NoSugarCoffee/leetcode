package com.dll.offer;

public class Offer25 {
  class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      this.val = x;
    }
  }

  class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
      ListNode p1 = l1;
      ListNode p2 = l2;
      ListNode dummy = new ListNode(-1);
      ListNode last = dummy;
      while (p1 != null && p2 != null) {
        if (p1.val > p2.val) {
          last.next = p2;
          p2 = p2.next;
        } else {
          last.next = p1;
          p1 = p1.next;
        }
        last = last.next;
      }
      if (p1 != null) {
        last.next = p1;
      }
      if (p2 != null) {
        last.next = p2;
      }
      return dummy.next;
    }
  }
}

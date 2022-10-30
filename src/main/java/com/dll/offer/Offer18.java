package com.dll.offer;

public class Offer18 {
  class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

  class Solution {
    public ListNode deleteNode(ListNode head, int val) {
      ListNode pre = null;
      ListNode cur = head;
      while (cur != null) {
        if (cur.val == val) {
          if (pre == null) {
            cur = cur.next;
            head = cur;
          } else {
            pre.next = cur.next;
            cur = cur.next;
          }
        } else {
          pre = cur;
          cur = cur.next;
        }
      }
      return head;
    }
  }
}

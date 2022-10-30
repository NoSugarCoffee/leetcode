package com.dll.linkedList;

public class AddTwoNumbers2 {
  class ListNode {
    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }

  class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
      ListNode head = new ListNode(-1);
      ListNode last = head;
      int pre = 0;
      while (l1 != null || l2 != null) {
        int l1Val = l1 == null ? 0 : l1.val;
        int l2Val = l2 == null ? 0 : l2.val;
        last.next = new ListNode((l1Val + l2Val + pre) % 10);
        pre = (l1Val + l2Val + pre) / 10;
        last = last.next;
        if (l1 != null) {
          l1 = l1.next;
        }
        if (l2 != null) {
          l2 = l2.next;
        }
      }
      // 注意此处的进位
      if (pre != 0) {
        last.next = new ListNode(pre);
        last = last.next;
      }
      last.next = null;
      return head.next;
    }
  }
}

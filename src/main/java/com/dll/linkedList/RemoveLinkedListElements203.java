package com.dll.linkedList;

public class RemoveLinkedListElements203 {
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
    public ListNode removeElements(ListNode head, int val) {
      ListNode dummy = new ListNode(-1, head);
      ListNode pre = dummy;
      ListNode p = head;
      while (p != null) {
        if (p.val == val) {
          pre.next = p.next;
        } else {
          pre = p;
        }
        p = p.next;
      }
      return dummy.next;
    }
  }
}

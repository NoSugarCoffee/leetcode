package com.dll.linkedList;

public class LinkedListCycleIIDoublePoint {
  class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
      next = null;
    }
  }

  public class Solution {

    public ListNode detectCycle(ListNode head) {
      ListNode slow, fast, find;
      try {
        find = head;
        slow = head.next;
        fast = slow.next;
      } catch (NullPointerException e) {
        return null;
      }
      while (fast != null) {
        if (fast == slow) {
          while (find != slow) {
            find = find.next;
            slow = slow.next;
          }
          return find;
        }
        fast = fast.next;
        slow = slow.next;
        if (fast != null) {
          fast = fast.next;
        } else {
          return null;
        }
      }
      return null;
    }
  }
}

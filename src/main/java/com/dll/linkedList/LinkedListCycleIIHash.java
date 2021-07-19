package com.dll.linkedList;

import java.util.HashSet;
import java.util.Set;

public class LinkedListCycleIIHash {
  class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
      val = x;
      next = null;
    }
  }

  public class Solution {
    private Set<ListNode> set = new HashSet<>();
    public ListNode detectCycle(ListNode head) {
      ListNode p = head;
      while (p != null) {
        if (set.contains(p)) {
          return p;
        }
        set.add(p);
        p = p.next;
      }
      return null;
    }
  }
}

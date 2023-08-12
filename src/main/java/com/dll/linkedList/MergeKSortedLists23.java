package com.dll.linkedList;

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

public class MergeKSortedLists23 {
  class Solution {
    ListNode mergeTwoLists(ListNode l1, ListNode l2) {
      ListNode head = new ListNode(-1, null);
      ListNode last = head;
      while (l1 != null && l2 != null) {
        last.next = new ListNode(Math.min(l1.val, l2.val), null);
        last = last.next;
        if (l1.val <= l2.val) {
          l1 = l1.next;
        } else {
          l2 = l2.next;
        }
      }
      if (l1 == null) {
        last.next = l2;
      } else {
        last.next = l1;
      }
      return removeHeadIfExist(head);
    }

    private ListNode removeHeadIfExist(ListNode head) {
      return (head.val == -1) ? head.next : head;
    }

    public ListNode mergeKLists(ListNode[] lists) {
      ListNode previousMerged = null;
      for (ListNode list : lists) {
        previousMerged = mergeTwoLists(previousMerged, list);
      }
      return previousMerged;
    }
  }
}

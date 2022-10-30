package com.dll.offer;

import java.util.ArrayList;
import java.util.List;

public class Offer06 {
  class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }
  }

  class Solution {
    public int[] reversePrint(ListNode head) {
      return this.recursion(head).stream().mapToInt(i -> i).toArray();
    }

    private List<Integer> recursion(ListNode node) {
      if (node == null) {
        return new ArrayList<>();
      }
      List<Integer> list = this.recursion(node.next);
      list.add(node.val);
      return list;
    }
  }
}

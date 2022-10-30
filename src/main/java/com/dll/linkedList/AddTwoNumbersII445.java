package com.dll.linkedList;

import java.util.ArrayDeque;

public class AddTwoNumbersII445 {
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
    private ArrayDeque<ListNode> pushToStack(ListNode l) {
      ArrayDeque<ListNode> stack = new ArrayDeque<>();
      while (l != null) {
        stack.push(l);
        l = l.next;
      }
      return stack;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
      ArrayDeque<ListNode> stack1 = pushToStack(l1);
      ArrayDeque<ListNode> stack2 = pushToStack(l2);
      ListNode head = new ListNode(-1);
      head.next = null;
      int carry = 0;
      while (!stack1.isEmpty() && !stack2.isEmpty()) {
        int s1 = stack1.pop().val;
        int s2 = stack2.pop().val;
        head.next = new ListNode((s1 + s2 + carry) % 10, head.next);
        carry = (s1 + s2 + carry) / 10;
      }
      ArrayDeque<ListNode> stack = stack1.isEmpty() ? stack2 : stack1;
      while (!stack.isEmpty()) {
        int val = stack.pop().val;
        head.next = new ListNode((val + carry) % 10, head.next);
        carry = (val + carry) / 10;
      }
      if (carry > 0) {
        head.next = new ListNode(carry, head.next);
      }
      return head.next;
    }
  }
}

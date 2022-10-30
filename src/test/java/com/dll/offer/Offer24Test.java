package com.dll.offer;

import org.junit.Test;

public class Offer24Test {
  @Test
  public void test() {
    Offer24.ListNode node = new Offer24().new ListNode(1);
    node.next = new Offer24().new ListNode(2);
    Offer24.ListNode head = new Offer24().new Solution().reverseList(node);
    assert head.val == 2;
    assert head.next.val == 1;
    assert head.next.next == null;
  }
}

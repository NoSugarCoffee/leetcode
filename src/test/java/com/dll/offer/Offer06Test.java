package com.dll.offer;

import com.dll.offer.Offer06.ListNode;
import org.junit.Assert;
import org.junit.Test;

public class Offer06Test {
  @Test
  public void test() {
    ListNode node = new Offer06().new ListNode(1);
    ListNode node2 = new Offer06().new ListNode(2);
    node.next = node2;

    Assert.assertArrayEquals(new int[]{2, 1},
        new Offer06().new Solution().reversePrint(node));

    Assert.assertArrayEquals(new int[]{},
        new Offer06().new Solution().reversePrint(null));
  }
}

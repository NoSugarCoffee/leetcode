package com.dll.linkedList;

import org.junit.Test;

public class RemoveDuplicatesFromSortedListII82Test {
  @Test
  public void test() {
    RemoveDuplicatesFromSortedListII82.ListNode node =
        new RemoveDuplicatesFromSortedListII82().new ListNode(1);
    RemoveDuplicatesFromSortedListII82.ListNode node1 =
        new RemoveDuplicatesFromSortedListII82().new ListNode(1);
    RemoveDuplicatesFromSortedListII82.ListNode node2 =
        new RemoveDuplicatesFromSortedListII82().new ListNode(3);
    node.next = node1;
    node1.next = node2;
    node2.next = null;
    assert new RemoveDuplicatesFromSortedListII82().new Solution().deleteDuplicates(node).val == 3;
  }
}

---
weight: 2
title: "链表"
---

# 链表

## 82. 删除排序链表中的重复元素 II
[leetcode](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/)
```java
// ../../../../../src/main/java/com/dll/linkedList/RemoveDuplicatesFromSortedListII82.java

package com.dll.linkedList;

public class RemoveDuplicatesFromSortedListII82 {
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    class Solution {
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode dummy = new ListNode();
            ListNode tail = dummy;
            ListNode cur = head;
            while(cur != null) {
                ListNode next = cur.next;
                if (next == null) {
                    tail.next = cur;
                    tail = tail.next;
                    cur = cur.next;
                } else if(cur.val != next.val) {
                    tail.next = cur;
                    tail = tail.next;
                    cur = cur.next;
                } else {
                    cur = findNextUnDuplicatedNodeWithHead(cur);
                }
            }
            tail.next = null;
            return dummy.next;
        }

        /**
         * find the node closest to the head with different value
         * example: 1->2->2 return 2
         * example: 2->2->3 return 3
         * example: 1->1->1 return null
         * example: null    return null
         * @param head of the list
         * @return the node closest to the head with different value or null
         */
        ListNode findNextUnDuplicatedNodeWithHead(ListNode head) {
            if (head == null || head.next == null) {
                return null;
            }
            if (head.val != head.next.val) {
                return head.next;
            }
            return findNextUnDuplicatedNodeWithHead(head.next);
        }
    }
}

```

## 83. 删除排序链表中的重复元素
[leetcode](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/)

```java
// ../../../../../src/main/java/com/dll/linkedList/RemoveDuplicatesFromSortedList83.java

package com.dll.linkedList;

public class RemoveDuplicatesFromSortedList83 {
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    class Solution {
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode pre = head;
            ListNode cur = head.next;
            while (cur != null) {
                if(cur.val == pre.val) {
                    cur = cur.next;
                    pre.next = cur;
                } else {
                    pre = cur;
                    cur = cur.next;
                }
            }
            return head;
        }
    }
}

```
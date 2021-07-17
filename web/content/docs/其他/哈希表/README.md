---
weight: 10
title: "哈希表"
---

# 哈希表
## 242. 有效的字母异位词
[leetcode](https://leetcode-cn.com/problems/valid-anagram/)
- 分别用 map 存储两个字符串的字符词频
- 比较两个 map 是否相等

```java
// ../../../../../src/main/java/com/dll/linkedList/AddTwoNumbers2.java

package com.dll.linkedList;

public class AddTwoNumbers2 {
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode head = new ListNode(-1);
            ListNode last = head;
            int pre = 0;
            while(l1 != null || l2 != null) {
                int l1Val = l1 == null? 0: l1.val;
                int l2Val = l2 == null? 0: l2.val;
                last.next = new ListNode((l1Val + l2Val + pre) % 10);
                pre = (l1Val + l2Val + pre) / 10;
                last = last.next;
                if (l1 != null) {
                    l1 = l1.next;
                }
                if (l2 != null) {
                    l2 = l2.next;
                }
            }
            // 注意此处的进位
            if (pre != 0) {
                last.next = new ListNode(pre);
                last = last.next;
            }
            last.next = null;
            return head.next;
        }
    }
}

```


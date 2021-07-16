---
weight: 2
title: "链表"
---

# 链表
## 2. 两数相加
[leetcode](https://leetcode-cn.com/problems/add-two-numbers/)
- 各个位置上的结点相加并需要考虑进位
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


## 19. 删除链表的倒数第 N 个结点
[leetcode](https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/)
- 使用双指针，cur 指向当前结点，pren 指向 cur 的第前 n 个结点，当 cur 走到 null 时，pren 即为需要删除的结点

```java
// ../../../../../src/main/java/com/dll/linkedList/RemoveNthNodeFromEndOfList19.java

package com.dll.linkedList;

public class RemoveNthNodeFromEndOfList19 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode dummy = new ListNode(-1, head);
            ListNode p = head;
            ListNode preOfPren = null;
            ListNode pren = null;
            while(p != null && n > 0) {
                n--;
                p = p.next;
            }
            if (n > 0) {
                return null;
            }
            preOfPren = dummy;
            pren = head;
            while(p != null) {
                preOfPren = pren;
                pren = pren.next;
                p = p.next;
            }
            preOfPren.next = pren.next;
            return dummy.next;
        }
    }
}

```

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

## 203. 移除链表元素
[leetcode](https://leetcode-cn.com/problems/remove-linked-list-elements/)

- 增加一个 dummy 结点使删除头结点和中间结点的逻辑一致
- p 总是指向当前需要做逻辑判断的结点， pre 则为 p 的前一个结点
- p 指向的结点 val 与给定一致时，删除 p 结点，即 pre.next = p.next
- pre 与 p 的维护：当结点删除，下一轮的 pre 不需要变动，p = p.next，否则一直保持 pre 和 p 的前进

```java
// ../../../../../src/main/java/com/dll/linkedList/RemoveLinkedListElements203.java

package com.dll.linkedList;

public class RemoveLinkedListElements203 {
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    class Solution {
        public ListNode removeElements(ListNode head, int val) {
            ListNode dummy = new ListNode(-1, head);
            ListNode pre = dummy;
            ListNode p = head;
            while(p != null) {
                if(p.val == val) {
                    pre.next = p.next;
                } else {
                    pre = p;
                }
                p = p.next;
            }
            return dummy.next;
        }
    }
}

```
## 206. 反转链表

## 445. 两数相加 II
[leetcode](https://leetcode-cn.com/problems/add-two-numbers-ii/)
```java
// ../../../../../src/main/java/com/dll/linkedList/AddTwoNumbersII445.java

package com.dll.linkedList;

import java.util.ArrayDeque;

public class AddTwoNumbersII445 {
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    class Solution {
        private ArrayDeque<ListNode> pushToStack(ListNode l) {
            ArrayDeque<ListNode> stack = new ArrayDeque<>();
            while(l != null) {
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
            while(!stack1.isEmpty() && !stack2.isEmpty()) {
                int s1 = stack1.pop().val;
                int s2 = stack2.pop().val;
                head.next = new ListNode((s1 + s2 + carry) % 10, head.next);
                carry =  (s1 + s2 + carry) / 10;
            }
            ArrayDeque<ListNode> stack = stack1.isEmpty()? stack2: stack1;
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

```


## 707. 设计链表
- 维护 dummy 为链表的初始结点，维护 tail 指针指向链表末尾非 null 结点（初始化时 tail = dummy），维护 len 代表当前链表的长度（不计 dummy）
- 何时更新 tail 指针？
    - addAtHead(val) 或 addAtIndex(-1, val) 且插入的结点为头结点，更新 tail 为当前插入的结点
    - deleteAtIndex(index) index 为链表的尾结点，更新 tail 为删除结点的 previous 结点

```java
// ../../../../../src/main/java/com/dll/linkedList/DesignLinkedList707.java

package com.dll.linkedList;

public class DesignLinkedList707 {
    class MyLinkedList {
        private Node dummy;
        // tail refs to last one of the list
        private Node tail;
        private int len;
        class Node {
            private int val;
            private Node next;

            public Node(int val) {
                this(val, null);
            }
            public Node(int val, Node next) {
                this.val = val;
                this.next = next;
            }
        }

        /** Initialize your data structure here. */
        public MyLinkedList() {
            dummy = new Node(-1);
            tail = dummy;
        }

        /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
        public int get(int index) {
            Node p = dummy.next;
            if (index < 0 || index >= len) {
                return -1;
            }
            while(index > 0) {
                p = p.next;
                index--;
            }
            return p.val;
        }

        /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
        public void addAtHead(int val) {
            Node pre = dummy;
            Node p = dummy.next;
            pre.next = new Node(val, p);
            len++;
            if(p == null) {
                tail = pre.next;
            }
        }

        /** Append a node of value val to the last element of the linked list. */
        public void addAtTail(int val) {
            Node node = new Node(val);
            tail.next = node;
            tail = node;
            len++;
        }

        /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
        public void addAtIndex(int index, int val) {
            Node pre = dummy;
            Node p = dummy.next;

            if(index < 0) {
                this.addAtHead(val);
                return;
            }
            if (index > len) {
                return;
            }
            if (index == len) {
                this.addAtTail(val);
                return;
            }

            while(index > 0) {
                index --;
                pre = p;
                p = p.next;
            }
            pre.next = new Node(val, p);
            len++;
        }

        /** Delete the index-th node in the linked list, if the index is valid. */
        public void deleteAtIndex(int index) {
            Node p = dummy.next;
            Node pre = dummy;
            if (index < 0 || index >= len) {
                return;
            }
            while(index > 0) {
                index--;
                pre = p;
                p = p.next;
            }

            pre.next = p.next;
            len--;
            if (p.next == null) {
                tail = pre;
            }
        }

        @Override
        public String toString() {
            Node p = this.dummy.next;
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            while( p != null) {
                sb.append(p.val + "->");
                p = p.next;
            }
            if (tail == null) {
                sb.append("null] tail: null");
            } else {
                sb.append("null] tail:" + tail.val);
            }
            return sb.toString();
        }
    }
}

```    
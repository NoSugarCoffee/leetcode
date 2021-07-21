---
weight: 1
title: "剑指 offer"
---

## 04. 二维数组中的查找

[leetcode](https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/)

```java
// ../../../../src/main/java/com/dll/offer/Offer04.java

package com.dll.offer;

public class Offer04 {
  class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
      int rows;
      if (matrix == null || (rows = matrix.length) < 1) {
        return false;
      }
      int cols = matrix[0].length;
      int i = 0;
      int j = cols - 1;

      while (i < rows  && j > -1) {
        int upperRightCorner = matrix[i][j];
        if (upperRightCorner == target) {
          return true;
        } else if (upperRightCorner > target) {
          j--;
        } else {
          i++;
        }
      }
      return false;
    }
  }
}

```


## 05. 替换空格
[leetcode](https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/)
```java
// ../../../../src/main/java/com/dll/offer/Offer05.java

package com.dll.offer;

import java.util.stream.Stream;

public class Offer05 {
  class Solution {
    public String replaceSpace(String s) {
      if (s == null) {
        return "";
      }
      StringBuilder sb = new StringBuilder();
      Stream.of(s.split("")).forEach(ss -> {
        if (" ".equals(ss)) {
          sb.append("%20");
        } else {
          sb.append(ss);
        }
      });
      return sb.toString();
    }
  }
}

```


## 06. 从尾到头打印链表
[leetcode](https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/)

### 递归法
```java
// ../../../../src/main/java/com/dll/offer/Offer06.java

package com.dll.offer;


import java.util.ArrayList;
import java.util.List;

public class Offer06 {
  class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
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


```

## 07. 重建二叉树

[leetcode](https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/)

根据前序和中序遍历的规则，前序遍历总是将树分割成 `{根 [左子树] [右子树]}`，中序遍历则将树分割成 `{[左子树] 根 [右子树]}`，两者左右子树的成员应该保持一致。

具体的，当还原前序遍历为 `{1,2,4,7,3,5,6,8}`，中序遍历为 `{4,7,2,1,5,3,8,6}` 的树时，需要经历以下步骤:

- 根据前序遍历 `{1,2,4,7,3,5,6,8}` 可得 `1` 是树的根结点

- 结合中序遍历可知左子树中序结果为 `{4,7,2}`, 前序结果为 `{2,4,7}`, 右子树中序结果为 `{5,3,8,6}`, 前序结果为 `{3,5,6,8}` 

- 对子树重复上述步骤
 

伪代码表示一个流程如下：

```
preorder = [1,2,4,7,3,5,6,8]
inorder = [4,7,2,1,5,3,8,6]

root = preorder[0]

# 拆分成两颗子树
subLeftTreeInorder, subRightTreeInorder = splitInorder(root)

subLeftTreeInorderlength = subLeftTreeInorder.length()
subRightTreeInorderlength = subRightTreeInorder.length()

subLeftTreePreorder = preorder[1:subLeftTreeInorderlength]
subRightTreePreorder = preorder[subLeftTreeInorderlength:subRightTreeInorderlength]

subLeftTreeRoot = subLeftTreePreorder[0]
subRightTreeRoot = subRightTreePreorder[0]

root.left = subLeftTreeRoot
root.right = subRightTreeRoot
```

经过上述直叙的方式，可以抽象成如下递归体：

```
func Node rec(preorder, inorder) {
  root = preorder[0]
  subLeftTreeInorder, subRightTreeInorder = splitInorder(preorder[0])
  
  subLeftTreeInorderlength = subLeftTreeInorder.length()
  subRightTreeInorderlength = subRightTreeInorder.length()
  
  subLeftTreePreorder = preorder[1:subLeftTreeInorderlength]
  subRightTreePreorder = preorder[subLeftTreeInorderlength:subRightTreeInorderlength]
  
  left = rec(subLeftTreePreorder, subLeftTreeInorder)
  right = rec(subRightTreePreorder, subRightTreeInorder)
  
  root.left = left
  root.right = right
  return root
}
  
```
   
```java
// ../../../../src/main/java/com/dll/offer/Offer07.java

package com.dll.offer;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Offer07 {
  class LevelTreeNode {
    TreeNode node;
    int level;
    public LevelTreeNode(TreeNode node, int level) {
      this.node = node;
      this.level = level;
    }
  }

  class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) {
      this.val = val;
    }
  }

  class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
      if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0) {
        return null;
      }
      if (preorder.length != inorder.length) {
        throw new RuntimeException(
            String.format("internal error, preorder:%s inorder:%s",
                Arrays.toString(preorder), Arrays.toString(inorder)));
      }
      int rootValue = preorder[0];
      int[][] subTreeInorder = this.splitInorder(inorder, rootValue);

      int[] leftInorder = subTreeInorder[0];
      int[] rightInorder = subTreeInorder[1];

      int leftTreeLength = leftInorder.length;
      int rightTreeLength = rightInorder.length;

      int[] leftPreorder = new int[]{};
      int[] rightPreorder = new int[]{};

      if (leftTreeLength > 0) {
        leftPreorder = Arrays.copyOfRange(preorder, 1, 1 + leftTreeLength);

      }
      if (rightTreeLength > 0) {
        rightPreorder = Arrays.copyOfRange(preorder,  1 + leftTreeLength, preorder.length);
      }

      TreeNode left = buildTree(leftPreorder, leftInorder);
      TreeNode right = buildTree(rightPreorder, rightInorder);

      TreeNode root = new TreeNode(rootValue);
      root.left = left;
      root.right = right;
      return root;
    }

    private int findIndexInArray(int[] array, int root) {
      int index = 0;
      for (int value: array) {
        if (value == root) {
          return index;
        }
        index++;
      }
      return -1;
    }

    int[][] splitInorder(int[] inorder, int root) {
      int rootIndex = this.findIndexInArray(inorder, root);
      if (rootIndex == -1) {
        return new int[][]{{},{}};
      }
      int[] left = Arrays.copyOfRange(inorder, 0, rootIndex);
      int[] right = Arrays.copyOfRange(inorder, rootIndex + 1, inorder.length);
      return new int[][]{left, right};
    }

    // this also can solve problem https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
    public List<List<Integer>> levelOrder(TreeNode root) {
      // map key is level, map value is the list of node value
      if (root == null) {
        return new ArrayList<>(new ArrayList<>());
      }
      Map<Integer, List<Integer>> map =
          new TreeMap<>(Comparator.comparingInt(Integer::valueOf));

      Deque<LevelTreeNode> queue = new ArrayDeque<>();
      queue.offer(new LevelTreeNode(root, 0));

      while (!queue.isEmpty()) {
        LevelTreeNode n = queue.poll();
        List<Integer> list = map.getOrDefault(n.level, new ArrayList<>());
        map.put(n.level, list);
        list.add(n.node.val);

        if (n.node.left != null) {
          queue.offer(new LevelTreeNode(n.node.left, n.level + 1));
        }
        if (n.node.right != null) {
          queue.offer(new LevelTreeNode(n.node.right, n.level + 1));
        }
      }
      return new ArrayList<>(map.values());
    }
  }
}

```

## 09. 用两个栈实现队列
[leetcode](https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/)

利用两个栈, deQueueStack 以及 enQueueStack

- 入队只从 enQueueStack 进
- 出队只从 deQueueStack 出，deQueueStack 内有元素时直接出队, 无元素时转移全部 enQueueStack 到 deQueueStack

```java
// ../../../../src/main/java/com/dll/offer/Offer09.java

package com.dll.offer;

import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Offer09 {
  class CQueue {
    Deque<Integer> inQueueStack = new LinkedList<>();
    Deque<Integer> deQueueStack = new LinkedList<>();

    public CQueue() {
    }

    public void appendTail(int value) {
      inQueueStack.push(value);
    }

    private int popWrapper() {
      try {
        return deQueueStack.pop();
      } catch (NoSuchElementException e) {
        return -1;
      }
    }

    public int deleteHead() {
      if (deQueueStack.isEmpty()) {
        while (!inQueueStack.isEmpty()) {
          deQueueStack.push(inQueueStack.pop());
        }
      }
      return popWrapper();
    }
  }
}

```

## 10- I. 斐波那契数列
[leetcode](https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/)

直接递归会超时，所以加了个简易的 cache
```java
// ../../../../src/main/java/com/dll/offer/Offer10I.java


package com.dll.offer;

import java.util.HashMap;
import java.util.Map;

public class Offer10I {
  class Solution {
    Map<Integer, Integer> cache = new HashMap<>();

    public int fib(int n) {
      if (cache.containsKey(n)) {
        return cache.get(n);
      }
      if (n < 2) {
        return n;
      }
      int val = (fib(n - 1) + fib(n - 2)) % 1000000007;
      cache.put(n, val);
      return val;
    }

  }
}

```

## 10- II. 青蛙跳台阶问题
[leetcode](https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/)

斐波那契数列包了一层背景，原理一样

```java
// ../../../../src/main/java/com/dll/offer/Offer10II.java


package com.dll.offer;

import java.util.HashMap;
import java.util.Map;

public class Offer10II {
  class Solution {

    Map<Integer, Integer> cache = new HashMap<>();

    private int recu(int n) {
      if (cache.containsKey(n)) {
        return cache.get(n);
      }
      if (n == 0 || n == 1) {
        return 1;
      }
      int val = (recu(n - 1) + recu(n - 2)) % 1000000007;
      cache.put(n, val);
      return val;
    }

    public int numWays(int n) {
      return recu(n);
    }
  }
}

```

## 11. 旋转数组的最小数字
这题直接用暴力法解了，因为个人觉得题目没什么太大实际意义
[leetcode](https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/)

```java
// ../../../../src/main/java/com/dll/offer/Offer11.java


package com.dll.offer;

public class Offer11 {
  class Solution {
    public int minArray(int[] numbers) {
      if (numbers == null || numbers.length < 1) {
        throw new RuntimeException();
      }
      int min = numbers[0];
      for (int i = 1; i < numbers.length; i++) {
        if (numbers[i] < numbers[i - 1]) {
          min = numbers[i];
          break;
        }
      }
      return min;
    }
  }
}

```

## 13. 机器人的运动范围
[leetcode](https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof)

这题有坑，开始以为按照如下代码可解，提交后一直不能 ac

```java
class Solution {
    int calc(int num) {
        int sum = 0;
        while(num != 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
    public int movingCount(int m, int n, int k) {
        int counter = 0;
        for(int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if ((calc(i) + calc(j)) <= k ) {
                    counter++;
                }
            }
        }
        return counter;
    }
}
```

翻看评论才知道，有如下二维数组，当 k = 8 时，机器人无法从第九行或者第九列跨过去
```
  0  1  2  3  4  5  6 7  8  9 10
0 可 可 可 可 可 可 可 可 可 不 可
1 可 可 可 可 可 可 可 可 不 不 可
2 可 可 可 可 可 可 可 不 不 不 可
3 可 可 可 可 可 可 不 不 不 不 可
4 可 可 可 可 可 不 不 不 不 不 可
5 可 可 可 可 不 不 不 不 不 不 可
6 可 可 可 不 不 不 不 不 不 不 可                      
7 可 可 不 不 不 不 不 不 不 不 可  
8 可 不 不 不 不 不 不 不 不 不 不  
9 不 不 不 不 不 不 不 不 不 不 不  
10可 可 可 可 可 可 可 可 可 可 不

（可为可到达的，不为不可到达的）
```

代码：
```java
// ../../../../src/main/java/com/dll/offer/Offer13.java

package com.dll.offer;

public class Offer13 {
    class Solution {
        int mLen;
        int nLen;
        int k;
        int[][] visited;

        private int calc(int num) {
            int sum = 0;
            while(num != 0) {
                sum += num % 10;
                num /= 10;
            }
            return sum;
        }

        public int dfs(int i, int j) {
            if ( i >= mLen || j >= nLen) {
                return 0;
            }
            if (visited[i][j] == 1) {
                return 0;
            }
            visited[i][j] = 1;
            if (calc(i) + calc(j) > this.k) {
                return 0;
            }
            return  1 + this.dfs(i + 1, j) + this.dfs(i, j + 1);
        }

        private void init(int m, int n, int k) {
            this.mLen = m;
            this.nLen = n;
            this.k = k;
            this.visited = new int[m][n];
        }

        public int movingCount(int m, int n, int k) {
            this.init(m, n, k);
            return this.dfs(0, 0);
        }
    }
}

```


## 18. 删除链表的节点
[leetcode](https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/)

```java
// ../../../../src/main/java/com/dll/offer/Offer18.java

package com.dll.offer;

public class Offer18 {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    class Solution {
        public ListNode deleteNode(ListNode head, int val) {
            ListNode pre = null;
            ListNode cur = head;
            while (cur != null) {
                if (cur.val == val) {
                    if (pre == null) {
                        cur = cur.next;
                        head = cur;
                    } else {
                        pre.next = cur.next;
                        cur = cur.next;
                    }
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

## 24. 反转链表
[leetcode](https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/)

```java
// ../../../../src/main/java/com/dll/offer/Offer24.java

package com.dll.offer;

public class Offer24 {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            this.val = x;
        }
    }
    class Solution {
        public ListNode reverseList(ListNode head) {
            ListNode prev = null;
            ListNode current = head;
            ListNode next = null;

            while(current != null) {
                next = current.next;
                current.next = prev;
                prev = current;
                current = next;
            }
            return prev;
        }
    }
}

```

## 25. 合并两个排序的链表
[leetcode](https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/)

```java
// ../../../../src/main/java/com/dll/offer/Offer25.java

package com.dll.offer;

public class Offer25 {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            this.val = x;
        }
    }
    class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode p1 = l1;
            ListNode p2 = l2;
            ListNode dummy = new ListNode(-1);
            ListNode last = dummy;
            while(p1 != null && p2 != null) {
                if ( p1.val > p2.val) {
                    last.next = p2;
                    p2 = p2.next;
                } else {
                    last.next = p1;
                    p1 = p1.next;
                }
                last = last.next;
            }
            if (p1 != null) {
                last.next = p1;
            }
            if (p2 != null) {
                last.next = p2;
            }
            return dummy.next;
        }
    }
}

```

## 52. 两个链表的第一个公共节点
[leetcode](https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/submissions/)

- 要比较的是 node 是否相等，而不是 value 是否相等。
- 由于是单向链表，即重合后一定是 “Y” 型，而不是 “X” 型，尾部长度一致。
- 使链表 A 和链表 B 末尾对齐，同时遍历，出现相同结点则直接返回，直到遍历至末尾都没有相同结点则返回 null。

**时空复杂度**
- 时间复杂度: O(len(A) + len(B)))
- 空间复杂度：O(1)

```java
// ../../../../src/main/java/com/dll/offer/Offer52.java

package com.dll.offer;

public class Offer52 {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            this.val = x;
        }
    }
    class Solution {
        public ListNode getIntersectionNOde(ListNode headA, ListNode headB) {
                ListNode pa = headA;
                ListNode pb = headB;
                int lenA = 0;
                int lenB = 0;
                while(pa != null) {
                    lenA++;
                    pa = pa.next;
                }
                while(pb != null) {
                    lenB++;
                    pb = pb.next;
                }
                int distance = Math.abs(lenA - lenB);
                ListNode pLong = headA;
                ListNode pShort = headB;
                if (lenB > lenA) {
                    pLong = headB;
                    pShort = headA;
                }
                while(distance-- > 0) {
                    pLong = pLong.next;
                }
                while(pLong != null) {
                    if (pLong == pShort) {
                        return pLong;
                    }
                    pLong = pLong.next;
                    pShort = pShort.next;
                }
                return null;
        }
    }
}

```
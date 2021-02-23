# 双指针

待补充

目录
=================
* [<a href="https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/" rel="nofollow">167. Two Sum II - Input array is sorted</a>](#two-sum-ii---input-array-is-sorted)
* [<a href="https://leetcode.com/problems/merge-sorted-array/" rel="nofollow">88. Merge Sorted Array</a>](#88-merge-sorted-array)
* [<a href="https://leetcode-cn.com/problems/linked-list-cycle-ii/" rel="nofollow">142. linked-list-cycle-ii</a>](#142-linked-list-cycle-ii)
   * [哈希法](#哈希法)
   * [快慢指针](#快慢指针)



## [167. Two Sum II - Input array is sorted](https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/) 

由于数组升序排列, 分别使用指针 ps 指向数组开头, 指针 pe 指向数组末尾, 当 array[ps] + array[pe] > target, 则 pe 向前移动, 否则 ps 向后移动, 直到 = target 

## [88. Merge Sorted Array](https://leetcode.com/problems/merge-sorted-array/)

开辟足够大的 nums3, p1、p2 分别指向两个有序数组初始位置, 储存当前指针指向的较小值至 nums3 且移动指针, 相等则任选

## [142. linked-list-cycle-ii](https://leetcode-cn.com/problems/linked-list-cycle-ii/)

### 哈希法

该思路比较直接也比较简单, 后续在补充

### 快慢指针
该思路比较有趣, 具体如下:

若存在环时, 遍历永不结束。慢指针每次走 1 步, 快指针每次走 2 步, 成环时快指针总是能追上慢指针
那么如何知道入口点, 由已知关系得:

![](https://assets.leetcode-cn.com/solution-static/142/142_fig1.png)

```
快指针路径长 = 2 倍慢指针的路径 = n 圈路径长 + a + b

2(a+b) = a+b+(b+c)*n 
   a+b = (b+c)n
     a = (b+c)n-b
     a = (b+c)(n-1)+c
   
从相遇点到入环点的距离加上 n-1 圈的环长，恰好等于从链表头部到入环点的距离
```
在表头新建指针与 slow 同步移动, 相交处即为入口点

---
weight: 3
title: "二分查找"
---

# 二分查找

## [Sqrt(x)](https://leetcode.com/problems/sqrtx/)

查找满足 a*a <= x 的最大 a 值，对 [1,x] 区间二分法取满足的即可

```java
// ../../../../src/binarySearch/Mysqrt.java

package binarySearch;

public class MySqrt {

  class Solution {

    public int mySqrt(int x) {
      int l = 1;
      int r = x;
      int result = 0;
      while (l <= r) {
        //NOTE: 不考虑 long 一直超出事件限制
        int mid = l + (r - l) / 2;
        if ((long) mid * mid <= x) {
          result = mid;
          l = mid + 1;
        } else {
          r = mid - 1;
        }
      }
      return result;
    }
  }
}

```


## [Find-first-and-last-position-of-element-in-sorted-array](https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/)

使用二分法找出左右边界

```java
// ../../../../src/binarySearch/FindFirstAndLastPositionOfElementInSortedArray.java

package binarySearch;

public class FindFirstAndLastPositionOfElementInSortedArray {

  class Solution {

    public int[] searchRange(int[] nums, int target) {
      int l = 0;
      int r = nums.length - 1;
      int res1 = -1;
      while (l <= r) {
        int mid = l + (r - l) / 2;
        if (nums[mid] < target) {
          l = mid + 1;
        } else {
          r = mid - 1;
          if (nums[mid] == target) {
            res1 = mid;
          }
        }
      }
      l = 0;
      r = nums.length - 1;
      int res2 = -1;
      while (l <= r) {
        int mid = l + (r - l) / 2;
        if (nums[mid] > target) {
          r = mid - 1;
        } else {
          l = mid + 1;
          if (nums[mid] == target) {
            res2 = mid;
          }
        }
      }
      return new int[]{res1, res2};
    }
  }
}

```

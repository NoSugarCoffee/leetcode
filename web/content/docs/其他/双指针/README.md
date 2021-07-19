---
weight: 2
title: "双指针"
---
# 双指针

## 167. 两数之和 II - 输入有序数组
[leetcode](https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/) 

由于数组升序, 分别使用指针 ps 指向数组开头, 指针 pe 指向数组末尾, 当 array[ps] + array[pe] > target, 则 pe 向前移动, 否则 ps 向后移动, 直到 = target

```java
// ../../../../../src/main/java/com/dll/doublePoint/TwoSumIIInputArrayIsSorted.java

package com.dll.doublePoint;

public class TwoSumIIInputArrayIsSorted {

  class Solution {

    public int[] twoSum(int[] numbers, int target) {
      int ps = 0;
      int pe = numbers.length - 1;
      while (ps < pe) {
        if (numbers[ps] + numbers[pe] == target) {
          return new int[]{ps + 1, pe + 1};
        } else if (numbers[ps] + numbers[pe] > target) {
          pe--;
        } else {
          ps++;
        }
      }
      return new int[]{-1, -1};
    }
  }
}

``` 

## 88. 合并两个有序数组
[leetcode](https://leetcode.com/problems/merge-sorted-array/)

开辟足够大的 nums3, p1、p2 分别指向两个有序数组初始位置, 存储当前指针指向的较小值至 nums3 且移动指针, 相等则任选

```java
// ../../../../../src/main/java/com/dll/doublePoint/MergeSortedArray.java

package com.dll.doublePoint;

public class MergeSortedArray {

  class Solution {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
      int[] nums3 = new int[m + n];
      int p1 = 0;
      int p2 = 0;
      int index = 0;

      while (p1 < m && p2 < n) {
        if (nums1[p1] <= nums2[p2]) {
          nums3[index] = nums1[p1];
          p1++;
        } else {
          nums3[index] = nums2[p2];
          p2++;
        }
        index++;
      }
      for (; p1 < m; p1++) {
        nums3[index++] = nums1[p1];
      }
      for (; p2 < n; p2++) {
        nums3[index++] = nums2[p2];
      }
      System.arraycopy(nums3, 0, nums1, 0, m + n);
    }
  }
}

``` 


## 76. 最小覆盖子串

[leetcode](https://leetcode-cn.com/problems/minimum-window-substring/)
 
### 滑动窗口

原字符 ori（s），以及需要包含的字符串 need（t），[l,r) 代表当前窗口，r 右滑直至满足条件，此时通过 l 右滑，可能找到最优解，重复直到结束。
    
```java
// ../../../../../src/main/java/com/dll/doublePoint/MinimumWindowSubstring.java

package com.dll.doublePoint;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class MinimumWindowSubstring {

  class Solution {

    public Map<Character, Integer> calc_freq(String s) {
      Map<Character, Integer> freq = new HashMap<>();
      for (int i = 0; i < s.length(); i++) {
        freq.put(s.charAt(i), freq.getOrDefault(s.charAt(i), 0) + 1);
      }
      return freq;
    }

    public boolean check(Map<Character, Integer> window_freq, Map<Character, Integer> need_freq) {
      Iterator<Entry<Character, Integer>> iterator = need_freq.entrySet().iterator();
      while (iterator.hasNext()) {
        Entry<Character, Integer> next = iterator.next();
        Character key = next.getKey();
        if (!(window_freq.containsKey(key) && window_freq.get(key) >= next.getValue())) {
          return false;
        }
      }
      return true;
    }

    public String minWindow(String s, String t) {
      String ori = s;
      String need = t;
      int l = 0;
      int r = 0;
      String result = "";
      Map<Character, Integer> needFreq = calc_freq(need);
      Map<Character, Integer> window_frep = new HashMap<>();
      int endIndex = ori.length() - 1;
      while (r <= endIndex) {
        window_frep.put(ori.charAt(r), window_frep.getOrDefault(ori.charAt(r), 0) + 1);
        while (check(window_frep, needFreq) && l <= r) {
          if ("".equals(result)) {
            result = ori.substring(l, r + 1);
          } else {
            result = ori.substring(l, r + 1).length() < result.length() ? ori.substring(l, r + 1)
                : result;
          }
          window_frep.put(ori.charAt(l), window_frep.getOrDefault(ori.charAt(l), 0) - 1);
          l++;
        }
        r++;
      }
      return result;
    }
  }
}

```
---
weight: 1
title: "数组"
---
## 矩阵中战斗力最弱的 K 行
[1337. the-k-weakest-rows-in-a-matrix](https://leetcode-cn.com/problems/the-k-weakest-rows-in-a-matrix/)

```java
// ../../../../src/main/java/com/dll/array/TheKWeakestRowsInAMatrix.java

package com.dll.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class TheKWeakestRowsInAMatrix {
  class Solution {
    public int[] kWeakestRows(int[][] mat, int k) {
      Map<Integer, Integer> map = new HashMap<>();
      IntStream.range(0, mat.length)
          .forEach(index -> map.put(index, Arrays.stream(mat[index]).sum()));
      return map.entrySet().stream()
          .sorted(
              Comparator.comparing(Map.Entry<Integer, Integer>::getValue)
                  .thenComparing(Map.Entry::getKey))
          .limit(k)
          .mapToInt(Map.Entry::getKey)
          .toArray();
    }
  }
}

```

## 二分查找
[704. binary-search](https://leetcode-cn.com/problems/binary-search/)

```java
// ../../../../src/main/java/com/dll/array/BinarySearch.java

package com.dll.array;

public class BinarySearch {
  class Solution {
    public int search(int[] nums, int target) {
      int leftIndex = 0;
      int rightIndex = nums.length - 1;
      while (leftIndex <= rightIndex) {
        int midIndex = (leftIndex + rightIndex) / 2;
        if (target == nums[midIndex]) {
          return midIndex;
        } else if (nums[midIndex] > target) {
          rightIndex = midIndex - 1;
        } else {
          leftIndex = midIndex + 1;
        }
      }
      return -1;
    }
  }
}

```
## 第一个错误的版本
[278. first-bad-version](https://leetcode-cn.com/problems/first-bad-version/)

left，right 分别为 1 和 versions.length，mid 为 （left + right） / 2
当 left <= right 时循环并做如下处理: 
1. versions[mid] is bad version，则 right = mid - 1，并更新 earliestErrVersion = versions[mid]
2. versions[mid] is not bad version，则 left = mid + 1

```java
// ../../../../src/main/java/com/dll/array/FirstBadVersion.java

package com.dll.array;

import java.util.function.Predicate;

public class FirstBadVersion {
  class VersionControl {
    boolean isBadVersion(int val) {
      return false;
    }
  }

  class Solution extends VersionControl {
    public int searchEarlisetErrVersion(int left, int right, Predicate<Integer> isBadFunc) {
      int earlisetErrVersion = -1;
      while (left <= right) {
        int mid = left + (right - left) / 2;
        if (isBadFunc.test(mid)) {
          earlisetErrVersion = mid;
          right = mid - 1;
        } else {
          left = mid + 1;
        }
      }
      return earlisetErrVersion;
    }

    public int firstBadVersion(int n) {
      return this.searchEarlisetErrVersion(1, n, this::isBadVersion);
    }
  }
}

```

## x 的平方根 
[69. sqrtx](https://leetcode-cn.com/problems/sqrtx/)

```java
// ../../../../src/main/java/com/dll/array/MySqrt.java

package com.dll.array;

public class MySqrt {
  class Solution {
    public int mySqrt(int x) {
      int l = 1;
      int r = x;
      int result = 0;
      while (l <= r) {
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

## 在排序数组中查找元素的第一个和最后一个位置 
[34. find-first-and-last-position-of-element-in-sorted-array](https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/)

```java
// ../../../../src/main/java/com/dll/array/FindFirstAndLastPositionOfElementInSortedArray.java

package com.dll.array;

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
      return new int[] {res1, res2};
    }
  }
}

```

## 搜索插入位置
[35. search-insert-position](https://leetcode-cn.com/problems/search-insert-position/)

```java
// ../../../../src/main/java/com/dll/array/SearchInsertPosition.java

package com.dll.array;

public class SearchInsertPosition {
  class Solution {
    private int binarySearch(int[] array, int target) {
      int left = 0;
      int right = array.length - 1;
      int mid = 0;
      while (left <= right) {
        mid = left + (right - left) / 2;
        if (array[mid] < target) {
          left = mid + 1;
        } else if (array[mid] > target) {
          right = mid - 1;
        } else {
          break;
        }
      }
      return array[mid] >= target ? mid : mid + 1;
    }

    public int searchInsert(int[] nums, int target) {
      return binarySearch(nums, target);
    }
  }
}

```

## 两数之和  
[1. two-sum](https://leetcode-cn.com/problems/two-sum/) 

```java
// ../../../../src/main/java/com/dll/array/TwoSum.java

package com.dll.array;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
  // 1. 暴力法
  class Solution {
    public int[] twoSum(int[] nums, int target) {
      for (int i = 0; i < nums.length; i++) {
        for (int j = i + 1; j < nums.length; j++) {
          if (nums[i] + nums[j] == target) {
            return new int[] {i, j};
          }
        }
      }
      return new int[] {-1, -1};
    }
  }

  // 2. hash
  class Solution2 {
    public int[] twoSum(int[] nums, int target) {
      Map<Integer, Integer> map = new HashMap<>();
      for (int i = 0; i < nums.length; i++) {
        if (map.containsKey(target - nums[i])) {
          return new int[] {map.get(target - nums[i]), i};
        }
        map.put(nums[i], i);
      }
      return new int[] {-1, -1};
    }
  }
}

```

## 两数之和 II - 输入有序数组 
[167. two-sum-ii-input-array-is-sorted](https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/) 

- 使用指针 ps 指向数组开头, 指针 pe 指向数组末尾
- 当 numbers[ps] + numbers[pe] > target, 则 pe 向前移动, 否则 ps 向后移动, 直到 = target

```java
// ../../../../src/main/java/com/dll/array/TwoSumIIInputArrayIsSorted.java

package com.dll.array;

public class TwoSumIIInputArrayIsSorted {

  class Solution {
    // 1. 双指针
    public int[] twoSum(int[] numbers, int target) {
      int ps = 0;
      int pe = numbers.length - 1;
      while (ps < pe) {
        if (numbers[ps] + numbers[pe] == target) {
          return new int[] {ps + 1, pe + 1};
        } else if (numbers[ps] + numbers[pe] > target) {
          pe--;
        } else {
          ps++;
        }
      }
      return new int[] {-1, -1};
    }
  }
}

```

## 三数之和 
[15. 3sum](https://leetcode-cn.com/problems/3sum/)

```java
// ../../../../src/main/java/com/dll/array/ThreeSum.java

package com.dll.array;

import java.util.*;

public class ThreeSum {
  // 1. 双重循环 + hash 法
  class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
      Map<Integer, Integer> map = new HashMap<>();
      for (int i = 0; i < nums.length; i++) {
        map.put(nums[i], i);
      }
      Set<List<Integer>> set = new HashSet<>();
      for (int i = 0; i < nums.length; i++) {
        for (int j = i + 1; j < nums.length; j++) {
          int z = -nums[i] - nums[j];
          if (map.containsKey(z) && map.get(z) != i && map.get(z) != j) {
            Integer[] array = new Integer[] {nums[i], nums[j], z};
            Arrays.sort(array);
            set.add(Arrays.asList(array));
          }
        }
      }
      return new ArrayList<>(set);
    }
  }
  // 2.
}

```

## 四数之和 
[18. 4sum](https://leetcode-cn.com/problems/4sum/)

```java
// ../../../../src/main/java/com/dll/array/FourSum.java

package com.dll.array;

import java.util.*;

public class FourSum {
  // 三重循坏 + hash
  class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
      Map<Integer, Integer> map = new HashMap<>();
      for (int i = 0; i < nums.length; i++) {
        map.put(nums[i], i);
      }
      Set<List<Integer>> set = new HashSet<>();
      for (int i = 0; i < nums.length; i++) {
        for (int j = i + 1; j < nums.length; j++) {
          for (int k = j + 1; k < nums.length; k++) {
            int z = target - nums[i] - nums[j] - nums[k];
            if (map.containsKey(z) && map.get(z) != i && map.get(z) != j && map.get(z) != k) {
              Integer[] array = new Integer[] {nums[i], nums[j], nums[k], z};
              Arrays.sort(array);
              set.add(Arrays.asList(array));
            }
          }
        }
      }
      return new ArrayList<>(set);
    }
  }
}

```
## 组合 
[77. combinations](https://leetcode-cn.com/problems/combinations/)

经过上面的 2、3、4 数之和问题的探索, 发现如果要求「N 个数之和为 target的全部组合」还是有点力不从心，本质上是需要求出给定数组的全部组合，所以先解此题

```java
// ../../../../src/main/java/com/dll/array/Combinations.java

package com.dll.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Combinations {
  // 1. 递归
  class Solution {
    private List<List<Integer>> result = new ArrayList<>();

    private int k;
    private int[] data;

    private void init(int[] data, int k) {
      this.data = data;
      this.k = k;
    }

    private int[] remain(int[] array, int index) {
      int[] result;
      try {
        result = Arrays.copyOfRange(array, index + 1, array.length);
      } catch (Exception e) {
        result = new int[] {};
      }
      return result;
    }

    private void recursion(int[] remain, List<Integer> prevPath) {
      if (prevPath.size() == k) {
        result.add(prevPath);
        return;
      }
      for (int i = 0; i < remain.length; i++) {
        List<Integer> curPath = new ArrayList<>(prevPath);
        curPath.add(remain[i]);
        this.recursion(this.remain(remain, i), curPath);
      }
    }

    public List<List<Integer>> combine(int n, int k) {
      this.init(IntStream.rangeClosed(1, n).toArray(), k);
      this.recursion(this.data, Collections.emptyList());
      return result;
    }
  }

  // 2. 递归（回溯）
  class Solution2 {
    private List<List<Integer>> result = new ArrayList<>();
    // 遍历过程中的实时路径
    private List<Integer> path = new ArrayList<>();
    private int k;
    private int[] data;

    private void init(int[] data, int k) {
      this.data = data;
      this.k = k;
    }

    private int[] remain(int[] array, int index) {
      int[] result;
      try {
        result = Arrays.copyOfRange(array, index + 1, array.length);
      } catch (Exception e) {
        result = new int[] {};
      }
      return result;
    }

    private void recursion(int[] remain) {
      if (this.path.size() == k) {
        result.add(new ArrayList<>(path));
        return;
      }
      for (int i = 0; i < remain.length; i++) {
        path.add(remain[i]);
        this.recursion(this.remain(remain, i));
        // 回溯
        path.remove(path.size() - 1);
      }
    }

    public List<List<Integer>> combine(int n, int k) {
      this.init(IntStream.rangeClosed(1, n).toArray(), k);
      this.recursion(this.data);
      return result;
    }
  }
}

```
## 组合总和
[39. combination-sum](https://leetcode-cn.com/problems/combination-sum/)

```java
// ../../../../src/main/java/com/dll/array/CombinationSum.java

package com.dll.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
  class Solution {
    private List<List<Integer>> result = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();
    private int sum;

    private int target;
    private int[] data;

    private void init(int[] data, int target) {
      this.data = data;
      this.target = target;
    }

    private int[] remain(int[] array, int index) {
      int[] result;
      try {
        result = Arrays.copyOfRange(array, index, array.length);
      } catch (Exception e) {
        result = new int[] {};
      }
      return result;
    }

    private void recursion(int[] array) {
      if (this.sum == this.target) {
        result.add(new ArrayList<>(this.path));
        return;
      }
      if (this.sum > this.target) {
        return;
      }
      for (int i = 0; i < array.length; i++) {
        this.path.add(array[i]);
        this.sum += array[i];
        this.recursion(this.remain(array, i));
        int remove = this.path.remove(this.path.size() - 1);
        this.sum -= remove;
      }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
      this.init(candidates, target);
      this.recursion(this.data);
      return result;
    }
  }
}

```

## 组合总和 II 
[40. combination-sum-ii](https://leetcode-cn.com/problems/combination-sum-ii/)

该题就是 [组合](#组合) 提到的「N 数之和为 target 的全部组合问题」，思路同上

```java
// ../../../../src/main/java/com/dll/array/CombinationSumII.java

package com.dll.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII {
  class Solution {
    private List<Integer> path = new ArrayList<>();
    private int[] sorted_candidates;
    private int[] candidates;
    private int target;
    private List<List<Integer>> result = new ArrayList<>();
    private int sum = 0;

    private void init(int[] candidates, int target) {
      this.candidates = candidates;
      int[] candidatesCopy = Arrays.copyOf(candidates, candidates.length);
      Arrays.sort(candidatesCopy);
      this.sorted_candidates = candidatesCopy;
      this.target = target;
    }

    private int[] remain(int[] array, int index) {
      int[] result;
      try {
        result = Arrays.copyOfRange(array, index + 1, array.length);
      } catch (Exception e) {
        result = new int[] {};
      }
      return result;
    }

    private boolean ifSkipOnContinuesEqual(int[] remain, int index) {
      return index - 1 >= 0 && remain[index] == remain[index - 1];
    }

    private void recursion(int[] remain) {
      if (sum == this.target) {
        result.add(new ArrayList<>(path));
        return;
      } else if (sum > this.target) {
        return;
      }
      for (int i = 0; i < remain.length; i++) {
        if (this.ifSkipOnContinuesEqual(remain, i)) {
          continue;
        }
        path.add(remain[i]);
        sum += remain[i];
        this.recursion(this.remain(remain, i));
        int val = path.remove(path.size() - 1);
        sum -= val;
      }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
      this.init(candidates, target);
      this.recursion(this.sorted_candidates);
      return result;
    }
  }
}

```

## 组合总和 III
[216. combination-sum-iii](https://leetcode-cn.com/problems/combination-sum-iii/)

```java
// ../../../../src/main/java/com/dll/array/CombinationSumIII.java

package com.dll.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class CombinationSumIII {
  class Solution {
    private List<List<Integer>> result = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();
    private int sum;

    private int[] data;
    private int target;
    private int k;

    private void init(int[] data, int target, int k) {
      this.data = data;
      this.target = target;
      this.k = k;
    }

    private int[] remain(int[] array, int index) {
      int[] result;
      try {
        result = Arrays.copyOfRange(array, index + 1, array.length);
      } catch (Exception e) {
        result = new int[] {};
      }
      return result;
    }

    private void recursion(int[] remain) {
      if (this.sum == this.target && this.path.size() == k) {
        this.result.add(new ArrayList<>(this.path));
      }
      if (this.sum > this.target || this.path.size() > k) {
        return;
      }
      for (int i = 0; i < remain.length; i++) {
        this.path.add(remain[i]);
        this.sum += remain[i];
        this.recursion(this.remain(remain, i));
        int remove = this.path.remove(this.path.size() - 1);
        this.sum -= remove;
      }
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
      this.init(IntStream.rangeClosed(1, 9).toArray(), n, k);
      this.recursion(this.data);
      return result;
    }
  }
}

```

## 四数相加 II
[454. 4sum-ii](https://leetcode-cn.com/problems/4sum-ii/)
```java
// ../../../../src/main/java/com/dll/array/FourSumII.java

package com.dll.array;

import java.util.HashMap;
import java.util.Map;

public class FourSumII {

  class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
      Map<Integer, Integer> m1 = new HashMap<>();
      Map<Integer, Integer> m2 = new HashMap<>();

      for (int n1 : nums1) {
        for (int n2 : nums2) {
          int sum = n1 + n2;
          m1.compute(sum, (t, u) -> m1.getOrDefault(sum, 0) + 1);
        }
      }

      for (int n3 : nums3) {
        for (int n4 : nums4) {
          int sum = n3 + n4;
          m2.compute(sum, (t, u) -> m2.getOrDefault(sum, 0) + 1);
        }
      }
      int[] result = {0};
      m1.forEach(
          (k1, v1) -> {
            m2.forEach(
                (k2, v2) -> {
                  if (k1 + k2 == 0) {
                    result[0] += v1 * v2;
                  }
                });
          });
      return result[0];
    }
  }
}

```

## 有效三角形的个数
[611. valid-triangle-number](https://leetcode-cn.com/problems/valid-triangle-number/)

- 满足构成三角形的条件：**任意**两边之和 > 第三边
- 不容易想到的是，按照非严格升序的非负数组
 [a1,a2,a3...an] 满足 a[i] <= a[j] <= a[k] (i < j < k 为数组的下标) ，
 则有如下隐含条件：
    - a[j] + a[k] >= a[i]，当且仅当 a[i], a[j], a[k] 等于 0 时等号成立，不满足构成三角形条件
    - a[i] + a[k] >= a[j]，当且仅当 a[i] = 0 时等号成立，不满足构成三角形条件
 当手动保证 a[i] + a[j] > a[k] 成立时，可以得到 a[i] > 0，即上述等号不成立，此时满足三角形的条件
```java
// ../../../../src/main/java/com/dll/array/ValidTriangleNumber.java

package com.dll.array;

import java.util.Arrays;

public class ValidTriangleNumber {
  class Solution {

    public int triangleNumber(int[] nums) {
      int[] sortedNums = Arrays.stream(nums).sorted().toArray();
      int result = 0;
      for (int i = 0; i < sortedNums.length; i++) {
        for (int j = i + 1; j < sortedNums.length; j++) {
          for (int z = j + 1; z < sortedNums.length; z++) {
            if (sortedNums[i] + sortedNums[j] > sortedNums[z]) {
              result++;
            }
          }
        }
      }
      return result;
    }
  }
}

```

## 移除元素
[27. remove-element](https://leetcode-cn.com/problems/remove-element/)

```java
// ../../../../src/main/java/com/dll/array/RemoveElement.java

package com.dll.array;

public class RemoveElement {
  class Solution {
    public int removeElement(int[] nums, int val) {
      int p1 = 0;
      int p2 = 0;
      for (int i = 0; i < nums.length; i++) {
        if (nums[i] != val) {
          nums[p1] = nums[p2];
          p1++;
        }
        p2++;
      }
      return p1;
    }
  }
}

```

## 合并两个有序数组 
[88. merge-sorted-array](https://leetcode.com/problems/merge-sorted-array/)

- 开辟长度为 nums1.length + nums2.length 的空数组 result
- p1、p2 分别指向两个有序数组起始 index，当任一数组未遍历到末尾时，循环处理以下逻辑：
    - 总是存储当前指针指向的较小值至 result 并维护指针，相等则任选
- 任一数组遍历完成，把剩余数组存入 result
```java
// ../../../../src/main/java/com/dll/array/MergeSortedArray.java

package com.dll.array;

public class MergeSortedArray {

  class Solution {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
      int[] result = new int[m + n];
      int p1 = 0;
      int p2 = 0;
      int index = 0;

      while (p1 < m && p2 < n) {
        if (nums1[p1] <= nums2[p2]) {
          result[index] = nums1[p1];
          p1++;
        } else {
          result[index] = nums2[p2];
          p2++;
        }
        index++;
      }
      for (; p1 < m; p1++) {
        result[index++] = nums1[p1];
      }
      for (; p2 < n; p2++) {
        result[index++] = nums2[p2];
      }
      System.arraycopy(result, 0, nums1, 0, m + n);
    }
  }
}

``` 

## 两个数组的交集
[349. intersection-of-two-arrays](https://leetcode-cn.com/problems/intersection-of-two-arrays/)
- 两个数组去重且从小到大排列
- 依次比较两个数组的每个元素，其中 p1，p2 分别为 arr1，arr2 的下标，按照如下逻辑：
    - arr1[p1] = arr[p2]，存储结果，p1 + 1，p2 + 1
    - arr1[p1] > arr[p2]，p2 + 1 ，反之
```java
// ../../../../src/main/java/com/dll/array/IntersectionOfTwoArrays.java

package com.dll.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntersectionOfTwoArrays {
  class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
      int[] n1 = Arrays.stream(nums1).sorted().distinct().toArray();
      int[] n2 = Arrays.stream(nums2).sorted().distinct().toArray();
      List<Integer> result = new ArrayList<>();
      int p1 = 0;
      int p2 = 0;
      while (p1 < n1.length && p2 < n2.length) {
        if (n1[p1] == n2[p2]) {
          result.add(n1[p1]);
          p1++;
          p2++;
        } else if (n1[p1] > n2[p2]) {
          p2++;
        } else {
          p1++;
        }
      }
      return result.stream().mapToInt(v -> v).toArray();
    }
  }
}

```


## 有序数组的平方
[977. squares-of-a-sorted-array](https://leetcode-cn.com/problems/squares-of-a-sorted-array/)
- 数组非递减排序，故当前数组的平方后的较大值总是出现在数组的两端
- 新建同等大小的数组用于存储结果，总是把较大值按照从尾部到头部存入

```java
// ../../../../src/main/java/com/dll/array/SquaresOfASortedArray.java

package com.dll.array;

public class SquaresOfASortedArray {
  class Solution {
    public int[] sortedSquares(int[] nums) {
      int[] newNums = new int[nums.length];
      int p = newNums.length - 1;
      int p1 = 0;
      int p2 = nums.length - 1;
      for (int i = 0; i < nums.length; i++) {
        int p1Square = nums[p1] * nums[p1];
        int p2Square = nums[p2] * nums[p2];
        if (p1Square < p2Square) {
          newNums[p] = p2Square;
          p2--;
        } else {
          newNums[p] = p1Square;
          p1++;
        }
        p--;
      }
      return newNums;
    }
  }
}

```

## 长度最小的子数组
[209. minimum-size-subarray-sum](https://leetcode-cn.com/problems/minimum-size-subarray-sum/)

```java
// ../../../../src/main/java/com/dll/array/MinimumSizeSubArraySum.java

package com.dll.array;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinimumSizeSubArraySum {
  class Solution {
    public int minSubArrayLen(int target, int[] nums) {
      int res = nums.length + 1;
      Deque<Integer> window = new ArrayDeque<>();
      int windowSum = 0;
      for (int i = 0; i < nums.length; i++) {
        if (windowSum < target) {
          window.offer(nums[i]);
          windowSum += nums[i];
        }
        while (windowSum >= target) {
          if (window.size() < res) {
            res = window.size();
          }
          int pop = window.remove();
          windowSum -= pop;
        }
      }
      return res == nums.length + 1 ? 0 : res;
    }
  }
}

```

## 最短无序连续子数组
[581. shortest-unsorted-continuous-subarray](https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/)

将 nums 数组排序记为 sortedNums，从首尾两端分别比较 nums 和 sortedNums，
若存在 nums[start] != sortedNums[start] && nums[end] != sortedNums[end]，则 [start,end] 区间则为所求

- 时间复杂度：快排 O(n * logn)
- 空间复杂度：开辟数组 O(n)

```java
// ../../../../src/main/java/com/dll/array/ShortestUnsortedContinuousSubarray.java

package com.dll.array;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ShortestUnsortedContinuousSubarray {
  class Solution {
    public int findUnsortedSubarray(int[] nums) {
      int[] sortedNums = Arrays.stream(nums).sorted().toArray();
      int first =
          IntStream.range(0, sortedNums.length)
              .filter(index -> sortedNums[index] != nums[index])
              .findFirst()
              .orElse(0);
      int end =
          IntStream.range(0, sortedNums.length)
              .map(index -> sortedNums.length - 1 - index)
              .filter(index -> sortedNums[index] != nums[index])
              .findFirst()
              .orElse(-1);
      return end - first + 1;
    }
  }
}

```
## 螺旋矩阵 II
[59. spiral-matrix-ii](https://leetcode-cn.com/problems/spiral-matrix-ii/)

```java
// ../../../../src/main/java/com/dll/array/SpiralMatrixII.java

package com.dll.array;

public class SpiralMatrixII {
  class Solution {

    public int[][] generateMatrix(int n) {
      int[][] matrix = new int[n][n];
      int l = 0;
      int r = n - 1;
      int t = 0;
      int b = n - 1;
      int inc = 1;
      while (inc <= n * n) {
        for (int i = l; i <= r; i++) {
          matrix[t][i] = inc++;
        }
        t++;
        for (int i = t; i <= b; i++) {
          matrix[i][r] = inc++;
        }
        r--;
        for (int i = r; i >= l; i--) {
          matrix[b][i] = inc++;
        }
        b--;
        for (int i = b; i >= t; i--) {
          matrix[i][l] = inc++;
        }
        l++;
      }
      return matrix;
    }
  }
}

```

## 环形数组是否存在循环
[457. circular-array-loop](https://leetcode-cn.com/problems/circular-array-loop/)

- 当走到 nums[i] 出现正和负时，不是「循环」
- 循环的步长 <= 1，不是「循环」
- 当出现重复的 index 时，且重复 index 不是第一个加入的值时，不是「循环」

```java
// ../../../../src/main/java/com/dll/array/CircularArrayLoop.java

package com.dll.array;

import java.util.TreeSet;

public class CircularArrayLoop {
  class Solution {
    private boolean isLoop(int[] nums, int index) {
      TreeSet<Integer> set = new TreeSet<>();
      int nextIndex = index;
      boolean flag = nums[nextIndex] > 0;
      while (true) {
        if (nums[nextIndex] > 0 != flag) {
          return false;
        }
        set.add(nextIndex);
        nextIndex =
            (nextIndex + nums[nextIndex] + Math.abs(nums[nextIndex]) * nums.length) % nums.length;

        if (set.contains(nextIndex)) {
          if (nextIndex != index) {
            return false;
          } else if (set.first().intValue() == set.last()) {
            return false;
          }
          break;
        }
      }
      return true;
    }

    public boolean circularArrayLoop(int[] nums) {
      for (int i = 0; i < nums.length; i++) {
        System.out.println(isLoop(nums, i) + "" + i);
        if (isLoop(nums, i)) {
          return true;
        }
      }
      return false;
    }
  }
}

```
## 优美的排列
[526. beautiful-arrangement](https://leetcode-cn.com/problems/beautiful-arrangement/)

回溯法列举全部排列，并判断是否满足需求

```java
// ../../../../src/main/java/com/dll/array/BeautifulArrangement.java

package com.dll.array;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BeautifulArrangement {
  class Solution {
    private List<Integer> path = new ArrayList<>();
    private int N;
    private int result;

    private int[] remain(int[] array, int index) {
      return IntStream.range(0, array.length).filter(i -> index != i).map(i -> array[i]).toArray();
    }

    private boolean isArrangement(int[] array) {
      for (int i = 0; i < array.length; i++) {
        if (array[array.length - i - 1] % (array.length - i) != 0
            && (array.length - i) % array[array.length - i - 1] != 0) {
          return false;
        }
      }
      return true;
    }

    private void recursion(int[] array) {
      if (path.size() > 0
          && path.get(path.size() - 1) % (path.size()) != 0
          && path.size() % path.get(path.size() - 1) != 0) {
        return;
      }
      if (this.path.size() == N) {
        result++;
      }
      for (int i = 0; i < array.length; i++) {
        this.path.add(array[i]);
        recursion(this.remain(array, i));
        this.path.remove(this.path.size() - 1);
      }
    }

    public int countArrangement(int n) {
      init(n);
      this.recursion(IntStream.rangeClosed(1, n).toArray());
      return result;
    }

    private void init(int n) {
      this.N = n;
    }
  }
}

```

## 接雨水
[42. trapping-rain-water](https://leetcode.cn/problems/trapping-rain-water)

```java
// ../../../../src/main/java/com/dll/array/TrappingRainWater42.java

package com.dll.array;

public class TrappingRainWater42 {
  class Solution {
    public int trap(int[] height) {
      int[] maxLeft = new int[height.length];
      int[] maxRight = new int[height.length];
      maxLeft[0] = 0;
      for (int i = 1; i < height.length; i++) {
        maxLeft[i] = Math.max(maxLeft[i - 1], height[i - 1]);
      }
      maxRight[height.length - 1] = 0;
      for (int j = height.length - 2; j >= 0; j--) {
        maxRight[j] = Math.max(maxRight[j + 1], height[j + 1]);
      }
      int count = 0;
      for (int z = 0; z < height.length; z++) {
        int low = Math.min(maxLeft[z], maxRight[z]);
        if (height[z] < low) {
          count += low - height[z];
        }
      }
      return count;
    }
  }
}

```

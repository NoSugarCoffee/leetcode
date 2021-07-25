---
weight: 1
title: "数组"
---
## 二分查找
使用二分法需要满足以下条件：
- 有序
- 无重复（不强制，重复会导致查找到的 index 不唯一）

### 二分查找
[704. leetcode](https://leetcode-cn.com/problems/binary-search/)

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

### x 的平方根 
[69. leetcode](https://leetcode-cn.com/problems/sqrtx/)

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

### 在排序数组中查找元素的第一个和最后一个位置 
[34. leetcode](https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/)

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
            return new int[]{res1, res2};
        }
    }
}

```

## N 数之和
### 两数之和  
[1. leetcode](https://leetcode-cn.com/problems/two-sum/) 

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
                        return new int[]{i, j};
                    }
                }
            }
            return new int[]{-1, -1};
        }
    }

    // 2. hash
    class Solution2 {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (map.containsKey(target - nums[i])) {
                    return new int[]{map.get(target - nums[i]), i};
                }
                map.put(nums[i], i);
            }
            return new int[]{-1, -1};
        }
    }
}

```

### 两数之和 II - 输入有序数组 
[167. leetcode](https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/) 

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

### 三数之和 
[15. leetcode](https://leetcode-cn.com/problems/3sum/)

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
                    if (map.containsKey(z)
                            && map.get(z) != i
                            && map.get(z) != j) {
                        Integer[] array = new Integer[]{nums[i], nums[j], z};
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

### 四数之和 
[18. leetcode](https://leetcode-cn.com/problems/4sum/)

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
                        if (map.containsKey(z)
                                && map.get(z) != i
                                && map.get(z) != j
                                && map.get(z) != k) {
                            Integer[] array = new Integer[]{nums[i], nums[j], nums[k], z};
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
### 组合 
[77. leetcode](https://leetcode-cn.com/problems/combinations/)

经过上面的 2、3、4 数之和问题的探索, 发现如果要求「N 个数之和为 target的全部组合」还是有点力不从心，本质上是需要求出给定数组的全部组合，所以先解此题

```java
// ../../../../src/main/java/com/dll/array/Combinations.java

package com.dll.array;

import java.util.*;
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
                result = new int[]{};
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
                result = new int[]{};
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
### 组合总和
[39. leetcode](https://leetcode-cn.com/problems/combination-sum/)

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
                result = new int[]{};
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

### 组合总和 II 
[40. leetcode](https://leetcode-cn.com/problems/combination-sum-ii/)

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
                result = new int[]{};
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

### 组合总和 III
[216. leetcode](https://leetcode-cn.com/problems/combination-sum-iii/)

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
                result = new int[]{};
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

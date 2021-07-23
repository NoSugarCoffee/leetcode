---
weight: 1
title: "数组"
---

## N 数之和相关问题
### 两数之和  [1](https://leetcode-cn.com/problems/two-sum/) 

```java
// ../../../../../src/main/java/com/dll/array/TwoSum.java

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

### 两数之和 II - 输入有序数组 [167](https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/) 

- 使用指针 ps 指向数组开头, 指针 pe 指向数组末尾
- 当 numbers[ps] + numbers[pe] > target, 则 pe 向前移动, 否则 ps 向后移动, 直到 = target

```java
// ../../../../../src/main/java/com/dll/array/TwoSumIIInputArrayIsSorted.java

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

### 三数之和 [15](https://leetcode-cn.com/problems/3sum/)

```java
// ../../../../../src/main/java/com/dll/array/ThreeSum.java

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

### 四数之和 [18](https://leetcode-cn.com/problems/4sum/)

```java
// ../../../../../src/main/java/com/dll/array/FourSum.java

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
### 组合 [77](https://leetcode-cn.com/problems/combinations/)


经过上面的 2、3、4 数之和问题的探索，发现如果要求“ n 个数之和为 target 的全部组合”还是有点力不从心，本质上是需要求出给定数组的全部组合

```java
// ../../../../../src/main/java/com/dll/array/Combinations.java

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


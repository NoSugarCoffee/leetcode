---
weight: 2
title: "数组"
---
# 数组

## N 数之和
### 1. 两数之和
[leetcode](https://leetcode-cn.com/problems/two-sum/)
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
### 15. 三数之和
[leetcode](https://leetcode-cn.com/problems/3sum/)
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

### 18. 四数之和
[leetcode](https://leetcode-cn.com/problems/4sum/)
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

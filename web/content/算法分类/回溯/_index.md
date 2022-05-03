---
weight: 8
title: "回溯"
---

## 77. 组合
[leetcode](https://leetcode-cn.com/problems/combinations/)

```java
// ../../../../src/main/java/com/dll/backtracing/Combinations.java

package com.dll.backtracing;

import java.util.ArrayList;
import java.util.List;

public class Combinations {
    class Solution {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();

        public void backTracing(int startIndex, int n, int k) {
            if (path.size() == k) {
                result.add(new ArrayList<>(path));
                return;
            }
            for (int i = startIndex; i <= n - (k - path.size() - 1); i++) {
                path.add(i);
                backTracing(i + 1, n, k);
                path.remove(path.size() - 1);
            }
        }

        public List<List<Integer>> combine(int n, int k) {
            backTracing(1, n, k);
            return result;
        }
    }
}

```

## 216. 组合总和 III
[leetcode](https://leetcode-cn.com/problems/combination-sum-iii/)

```java
// ../../../../src/main/java/com/dll/backtracing/CombinationSumIII.java

package com.dll.backtracing;

import java.util.ArrayList;
import java.util.List;

public class CombinationSumIII {
    class Solution {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();

        int sum(List<Integer> path) {
            return path.stream().reduce(0, Integer::sum);
        }

        void backTracing(int k, int n, int startIndex) {
            int total = sum(path);
            if (total == n && path.size() == k) {
                result.add(new ArrayList<>(path));
                return;
            } else if (total > n || path.size() > k) {
                return;
            }
            for (int i = startIndex; i <= 9 - (k - path.size()) + 1; i++) {
                path.add(i);
                backTracing(k, n, startIndex + 1);
                path.remove(path.size() - 1);
            }
        }

        public List<List<Integer>> combinationSum3(int k, int n) {
            backTracing(k, n, 1);
            return result;
        }
    }
}

```


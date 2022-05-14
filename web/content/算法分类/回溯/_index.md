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

### 17. 电话号码的字母组合
[leetcode](https://leetcode.cn/problems/letter-combinations-of-a-phone-number/)
```java
// ../../../../src/main/java/com/dll/backtracing/LetterCombinationsOfAPhoneNumber.java

package com.dll.backtracing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsOfAPhoneNumber {
    class Solution {
        private final Map<Integer, Character[]> numAndLetters = mappingNumAndLetters();
        private final List<Character> path = new ArrayList<>();
        private final List<String> result = new ArrayList<>();

        Map<Integer, Character[]> mappingNumAndLetters() {
            Map<Integer, Character[]> map = new HashMap<>();
            char ch = 'a';
            for (int i = 2; i <= 9; i++) {
                int loop = (i == 7 || i == 9) ? 4 : 3;
                List<Character> letters = new ArrayList<>();
                while (ch <= 'z') {
                    loop--;
                    letters.add(ch);
                    ch++;
                    if (loop <= 0) {
                        map.put(i, letters.toArray(new Character[0]));
                        break;
                    }
                }

            }
            return map;
        }

        public void backTracing(String digits, int index) {
            if (path.size() >= digits.length()) {
                if (path.size() == digits.length()) {
                    StringBuilder sb = new StringBuilder();
                    path.forEach(sb::append);
                    // edge case: digits = ""
                    if (!sb.toString().isEmpty()) {
                        result.add(sb.toString());
                    }
                }
                return;
            }
            Character[] candidates = numAndLetters.get(Character.getNumericValue(digits.charAt(index)));
            for (char candidate : candidates) {
                path.add(candidate);
                backTracing(digits, index + 1);
                path.remove(path.size() - 1);
            }
        }

        public List<String> letterCombinations(String digits) {
            backTracing(digits, 0);
            return result;
        }
    }
}

```



---
weight: 1
title: "数学"
---
## 哈希表
### 快乐数
[202. leetcode](https://leetcode-cn.com/problems/happy-number/)
- squareSumPerPosition 函数计算给定数字的各位置平方和
- set 记录每次数字，如果出现重复，则表明一定是无限循环，否则一直在「进步」 
```java
// ../../../../src/main/java/com/dll/math/HappyNumber.java

package com.dll.math;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {
    class Solution {
        int squareSumPerPosition(int number) {
            int result = 0;
            while (number != 0) {
                result += Math.pow(number % 10, 2);
                number /= 10;
            }
            return result;
        }

        public boolean isHappy(int n) {
            Set<Integer> visited = new HashSet<>();
            int next = n;
            while (!visited.contains(next)) {
                visited.add(next);
                next = squareSumPerPosition(next);
                if (next == 1) {
                    return true;
                }
            }
            return false;
        }
    }
}

```

### 第 N 个泰波那契数
[1137. leetcode](https://leetcode-cn.com/problems/n-th-tribonacci-number/)

```java
// ../../../../src/main/java/com/dll/math/NThTribonacciNumber.java

package com.dll.math;

import java.util.HashMap;
import java.util.Map;

public class NThTribonacciNumber {
    class Solution {
        private Map<Integer, Integer> map = new HashMap<>();

        {
            map.put(0, 0);
            map.put(1, 1);
            map.put(2, 2);
        }

        public int tribonacci(int n) {
            if (map.containsKey(n)) {
                return map.get(n);
            }
            int result = tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
            map.put(n, result);
            return result;
        }
    }
}

```
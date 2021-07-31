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
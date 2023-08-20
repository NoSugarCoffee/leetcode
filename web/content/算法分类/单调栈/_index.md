---
weight: 2
title: "单调栈"
---

## 每日温度

[739. daily-temperatures](https://leetcode.cn/problems/daily-temperatures)

```java
// ../../../../src/main/java/com/dll/monotonicstack/DailyTemperatures.java

package com.dll.monotonicstack;

import java.util.Stack;

public class DailyTemperatures {
  class Temperature {
    int value;
    int index;

    public Temperature(int value, int index) {
      this.value = value;
      this.index = index;
    }

    public int getValue() {
      return value;
    }

    public int getIndex() {
      return index;
    }
  }

  class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
      Stack<Temperature> stack = new Stack<>();
      int[] result = new int[temperatures.length];

      for (int i = 0; i < temperatures.length; i++) {
        Temperature current = new Temperature(temperatures[i], i);
        while (!stack.empty()) {
          Temperature peeked = stack.peek();
          if (current.getValue() > peeked.value) {
            Temperature popped = stack.pop();
            result[popped.index] = current.index - popped.index;
          } else {
            break;
          }
        }
        stack.push(current);
      }
      return result;
    }
  }
}

```

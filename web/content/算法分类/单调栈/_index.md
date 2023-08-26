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

## 接雨水
[42. trapping-rain-water](https://leetcode.cn/problems/trapping-rain-water)

```java
// ../../../../src/main/java/com/dll/monotonicstack/TrappingRainWater.java

package com.dll.monotonicstack;

import java.util.Stack;

public class TrappingRainWater {
  class Solution {
    class Elevation {
      int value;
      int index;

      public Elevation(int value, int index) {
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

    public int trap(int[] height) {
      int traps = 0;
      Stack<Elevation> stack = new Stack<>();
      for (int i = 0; i < height.length; i++) {
        Elevation current = new Elevation(height[i], i);
        if (!stack.empty() && current.getValue() >= stack.peek().getValue()) {
          Elevation top = stack.peek();
          while (current.getValue() >= top.getValue() && !stack.empty()) {
            Elevation pop = stack.pop();
            if (stack.empty()) {
              break;
            }
            if (current.getValue() > top.getValue()) {
              Elevation peekAfterPop = stack.peek();
              int trap =
                  (Math.min(peekAfterPop.getValue(), current.getValue()) - pop.getValue())
                      * (current.getIndex() - peekAfterPop.getIndex() - 1);
              traps += trap;
            }
            top = stack.peek();
          }
        }
        stack.push(current);
      }
      return traps;
    }
  }
}

```
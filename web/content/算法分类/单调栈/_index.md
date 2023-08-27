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
## 下一个更大元素 I
[496. next-greater-element-i](https://leetcode.cn/problems/next-greater-element-i/)

```java
// ../../../../src/main/java/com/dll/monotonicstack/NextGreaterElementI.java

package com.dll.monotonicstack;

import java.util.Arrays;
import java.util.HashMap;import java.util.Map;import java.util.Stack;

public class NextGreaterElementI {
  class Element {
    int value;
    int index;

    public Element(int value, int index) {
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
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
      Map<Integer, Integer> nextGreaterElements = new HashMap<>();
      Stack<Element> stack = new Stack<>();
      for (int i = 0; i < nums2.length; i++) {
        Element current = new Element(nums2[i], i);
        while (!stack.empty()) {
          Element peek = stack.peek();
          if (current.getValue() > peek.getValue()) {
            Element pop = stack.pop();
            nextGreaterElements.put(pop.getValue(), current.getValue());
          } else  {
            break;
          }
        }
        stack.push(current);
      }
      return Arrays.stream(nums1)
          .map(v -> nextGreaterElements.get(v) == null ? -1 : nextGreaterElements.get(v))
          .toArray();
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
        while (!stack.empty()) {
          Elevation peek = stack.peek();
          if (current.getValue() >= peek.getValue()) {
            Elevation pop = stack.pop();
            if (current.getValue() > peek.getValue() && !stack.empty()) {
              Elevation peekAfterPop = stack.peek();
              int trap =
                  (Math.min(peekAfterPop.getValue(), current.getValue()) - pop.getValue())
                      * (current.getIndex() - peekAfterPop.getIndex() - 1);
              traps += trap;
            }
          } else {
            break;
          }
        }
        stack.push(current);
      }
      return traps;
    }
  }
}

```
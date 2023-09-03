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
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

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
          } else {
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

## 下一个更大元素 II
[503. next-greater-element-ii](https://leetcode.cn/problems/next-greater-element-ii/)

```java
// ../../../../src/main/java/com/dll/monotonicstack/NextGreaterElementII.java

package com.dll.monotonicstack;

import java.util.Arrays;
import java.util.Stack;
import java.util.stream.IntStream;

public class NextGreaterElementII {
  class Solution {
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

    public int[] nextGreaterElements(int[] nums) {
      int[] target = IntStream.concat(Arrays.stream(nums), Arrays.stream(nums)).toArray();
      int[] result = new int[nums.length * 2];
      Arrays.fill(result, -1);

      Stack<Element> stack = new Stack<>();
      for (int i = 0; i < target.length; i++) {
        Element current = new Element(target[i], i);
        while (!stack.isEmpty()) {
          Element peek = stack.peek();
          if (current.getValue() > peek.getValue()) {
            Element pop = stack.pop();
            result[pop.getIndex()] = current.getValue();
          } else {
            break;
          }
        }
        stack.push(current);
      }
      return Arrays.stream(result).limit(nums.length).toArray();
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

## 柱状图中最大的矩形
[84. largest-rectangle-in-histogram](https://leetcode.cn/problems/largest-rectangle-in-histogram/)

```java
// ../../../../src/main/java/com/dll/monotonicstack/LargestRectangleInHistogram.java

package com.dll.monotonicstack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.IntStream;

public class LargestRectangleInHistogram {
  class Solution {
    class Rectangle {
      int height;
      int index;

      public Rectangle(int height, int index) {
        this.height = height;
        this.index = index;
      }

      public int getHeight() {
        return height;
      }

      public int getIndex() {
        return index;
      }
    }

    public int largestRectangleArea(int[] heights) {
      Deque<Rectangle> stack = new ArrayDeque<>();
      int[] heights_ =
          IntStream.concat(Arrays.stream(heights), Arrays.stream(new int[] {0})).toArray();
      stack.push(new Rectangle(0, -1));
      int maxArea = 0;
      for (int i = 0; i < heights_.length; i++) {
        Rectangle current = new Rectangle(heights_[i], i);
        while (!stack.isEmpty()) {
          Rectangle peek = stack.peek();
          if (current.getHeight() < peek.getHeight()) {
            Rectangle pop = stack.pop();
            if (!stack.isEmpty()) {
              Rectangle peekAfterPop = stack.peek();
              maxArea =
                  Math.max(
                      maxArea,
                      (current.getIndex() - 1 - peekAfterPop.getIndex()) * pop.getHeight());
            }
          } else {
            break;
          }
        }
        stack.push(current);
      }
      return maxArea;
    }
  }
}

```
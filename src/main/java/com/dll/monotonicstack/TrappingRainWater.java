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

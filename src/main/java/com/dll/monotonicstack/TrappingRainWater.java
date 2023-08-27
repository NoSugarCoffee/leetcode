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

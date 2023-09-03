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

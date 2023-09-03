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

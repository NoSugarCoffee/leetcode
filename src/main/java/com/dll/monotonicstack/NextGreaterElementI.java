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

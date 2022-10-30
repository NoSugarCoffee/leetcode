package com.dll.offer;

import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Offer09 {
  class CQueue {
    Deque<Integer> inQueueStack = new LinkedList<>();
    Deque<Integer> deQueueStack = new LinkedList<>();

    public CQueue() {}

    public void appendTail(int value) {
      inQueueStack.push(value);
    }

    private int popWrapper() {
      try {
        return deQueueStack.pop();
      } catch (NoSuchElementException e) {
        return -1;
      }
    }

    public int deleteHead() {
      if (deQueueStack.isEmpty()) {
        while (!inQueueStack.isEmpty()) {
          deQueueStack.push(inQueueStack.pop());
        }
      }
      return popWrapper();
    }
  }
}

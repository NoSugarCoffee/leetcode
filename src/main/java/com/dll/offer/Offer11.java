package com.dll.offer;

public class Offer11 {
  class Solution {
    public int minArray(int[] numbers) {
      if (numbers == null || numbers.length < 1) {
        throw new RuntimeException();
      }
      int min = numbers[0];
      for (int i = 1; i < numbers.length; i++) {
        if (numbers[i] < numbers[i - 1]) {
          min = numbers[i];
          break;
        }
      }
      return min;
    }
  }
}

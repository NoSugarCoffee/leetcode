package com.dll.math;

import java.util.HashMap;
import java.util.Map;

public class NThTribonacciNumber {
  class Solution {
    private Map<Integer, Integer> map = new HashMap<>();

    {
      map.put(0, 0);
      map.put(1, 1);
      map.put(2, 2);
    }

    public int tribonacci(int n) {
      if (map.containsKey(n)) {
        return map.get(n);
      }
      int result = tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
      map.put(n, result);
      return result;
    }
  }
}

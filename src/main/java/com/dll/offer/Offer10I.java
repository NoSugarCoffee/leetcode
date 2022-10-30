package com.dll.offer;

import java.util.HashMap;
import java.util.Map;

public class Offer10I {
  class Solution {
    Map<Integer, Integer> cache = new HashMap<>();

    public int fib(int n) {
      if (cache.containsKey(n)) {
        return cache.get(n);
      }
      if (n < 2) {
        return n;
      }
      int val = (fib(n - 1) + fib(n - 2)) % 1000000007;
      cache.put(n, val);
      return val;
    }
  }
}

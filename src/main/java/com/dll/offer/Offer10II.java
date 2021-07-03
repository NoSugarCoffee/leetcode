
package com.dll.offer;

import java.util.HashMap;
import java.util.Map;

public class Offer10II {
  class Solution {

    Map<Integer, Integer> cache = new HashMap<>();

    private int recu(int n) {
      if (cache.containsKey(n)) {
        return cache.get(n);
      }
      if (n == 0 || n == 1) {
        return 1;
      }
      int val = (recu(n - 1) + recu(n - 2)) % 1000000007;
      cache.put(n, val);
      return val;
    }

    public int numWays(int n) {
      return recu(n);
    }
  }
}

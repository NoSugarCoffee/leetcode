package com.dll.dp;

public class IntegerBreak {
  class Solution {
    public int integerBreak(int n) {
      int[] dp = new int[n + 1];
      for (int i = 2; i <= n; i++) {
        for (int j = i - 1; j > 0; j--) {
          dp[i] = Math.max(dp[i], Math.max(j * dp[i - j], j * (i - j)));
        }
      }
      return dp[n];
    }
  }
}

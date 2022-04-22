package com.dll.dp;

public class FibonacciNumber {
    class Solution {
        public int fib(int n) {
            if (n < 2) {
                return n;
            }
            int[] dp = new int[2];
            dp[1] = 1;
            for (int i = 2; i <= n; i++) {
                int fibN = dp[0] + dp[1];
                dp[0] = dp[1];
                dp[1] = fibN;
            }
            return dp[1];
        }
    }
}

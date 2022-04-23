package com.dll.dp;

import java.util.Arrays;

public class UniquePaths {
    class Solution {
        public int uniquePaths(int m, int n) {
            int[][] dp = new int[m + 1][n + 1];
            Arrays.stream(dp).forEach(item -> Arrays.fill(item, 1));

            for (int i = 2; i <= m; i++) {
                for (int j = 2; j <= n; j++) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
            return dp[m][n];
        }
    }
}

---
weight: 9
title: "动态规划"
---

## 斐波那契数
[509. leetcode](https://leetcode-cn.com/problems/fibonacci-number/)
```java
// ../../../../src/main/java/com/dll/dp/FibonacciNumber.java

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

```

## 爬楼梯
[70. leetcode](https://leetcode-cn.com/problems/climbing-stairs/)

```java
// ../../../../src/main/java/com/dll/dp/ClimbingStairs.java

package com.dll.dp;

public class ClimbingStairs {
    class Solution {
        public int climbStairs(int n) {
            if (n <= 2) {
                return n;
            }
            int[] dp = new int[n + 1];
            dp[1] = 1;
            dp[2] = 2;
            for (int i = 3; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            return dp[n];
        }
    }
}

```

## 使用最小花费爬楼梯
[746. leetcode](https://leetcode-cn.com/problems/min-cost-climbing-stairs/)

```java
// ../../../../src/main/java/com/dll/dp/MinCostClimbingStairs.java

package com.dll.dp;

public class MinCostClimbingStairs {
    class Solution {
        public int minCostClimbingStairs(int[] cost) {
            int[] dp = new int[cost.length];
            dp[0] = cost[0];
            dp[1] = cost[1];
            for (int i = 2; i < cost.length; i++) {
                dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
            }
            return Math.min(dp[cost.length - 1], dp[cost.length - 2]);
        }
    }
}

```

## 不同路径
[62. leetcode](https://leetcode-cn.com/problems/unique-paths/)

```java
// ../../../../src/main/java/com/dll/dp/UniquePaths.java

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

```

## 不同路径 II
[63. leetcode](https://leetcode-cn.com/problems/unique-paths-ii/)

```java
// ../../../../src/main/java/com/dll/dp/UniquePathsII.java

package com.dll.dp;

public class UniquePathsII {
    class Solution {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            int m = obstacleGrid.length;
            int n = obstacleGrid[0].length;
            int[][] dp = new int[m + 1][n + 1];
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (obstacleGrid[i - 1][j - 1] == 1) {
                        dp[i][j] = 0;
                    } else if (i == 1 && j == 1) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                    }
                }
            }
            return dp[m][n];
        }
    }
}

```


## 整数拆分
[343. leetcode](https://leetcode-cn.com/problems/integer-break/)

```java
// ../../../../src/main/java/com/dll/dp/IntegerBreak.java

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

```
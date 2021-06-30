---
weight: 3
title: "剑指 offer"
---

## 04. 二维数组中的查找

[leetcode](https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/)

```java
// ../../../../src/main/java/com/dll/offer/Offer04.java

package com.dll.offer;

public class Offer04 {
  class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
      int rows;
      if (matrix == null || (rows = matrix.length) < 1) {
        return false;
      }
      int cols = matrix[0].length;
      int i = 0;
      int j = cols - 1;

      while (i < rows  && j > -1) {
        int upperRightCorner = matrix[i][j];
        if (upperRightCorner == target) {
          return true;
        } else if (upperRightCorner > target) {
          j--;
        } else {
          i++;
        }
      }
      return false;
    }
  }
}

```


## 05. 替换空格
[leetcode](https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/)
```java
// ../../../../src/main/java/com/dll/offer/Offer05.java

package com.dll.offer;

import java.util.stream.Stream;

public class Offer05 {
  class Solution {
    public String replaceSpace(String s) {
      if (s == null) {
        return "";
      }
      StringBuilder sb = new StringBuilder();
      Stream.of(s.split("")).forEach(ss -> {
        if (" ".equals(ss)) {
          sb.append("%20");
        } else {
          sb.append(ss);
        }
      });
      return sb.toString();
    }
  }
}

```


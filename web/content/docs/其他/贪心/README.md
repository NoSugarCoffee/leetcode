---
weight: 2
title: "贪心"
---

# 贪心

## 455. 分发饼干
[leetcode](https://leetcode-cn.com/problems/assign-cookies/)

- 对每块饼干都去匹配一下胃口，满足饼干 >= 胃口，即满足
- 为了不出现如大的饼干被小的胃口占用，即应该使大的饼干分配给大胃口，对饼干和胃口进行升序排列

```java
// ../../../../../src/main/java/com/dll/greedy/AssignCookies.java

package com.dll.greedy;

import java.util.Arrays;

public class AssignCookies {

  class Solution {

    public int findContentChildren(int[] g, int[] s) {
      // rename
      int[] appetites = g;
      int[] cookies = s;

      // ascending
      Arrays.sort(cookies);
      Arrays.sort(appetites);

      int counter = 0;

      for (int cookie : cookies) {
        if (counter < appetites.length && cookie >= appetites[counter]) {
          counter++;
        }
      }
      return counter;
    }
  }
}



```

## 135. 分发糖果
[leetcode](https://leetcode-cn.com/problems/candy/)

### 我的思路
1. 把所有孩子的糖果数初始化为 1
2. 去重且按升序排列评分数组
3. 依次按照评分数组的顺序去更新糖果数组, 更新当前的位置的糖果数为评分比其高的左或右糖果数(取大值) + 1
```
如 [0,1,2,5,3,2,7]:
得到初始化数组: [1,1,1,1,1,1,1]
得到评分数组: [0,1,2,3,5,7]
更新第一轮:
0 -> [1,1,1,1,1,1,1]
1 -> [1,2,1,1,1,1,1]
2 -> [1,2,3,1,1,1,1]
3 -> [1,2,3,1,3,1,1]
5 -> [1,2,3,4,3,1,1]
7 -> [1,2,3,4,2,1,2]
```

### 书上思路
1. 把所有孩子的糖果数初始化为 1
2. 先从左往右遍历一遍，如果右边孩子的评分比左边的高，则右边孩子的糖果数更新为左边孩子的糖果数加 1
3. 从右往左遍历一遍，如果左边孩子的评分比右边的高，且左边孩子当前的糖果数不大于右边孩子的糖果数，则左边孩子的糖果数更新为右边孩子的糖果数加 1

```java
// ../../../../../src/main/java/com/dll/greedy/Candy.java

package com.dll.greedy;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

public class Candy {

  class Solution {

    public int candy(int[] ratings) {
      // use candy array to store result
      int[] candy = new int[ratings.length];
      // uniq and ascending
      int[] uniqAndSortedRats = IntStream.of(ratings).distinct().sorted().toArray();
      // set default value
      Arrays.fill(candy, 1);

      for (int uasr : uniqAndSortedRats) {
        for (int i = 0; i < ratings.length; i++) {
          if (uasr == ratings[i]) {
            this.updateCandy(ratings, candy, i);
          }
        }
        System.out.println(Arrays.toString(candy));
      }
      return IntStream.of(candy).sum();
    }

    Optional<Integer> getLeftValue(int[] arr, int index) {
      if (index == 0) {
        return Optional.empty();
      }
      return Optional.of(arr[index - 1]);
    }

    Optional<Integer> getRightValue(int[] arr, int index) {
      if (index == arr.length - 1) {
        return Optional.empty();
      }
      return Optional.of(arr[index + 1]);
    }

    int max(int a, int b) {
      return a > b ? a : b;
    }

    void updateCandy(int[] ratings, int[] candy, int index) {
      // 判断 ratings[index] 是否存在比 ratings[index - 1] or ratings[index + 1] 大
      // true 则按照如下更新：
      // 1. 左右均比其大，则取 max(candy[index-1], candy(index+1)) + 1
      // 2. 一侧大则取一侧的值 + 1
      // false 则不更新
      int current = ratings[index];
      Optional<Integer> leftRatingsValue = getLeftValue(ratings, index);
      Optional<Integer> rightRatingsValue = getRightValue(ratings, index);

      Optional<Integer> leftCandyValue = getLeftValue(candy, index);
      Optional<Integer> rightCandyValue = getRightValue(candy, index);

      if ((leftRatingsValue.isPresent() && leftRatingsValue.get() < current)
          && (rightRatingsValue.isPresent() && rightRatingsValue.get() < current)) {
        candy[index] = max(leftCandyValue.get(), rightCandyValue.get()) + 1;
      } else if (leftRatingsValue.isPresent()
          && leftRatingsValue.get() < current) {
        candy[index] = leftCandyValue.get() + 1;
      } else if (rightRatingsValue.isPresent()
          && rightRatingsValue.get() < current) {
        candy[index] = rightCandyValue.get() + 1;
      }
    }
  }
}

```

## 435. 无重叠区间
[leetcode](https://leetcode-cn.com/problems/non-overlapping-intervals/) 

### 我的思路
错误思路: 原本想的是优先选择区间小的，但是如下情况就不满足：
```
[5,7] [3,6] [6,20]
正确应该是去除 [5,7] 区间
但是按照我的思路会选 [5,7] 去除 [3,6] [6,20] 区间
```

### 书上思路
按照区间右端点升序排列，且一直维护当前右端点为不重复区间的最大右端点

```java
// ../../../../../src/main/java/com/dll/greedy/NonOverlappingIntervals.java

package com.dll.greedy;

import java.util.Arrays;
import java.util.Comparator;

public class NonOverlappingIntervals {

  class Solution {

    public int eraseOverlapIntervals(int[][] intervals) {
      if (intervals.length < 1) {
        return 0;
      }
      Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));
      int end = intervals[0][1];
      int counter = 0;
      for (int i = 1; i < intervals.length; i++) {
        if (intervals[i][0] < end) {
          counter++;
        } else {
          end = intervals[i][1];
        }
      }
      return counter;
    }
  }
}
```

## 605. 种花问题
[leetcode](https://leetcode-cn.com/problems/can-place-flowers/)

### 我的思路
在 1 的左右侧设置'障碍物', 用 2 代表, 根据连续多少个 0, 能发现如下规律:
```
连续 1 个 0 -> 可种 1 朵花
连续 2 个 0 -> 可种 1 朵花
连续 3 个 0 -> 可种 2 朵花
连续 n 个 0 -> 可种 (n+1)/2 朵花
```

```java
// ../../../../../src/main/java/com/dll/greedy/CanPlaceFlowers.java

package com.dll.greedy;

public class CanPlaceFlowers {

  class Solution {

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
      int zeroCountContinuous = 0;
      int counter = 0;
      for (int i = 0; i < flowerbed.length; i++) {
        if (flowerbed[i] == 1) {
          if (i - 1 >= 0) {
            flowerbed[i - 1] = 2;
            if (zeroCountContinuous > 0) {
              zeroCountContinuous--;
            }
          }
          if (i + 1 < flowerbed.length) {
            flowerbed[i + 1] = 2;
          }
          counter += (zeroCountContinuous + 1) / 2;
          zeroCountContinuous = 0;
        } else if (flowerbed[i] == 0) {
          zeroCountContinuous++;
        }
      }
      if (zeroCountContinuous > 0) {
        counter += (zeroCountContinuous + 1) / 2;
      }
      return counter >= n;
    }
  }
}

```

### 贪心策略 (TODO)
遍历，能种就种（在可种的时候不种都不会得到更优解）

## 452. 用最少数量的箭引爆气球
[leetcode](https://leetcode-cn.com/problems/minimum-number-of-arrows-to-burst-balloons/)
```
   |----| A
|---| B 
       |----| C
  |------------| D
                  |--| E
```      
通过观察不难发现可以取任一线段(气球), 尝试找与其有交集的任一线段,
若不存在, 则此线段是独立线段, 花费一支箭, 若存在, 则继续找下一条与该交集相交的线段（尽可能多的）, 直至不存在, 花费一支箭

然而写完代码发现这个思路竟然是错误的！

例: [3,8],[7,12],[9,10],[0,6]
```
    |---| [3,8] A
      |------------| [7,12] B
              |---| [9,10] C
|----| [0,6] D    

按照代码逻辑: AB 一箭, C 一箭, D 一箭
事实存在更优: AD 一箭, BC 一箭
```
进一步, 如果按照其左或右端点排序, 就能 AC 该问题(虽然效率很低)
```
|----| [0,6] D 
    |---| [3,8] A
              |---| [9,10] C    
      |------------| [7,12] B

```


```java
// ../../../../../src/main/java/com/dll/greedy/MinimumNumberOfArrowsToBurstBalloons.java

package com.dll.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MinimumNumberOfArrowsToBurstBalloons {

  class Solution {

    public int findMinArrowShots(int[][] points) {
      Arrays.sort(points, Comparator.comparingInt(o -> o[1]));
      List<int[]> balloons = new ArrayList<>(Arrays.asList(points));
      int arrows = 0;
      while (!balloons.isEmpty()) {
        int[] p1 = balloons.get(0);
        balloons.remove(0);
        for (int i = 0; i < balloons.size(); i++) {
          if ((balloons.get(i)[0] >= p1[0] && balloons.get(i)[0] <= p1[1])
              || (balloons.get(i)[1] >= p1[0] && balloons.get(i)[1] <= p1[1])
              || (balloons.get(i)[0] < p1[0] && balloons.get(i)[1] > p1[1])) {
            int start = balloons.get(i)[0] > p1[0] ? balloons.get(i)[0] : p1[0];
            int end = balloons.get(i)[1] < p1[1] ? balloons.get(i)[1] : p1[1];
            p1 = new int[]{start, end};
            balloons.remove(i--);
          }
        }
        arrows++;
      }
      return arrows;
    }
  }
}

```

**为啥排序能解决问题？**

排序解除了'剩两端'的问题, 如上例中, 当 AB 组合的时候, 可能会同时存在类似 C.start > A.end , D.end < B.start 的情形, 而排序后刚好破坏了这种情形

```
    |---| [3,8] A
      |------------| [7,12] B
        |>>>>>>>  C
<<<<<<| D   
``` 
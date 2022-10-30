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
      } else if (leftRatingsValue.isPresent() && leftRatingsValue.get() < current) {
        candy[index] = leftCandyValue.get() + 1;
      } else if (rightRatingsValue.isPresent() && rightRatingsValue.get() < current) {
        candy[index] = rightCandyValue.get() + 1;
      }
    }
  }
}

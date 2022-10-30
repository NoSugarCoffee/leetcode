package com.dll.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class CombinationSumIII {
  class Solution {
    private List<List<Integer>> result = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();
    private int sum;

    private int[] data;
    private int target;
    private int k;

    private void init(int[] data, int target, int k) {
      this.data = data;
      this.target = target;
      this.k = k;
    }

    private int[] remain(int[] array, int index) {
      int[] result;
      try {
        result = Arrays.copyOfRange(array, index + 1, array.length);
      } catch (Exception e) {
        result = new int[] {};
      }
      return result;
    }

    private void recursion(int[] remain) {
      if (this.sum == this.target && this.path.size() == k) {
        this.result.add(new ArrayList<>(this.path));
      }
      if (this.sum > this.target || this.path.size() > k) {
        return;
      }
      for (int i = 0; i < remain.length; i++) {
        this.path.add(remain[i]);
        this.sum += remain[i];
        this.recursion(this.remain(remain, i));
        int remove = this.path.remove(this.path.size() - 1);
        this.sum -= remove;
      }
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
      this.init(IntStream.rangeClosed(1, 9).toArray(), n, k);
      this.recursion(this.data);
      return result;
    }
  }
}

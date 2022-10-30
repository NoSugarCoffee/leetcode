package com.dll.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Combinations {
  // 1. 递归
  class Solution {
    private List<List<Integer>> result = new ArrayList<>();

    private int k;
    private int[] data;

    private void init(int[] data, int k) {
      this.data = data;
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

    private void recursion(int[] remain, List<Integer> prevPath) {
      if (prevPath.size() == k) {
        result.add(prevPath);
        return;
      }
      for (int i = 0; i < remain.length; i++) {
        List<Integer> curPath = new ArrayList<>(prevPath);
        curPath.add(remain[i]);
        this.recursion(this.remain(remain, i), curPath);
      }
    }

    public List<List<Integer>> combine(int n, int k) {
      this.init(IntStream.rangeClosed(1, n).toArray(), k);
      this.recursion(this.data, Collections.emptyList());
      return result;
    }
  }

  // 2. 递归（回溯）
  class Solution2 {
    private List<List<Integer>> result = new ArrayList<>();
    // 遍历过程中的实时路径
    private List<Integer> path = new ArrayList<>();
    private int k;
    private int[] data;

    private void init(int[] data, int k) {
      this.data = data;
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
      if (this.path.size() == k) {
        result.add(new ArrayList<>(path));
        return;
      }
      for (int i = 0; i < remain.length; i++) {
        path.add(remain[i]);
        this.recursion(this.remain(remain, i));
        // 回溯
        path.remove(path.size() - 1);
      }
    }

    public List<List<Integer>> combine(int n, int k) {
      this.init(IntStream.rangeClosed(1, n).toArray(), k);
      this.recursion(this.data);
      return result;
    }
  }
}

package com.dll.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
  class Solution {
    private List<List<Integer>> result = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();
    private int sum;

    private int target;
    private int[] data;

    private void init(int[] data, int target) {
      this.data = data;
      this.target = target;
    }

    private int[] remain(int[] array, int index) {
      int[] result;
      try {
        result = Arrays.copyOfRange(array, index, array.length);
      } catch (Exception e) {
        result = new int[] {};
      }
      return result;
    }

    private void recursion(int[] array) {
      if (this.sum == this.target) {
        result.add(new ArrayList<>(this.path));
        return;
      }
      if (this.sum > this.target) {
        return;
      }
      for (int i = 0; i < array.length; i++) {
        this.path.add(array[i]);
        this.sum += array[i];
        this.recursion(this.remain(array, i));
        int remove = this.path.remove(this.path.size() - 1);
        this.sum -= remove;
      }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
      this.init(candidates, target);
      this.recursion(this.data);
      return result;
    }
  }
}

package com.dll.backtracing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsII {
  class Solution {
    private List<Integer> path = new ArrayList<>();
    private List<List<Integer>> result = new ArrayList<>();

    private void backTracing(int[] nums, int[] used, int start) {
      for (int i = start; i < nums.length; i++) {
        if (i >= 1 && nums[i] == nums[i - 1] && used[i - 1] == 0) {
          continue;
        }
        path.add(nums[i]);
        used[i] = 1;
        result.add(new ArrayList<>(path));
        backTracing(nums, used, i + 1);
        path.remove(path.size() - 1);
        used[i] = 0;
      }
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
      result.add(new ArrayList<>(path));
      int[] used = new int[nums.length];
      Arrays.sort(nums);
      backTracing(nums, used, 0);
      return result;
    }
  }
}

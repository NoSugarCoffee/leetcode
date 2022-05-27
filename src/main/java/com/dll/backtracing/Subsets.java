package com.dll.backtracing;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
    class Solution {
        private List<Integer> path = new ArrayList<>();
        private List<List<Integer>> result = new ArrayList<>();
        private void backTracing(int[] nums, int start) {
            if (start >= nums.length) {
                return ;
            }
            for (int i = start; i < nums.length; i++) {
                path.add(nums[i]);
                result.add(new ArrayList<>(path));
                backTracing(nums, i + 1);
                path.remove(path.size() -1);
            }
        }
        public List<List<Integer>> subsets(int[] nums) {
            result.add(new ArrayList<>(path));
            backTracing(nums, 0);
            return result;
        }
    }
}

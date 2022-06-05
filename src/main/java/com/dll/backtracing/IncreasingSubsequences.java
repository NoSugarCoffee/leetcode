package com.dll.backtracing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IncreasingSubsequences {
    class Solution {
        private final List<List<Integer>> result = new ArrayList<>();
        private final List<Integer> path = new ArrayList<>();

        private void backTracing(int[] nums, int startIndex) {
            Map<Integer, Integer> used = new HashMap();
            for (int i = startIndex; i < nums.length; i++) {
                if (used.containsKey(nums[i]) || (path.size() > 0) && path.get(path.size() - 1) > nums[i]) {
                    continue;
                }
                used.put(nums[i], 1);
                path.add(nums[i]);
                if (path.size() >= 2) {
                    result.add(new ArrayList<>(path));
                }
                backTracing(nums, i + 1);
                path.remove(path.size() - 1);
            }
        }

        public List<List<Integer>> findSubsequences(int[] nums) {
            backTracing(nums, 0);
            return result;
        }
    }
}

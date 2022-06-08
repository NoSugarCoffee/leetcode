package com.dll.backtracing;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    class Solution {
        private List<Integer> path = new ArrayList<>();
        private List<List<Integer>> result = new ArrayList<>();

        private void backTracing(int[] nums, boolean[] visited) {
            if (path.size() == nums.length) {
                result.add(new ArrayList<>(path));
                return;
            }
            for (int i = 0; i < nums.length; i++) {
                if (visited[i]) {
                    continue;
                }
                path.add(nums[i]);
                visited[i] = true;
                backTracing(nums, visited);
                path.remove(path.size() - 1);
                visited[i] = false;
            }
        }

        public List<List<Integer>> permute(int[] nums) {
            backTracing(nums, new boolean[nums.length]);
            return result;
        }
    }
}

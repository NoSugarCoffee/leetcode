package com.dll.backtracing;

import java.util.ArrayList;
import java.util.List;

public class CombinationSumIII {
    class Solution {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();

        int sum(List<Integer> path) {
            return path.stream().reduce(0, Integer::sum);
        }

        void backTracing(int k, int n, int startIndex) {
            int total = sum(path);
            if (total == n && path.size() == k) {
                result.add(new ArrayList<>(path));
                return;
            } else if (total > n || path.size() > k) {
                return;
            }
            for (int i = startIndex; i <= 9 - (k - path.size()) + 1; i++) {
                path.add(i);
                backTracing(k, n, startIndex + 1);
                path.remove(path.size() - 1);
            }
        }

        public List<List<Integer>> combinationSum3(int k, int n) {
            backTracing(k, n, 1);
            return result;
        }
    }
}

package com.dll.backtracing;

import java.util.ArrayList;
import java.util.List;

public class Combinations {
    class Solution {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();

        public void backTracing(int startIndex, int n, int k) {
            if (path.size() == k) {
                result.add(new ArrayList<>(path));
                return;
            }
            for (int i = startIndex; i <= n - (k - path.size() - 1); i++) {
                path.add(i);
                backTracing(i + 1, n, k);
                path.remove(path.size() - 1);
            }
        }

        public List<List<Integer>> combine(int n, int k) {
            backTracing(1, n, k);
            return result;
        }
    }
}

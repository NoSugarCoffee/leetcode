package com.dll.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII {
    class Solution {
        private List<Integer> path = new ArrayList<>();
        private int[] sorted_candidates;
        private int[] candidates;
        private int target;
        private List<List<Integer>> result = new ArrayList<>();
        private int sum = 0;

        private void init(int[] candidates, int target) {
            this.candidates = candidates;
            int[] candidatesCopy = Arrays.copyOf(candidates, candidates.length);
            Arrays.sort(candidatesCopy);
            this.sorted_candidates = candidatesCopy;
            this.target = target;
        }
        private int[] remain(int[] array, int index) {
            int[] result;
            try {
                result = Arrays.copyOfRange(array, index + 1, array.length);
            } catch (Exception e) {
                result = new int[]{};
            }
            return result;
        }
        private boolean ifSkipOnContinuesEqual(int[] remain, int index) {
            return index - 1 >= 0 && remain[index] == remain[index - 1];
        }
        private void recursion(int[] remain) {
            if (sum == this.target) {
                result.add(new ArrayList<>(path));
                return;
            } else if (sum > this.target) {
                return;
            }
            for (int i = 0; i < remain.length; i++) {
                if (this.ifSkipOnContinuesEqual(remain, i)) {
                    continue;
                }
                path.add(remain[i]);
                sum += remain[i];
                this.recursion(this.remain(remain, i));
                int val = path.remove(path.size() - 1);
                sum -= val;
            }
        }
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            this.init(candidates, target);
            this.recursion(this.sorted_candidates);
            return result;
        }
    }
}

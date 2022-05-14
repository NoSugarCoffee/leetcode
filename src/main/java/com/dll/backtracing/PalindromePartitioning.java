package com.dll.backtracing;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {
    class Solution {
        private List<String> path = new ArrayList<>();
        private List<List<String>> result = new ArrayList<>();

        public boolean isPalindrome(String text) {
            char[] letters = text.toCharArray();
            int i = 0;
            while (i < letters.length / 2) {
                if (letters[i] != letters[letters.length - 1 - i]) {
                    return false;
                }
                i++;
            }
            return true;
        }

        public void backTracing(String leftover) {
            if (leftover.isEmpty() && path.stream().allMatch(this::isPalindrome)) {
                result.add(new ArrayList<>(path));
                return;
            }
            for (int splitIndex = 1; splitIndex <= leftover.length(); splitIndex++) {
                String left = leftover.substring(0, splitIndex);
                String right = leftover.substring(splitIndex);
                path.add(left);
                backTracing(right);
                path.remove(path.size() - 1);
            }
        }

        public List<List<String>> partition(String s) {
            backTracing(s);
            return result;
        }
    }
}

package com.dll.array;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinimumSizeSubArraySum {
    class Solution {
        public int minSubArrayLen(int target, int[] nums) {
            int res = nums.length + 1;
            Deque<Integer> window = new ArrayDeque<>();
            int windowSum = 0;
            for (int i = 0; i < nums.length; i++) {
                if (windowSum < target) {
                    window.offer(nums[i]);
                    windowSum += nums[i];
                }
                while(windowSum >= target) {
                    if (window.size() < res) {
                        res = window.size();
                    }
                    int pop = window.remove();
                    windowSum -= pop;
                }
            }
            return res == nums.length + 1? 0: res;
        }
    }
}

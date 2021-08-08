package com.dll.array;

import java.util.TreeSet;

public class CircularArrayLoop {
    class Solution {
        private boolean isLoop(int[] nums, int index) {
            TreeSet<Integer> set = new TreeSet<>();
            int nextIndex = index;
            boolean flag = nums[nextIndex] > 0;
            while (true) {
                if (nums[nextIndex] > 0 != flag) {
                    return false;
                }
                set.add(nextIndex);
                nextIndex = (nextIndex + nums[nextIndex] + Math.abs(nums[nextIndex]) * nums.length) % nums.length;

                if (set.contains(nextIndex)) {
                    if (nextIndex != index) {
                        return false;
                    } else if (set.first().intValue() == set.last()) {
                        return false;
                    }
                    break;
                }
            }
            return true;
        }

        public boolean circularArrayLoop(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                System.out.println(isLoop(nums, i) + "" + i);
                if (isLoop(nums, i)) {
                    return true;
                }
            }
            return false;
        }
    }
}

package com.dll.array;

public class BinarySearch {
    class Solution {
        public int search(int[] nums, int target) {
            int leftIndex = 0;
            int rightIndex = nums.length - 1;
            int midIndex = (leftIndex + rightIndex) / 2;
            while (target != nums[midIndex] && leftIndex <= rightIndex) {
                if (nums[midIndex] > target) {
                    rightIndex = midIndex - 1;
                } else {
                    leftIndex = midIndex + 1;
                }
                midIndex = (leftIndex + rightIndex) / 2;
            }
            return leftIndex > rightIndex ? -1 : midIndex;
        }
    }
}

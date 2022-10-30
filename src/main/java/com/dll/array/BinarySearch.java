package com.dll.array;

public class BinarySearch {
  class Solution {
    public int search(int[] nums, int target) {
      int leftIndex = 0;
      int rightIndex = nums.length - 1;
      while (leftIndex <= rightIndex) {
        int midIndex = (leftIndex + rightIndex) / 2;
        if (target == nums[midIndex]) {
          return midIndex;
        } else if (nums[midIndex] > target) {
          rightIndex = midIndex - 1;
        } else {
          leftIndex = midIndex + 1;
        }
      }
      return -1;
    }
  }
}

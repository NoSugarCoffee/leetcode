package com.dll.array;

public class RemoveElement {
  class Solution {
    public int removeElement(int[] nums, int val) {
      int p1 = 0;
      int p2 = 0;
      for (int i = 0; i < nums.length; i++) {
        if (nums[i] != val) {
          nums[p1] = nums[p2];
          p1++;
        }
        p2++;
      }
      return p1;
    }
  }
}

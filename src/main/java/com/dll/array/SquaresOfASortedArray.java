package com.dll.array;

public class SquaresOfASortedArray {
  class Solution {
    public int[] sortedSquares(int[] nums) {
      int[] newNums = new int[nums.length];
      int p = newNums.length - 1;
      int p1 = 0;
      int p2 = nums.length - 1;
      for (int i = 0; i < nums.length; i++) {
        int p1Square = nums[p1] * nums[p1];
        int p2Square = nums[p2] * nums[p2];
        if (p1Square < p2Square) {
          newNums[p] = p2Square;
          p2--;
        } else {
          newNums[p] = p1Square;
          p1++;
        }
        p--;
      }
      return newNums;
    }
  }
}

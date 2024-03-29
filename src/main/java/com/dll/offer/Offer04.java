package com.dll.offer;

public class Offer04 {
  class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
      int rows;
      if (matrix == null || (rows = matrix.length) < 1) {
        return false;
      }
      int cols = matrix[0].length;
      int i = 0;
      int j = cols - 1;

      while (i < rows && j > -1) {
        int upperRightCorner = matrix[i][j];
        if (upperRightCorner == target) {
          return true;
        } else if (upperRightCorner > target) {
          j--;
        } else {
          i++;
        }
      }
      return false;
    }
  }
}

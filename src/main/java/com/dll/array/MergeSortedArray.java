package com.dll.array;

public class MergeSortedArray {

  class Solution {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
      int[] result = new int[m + n];
      int p1 = 0;
      int p2 = 0;
      int index = 0;

      while (p1 < m && p2 < n) {
        if (nums1[p1] <= nums2[p2]) {
          result[index] = nums1[p1];
          p1++;
        } else {
          result[index] = nums2[p2];
          p2++;
        }
        index++;
      }
      for (; p1 < m; p1++) {
        result[index++] = nums1[p1];
      }
      for (; p2 < n; p2++) {
        result[index++] = nums2[p2];
      }
      System.arraycopy(result, 0, nums1, 0, m + n);
    }
  }
}

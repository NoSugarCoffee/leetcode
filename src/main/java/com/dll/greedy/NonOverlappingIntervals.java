package com.dll.greedy;

import java.util.Arrays;
import java.util.Comparator;

public class NonOverlappingIntervals {

  class Solution {

    public int eraseOverlapIntervals(int[][] intervals) {
      if (intervals.length < 1) {
        return 0;
      }
      Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));
      int end = intervals[0][1];
      int counter = 0;
      for (int i = 1; i < intervals.length; i++) {
        if (intervals[i][0] < end) {
          counter++;
        } else {
          end = intervals[i][1];
        }
      }
      return counter;
    }
  }
}

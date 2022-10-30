package com.dll.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MinimumNumberOfArrowsToBurstBalloons {

  class Solution {

    public int findMinArrowShots(int[][] points) {
      Arrays.sort(points, Comparator.comparingInt(o -> o[1]));
      List<int[]> balloons = new ArrayList<>(Arrays.asList(points));
      int arrows = 0;
      while (!balloons.isEmpty()) {
        int[] p1 = balloons.get(0);
        balloons.remove(0);
        for (int i = 0; i < balloons.size(); i++) {
          if ((balloons.get(i)[0] >= p1[0] && balloons.get(i)[0] <= p1[1])
              || (balloons.get(i)[1] >= p1[0] && balloons.get(i)[1] <= p1[1])
              || (balloons.get(i)[0] < p1[0] && balloons.get(i)[1] > p1[1])) {
            int start = balloons.get(i)[0] > p1[0] ? balloons.get(i)[0] : p1[0];
            int end = balloons.get(i)[1] < p1[1] ? balloons.get(i)[1] : p1[1];
            p1 = new int[] {start, end};
            balloons.remove(i--);
          }
        }
        arrows++;
      }
      return arrows;
    }
  }
}

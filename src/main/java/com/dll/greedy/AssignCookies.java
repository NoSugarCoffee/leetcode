package com.dll.greedy;

import java.util.Arrays;

public class AssignCookies {

  class Solution {

    public int findContentChildren(int[] g, int[] s) {
      // rename
      int[] appetites = g;
      int[] cookies = s;

      // ascending
      Arrays.sort(cookies);
      Arrays.sort(appetites);

      int counter = 0;

      for (int cookie : cookies) {
        if (counter < appetites.length && cookie >= appetites[counter]) {
          counter++;
        }
      }
      return counter;
    }
  }
}

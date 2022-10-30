package com.dll.string;

public class ReverseString {
  class Solution {
    private void swap(char[] s, int p, int q) {
      char temp = s[p];
      s[p] = s[q];
      s[q] = temp;
    }

    public void reverseString(char[] s) {
      if (s.length <= 1) {
        return;
      }
      for (int i = 0; i < s.length / 2; i++) {
        int p = i;
        int q = s.length - 1 - i;
        swap(s, p, q);
      }
    }
  }
}

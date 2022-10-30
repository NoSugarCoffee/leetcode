package com.dll.string;

import java.util.ArrayDeque;
import java.util.Deque;

public class StringCompression {
  class Solution {
    public int[] splitNum(int num) {
      Deque<Integer> result = new ArrayDeque<>();
      while (num != 0) {
        result.push(num % 10);
        num /= 10;
      }
      return result.stream().mapToInt(i -> i).toArray();
    }

    public int compress(char[] chars) {
      if (chars.length <= 1) {
        return chars.length;
      }
      int writeIndex = 0;
      char pre = chars[0];
      int count = 1;

      for (int i = 1; i < chars.length; i++) {
        if (chars[i] == pre) {
          count++;
        } else {
          writeIndex = updateChars(chars, writeIndex, pre, count);
          count = 1;
        }
        pre = chars[i];
      }
      writeIndex = updateChars(chars, writeIndex, pre, count);
      return writeIndex;
    }

    private int updateChars(char[] chars, int writeIndex, char val, int count) {
      chars[writeIndex] = val;
      writeIndex++;
      if (count != 1) {
        int[] nums = splitNum(count);
        int l = 0;
        while (l < nums.length) {
          chars[writeIndex++] = Character.forDigit(nums[l], 10);
          l++;
        }
      }
      return writeIndex;
    }
  }
}

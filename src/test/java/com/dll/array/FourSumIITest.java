package com.dll.array;

import org.junit.Test;

public class FourSumIITest {
  @Test
  public void test() {
    new FourSumII().new Solution()
        .fourSumCount(new int[] {1, 2}, new int[] {-2, -1}, new int[] {-1, 2}, new int[] {0, 2});
  }
}

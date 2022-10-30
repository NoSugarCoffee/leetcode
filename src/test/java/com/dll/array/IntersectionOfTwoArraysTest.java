package com.dll.array;

import org.junit.Assert;
import org.junit.Test;

public class IntersectionOfTwoArraysTest {
  @Test
  public void test() {
    Assert.assertArrayEquals(
        new int[] {2},
        new IntersectionOfTwoArrays().new Solution()
            .intersection(new int[] {1, 2, 2, 1}, new int[] {2, 2}));
  }
}

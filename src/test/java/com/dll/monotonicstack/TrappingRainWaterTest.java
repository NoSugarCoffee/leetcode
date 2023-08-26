package com.dll.monotonicstack;

import org.junit.Assert;
import org.junit.Test;

public class TrappingRainWaterTest {
  @Test
  public void test() {
    Assert.assertEquals(0, new TrappingRainWater().new Solution().trap(new int[] {1}));
    Assert.assertEquals(
        9, new TrappingRainWater().new Solution().trap(new int[] {4, 2, 0, 3, 2, 5}));
  }
}

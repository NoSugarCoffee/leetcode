package com.dll.greedy;

import org.junit.Assert;
import org.junit.Test;

public class CandyTest {

  @Test
  public void test() {
    // [[0,1,2,5,3,2,7]
    int[] ratings = new int[] {0, 1, 2, 5, 3, 2, 7};
    Assert.assertEquals(15, new Candy().new Solution().candy(ratings));

    ratings = new int[] {1, 2, 2};
    Assert.assertEquals(4, new Candy().new Solution().candy(ratings));
  }
}

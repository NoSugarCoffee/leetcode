package swardmeansoffer;

import org.junit.Assert;
import org.junit.Test;

public class Offer04Test {
  @Test
  public void test() {
    Assert.assertTrue(new Offer04().new Solution()
        .findNumberIn2DArray(new int[][]{{-5}}, -5));
  }
}

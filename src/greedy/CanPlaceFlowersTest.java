package greedy;

import org.junit.Assert;
import org.junit.Test;

public class CanPlaceFlowersTest {

  @Test
  public void test() {
    Assert.assertEquals(true,
        new CanPlaceFlowers().new Solution().canPlaceFlowers(new int[]{1, 0, 0, 0, 1, 0, 0}, 2));
  }
}
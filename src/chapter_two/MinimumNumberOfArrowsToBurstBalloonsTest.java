package chapter_two;

import org.junit.Assert;
import org.junit.Test;

public class MinimumNumberOfArrowsToBurstBalloonsTest {
  @Test
  public void test(){
    int[][] points = new int[][]{{10,16},{2,8},{1,6},{7,12}};
//    Assert.assertEquals(2, new MinimumNumberOfArrowsToBurstBalloons().new Solution().findMinArrowShots(points));
//
//    points = new int[][]{{1,2},{3,4},{5,6},{7,8}};
//    Assert.assertEquals(4, new MinimumNumberOfArrowsToBurstBalloons().new Solution().findMinArrowShots(points));

    points = new int[][]{{3,8},{7,12},{9,10},{0,6}};
    Assert.assertEquals(2, new MinimumNumberOfArrowsToBurstBalloons().new Solution().findMinArrowShots(points));
  }
}
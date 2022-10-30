package com.dll.offer;

import org.junit.Assert;
import org.junit.Test;

public class Offer05Test {

  @Test
  public void test() {
    Assert.assertEquals(
        "We%20are%20happy.", new Offer05().new Solution().replaceSpace("We are happy."));
    Assert.assertEquals("", new Offer05().new Solution().replaceSpace(null));
  }
}

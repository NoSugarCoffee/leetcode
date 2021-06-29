package com.dll.greedy;

import org.junit.Assert;
import org.junit.Test;

public class AssignCookiesTest {

  @Test
  public void test() {
    AssignCookies.Solution solution = new AssignCookies().new Solution();
    int[] appetites = new int[]{1, 2};
    int[] cookies = new int[]{1, 2, 3};
    Assert.assertEquals(2, solution.findContentChildren(appetites, cookies));
  }
}
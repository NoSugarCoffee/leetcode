package com.dll.backtracing;

import org.junit.Test;

public class RestoreIpAddressesTest {
  @Test
  public void test() {
    RestoreIpAddresses.Solution solution = new RestoreIpAddresses().new Solution();
    System.out.println(solution.restoreIpAddresses("172162541"));
  }
}

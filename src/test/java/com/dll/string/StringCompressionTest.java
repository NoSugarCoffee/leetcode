package com.dll.string;

import org.junit.Assert;
import org.junit.Test;

public class StringCompressionTest {
  @Test
  public void testSplitNum() {
    Assert.assertArrayEquals(new int[] {3}, new StringCompression().new Solution().splitNum(3));
    Assert.assertArrayEquals(new int[] {3, 1}, new StringCompression().new Solution().splitNum(31));
  }

  @Test
  public void testCompress() {
    Assert.assertEquals(
        6,
        new StringCompression().new Solution()
            .compress(new char[] {'a', 'a', 'b', 'b', 'c', 'c', 'c'}));

    Assert.assertEquals(
        4,
        new StringCompression().new Solution()
            .compress(
                new char[] {'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}));
  }
}

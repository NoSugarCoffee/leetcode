package com.dll.string;

import org.junit.Assert;
import org.junit.Test;

public class FindCommonCharactersTest {
  @Test
  public void test() {
    Assert.assertArrayEquals(
        new String[] {"e", "l", "l"},
        new FindCommonCharacters().new Solution()
            .commonChars(new String[] {"bella", "label", "roller"})
            .toArray());
  }
}

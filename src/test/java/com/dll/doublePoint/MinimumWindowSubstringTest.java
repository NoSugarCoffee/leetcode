package com.dll.doublePoint;

import com.dll.string.MinimumWindowSubstring;
import org.junit.Assert;
import org.junit.Test;

public class MinimumWindowSubstringTest {

  @Test
  public void test() {
    MinimumWindowSubstring.Solution solution = new MinimumWindowSubstring().new Solution();
    Assert.assertEquals("{A=1, B=2}", solution.calc_freq("ABB").toString());

    Assert.assertEquals(true, solution.check(solution.calc_freq("ABB"),
        solution.calc_freq("AB")));

    Assert.assertEquals(false, solution.check(solution.calc_freq("AB"),
        solution.calc_freq("ABB")));
    Assert.assertEquals("BANC", solution.minWindow("ADOBECODEBANC", "ABC"));
    Assert.assertEquals("a", solution.minWindow("a", "a"));
  }
}
package com.dll.offer;

import com.dll.offer.Offer07.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Offer07Test {
  @Test
  public void testSplitInorder() {
    Assert.assertArrayEquals(new int[][]{{1},{3}},
        new Offer07().new Solution().splitInorder(new int[]{1,2,3}, 2));

    Assert.assertArrayEquals(new int[][]{{},{}},
        new Offer07().new Solution().splitInorder(new int[]{1,2,3}, 0));

    Assert.assertArrayEquals(new int[][]{{},{2, 3}},
        new Offer07().new Solution().splitInorder(new int[]{1,2,3}, 1));

  }

  @Test
  public void testBuildTree() {
    TreeNode node = new Offer07().new Solution()
        .buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7});

    List<List<Integer>> lists = new Offer07().new Solution().levelOrder(node);
    List<List<Integer>> expect = new ArrayList<>();
    expect.add(Arrays.asList(3));
    expect.add(Arrays.asList(9, 20));
    expect.add(Arrays.asList(15, 7));
    Assert.assertEquals(expect, lists);
  }
}

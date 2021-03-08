package chapter_five;

import org.junit.Assert;
import org.junit.Test;

public class QuickSortTest {

  @Test
  public void sort() {
    int[] arr = new int[]{1,2,5,6,0,-1};
    new QuickSort().sort(arr);
    Assert.assertArrayEquals(new int[]{-1, 0, 1, 2, 5, 6}, arr);
    arr = new int[]{1,0,6,1,2,7,9,3,4,5,1,0,8};
    new QuickSort().sort(arr);
    Assert.assertArrayEquals(new int[]{0, 0, 1, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9}, arr);
  }
}
package sort;

import org.junit.Assert;
import org.junit.Test;

public class SortTest {

  @Test
  public void quickSort() {
    int[] arr = new int[]{1, 2, 5, 6, 0, -1};
    new QuickSort().sort(arr);
    Assert.assertArrayEquals(new int[]{-1, 0, 1, 2, 5, 6}, arr);
    arr = new int[]{1, 0, 6, 1, 2, 7, 9, 3, 4, 5, 1, 0, 8};
    new QuickSort().sort(arr);
    Assert.assertArrayEquals(new int[]{0, 0, 1, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9}, arr);
  }

  @Test
  public void bubbleSort() {
    int[] arr = new int[]{1, 2, 5, 6, 0, -1};
    new BubbleSort().sort(arr);
    Assert.assertArrayEquals(new int[]{-1, 0, 1, 2, 5, 6}, arr);
    arr = new int[]{1,0,6,1,2,7,9,3,4,5,1,0,8};
    new BubbleSort().sort(arr);
    Assert.assertArrayEquals(new int[]{0, 0, 1, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9}, arr);
  }

  @Test
  public void mergeSort() {
    int[] result = new MergeSort().mergeSortedArray(new int[]{1, 2, 5}, new int[]{2, 3, 7});
    Assert.assertArrayEquals(new int[]{1, 2, 2, 3, 5, 7}, result);

    int[] arr = new int[]{1, 2, 5, 6, 0, -1};
    new MergeSort().sort(arr);
    Assert.assertArrayEquals(new int[]{-1, 0, 1, 2, 5, 6}, arr);
    arr = new int[]{1,0,6,1,2,7,9,3,4,5,1,0,8};
    new MergeSort().sort(arr);
    Assert.assertArrayEquals(new int[]{0, 0, 1, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9}, arr);
  }
}
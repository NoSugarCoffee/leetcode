package sort;

import java.util.Arrays;

public class MergeSort {

  /**
   * 用于合并两个升序的数组
   */
  public int[] mergeSortedArray(int[] arr1, int[] arr2) {
    int[] merged = new int[arr1.length + arr2.length];
    int i = 0;
    int j = 0;
    int k = 0;
    while (i < arr1.length && j < arr2.length) {
      merged[k++] = arr1[i] > arr2[j] ? arr2[j++] : arr1[i++];
    }
    while (i < arr1.length) {
      merged[k++] = arr1[i++];
    }
    while (j < arr2.length) {
      merged[k++] = arr2[j++];
    }
    return merged;
  }

  // [start, mid)
  // [mid, end)
  public int[] merge(int[] arr, int start, int mid, int end) {
    int[] t1 = Arrays.copyOfRange(arr, start, mid);
    int[] t2 = Arrays.copyOfRange(arr, mid, end);
    int[] result = this.mergeSortedArray(t1, t2);
    for (int i = 0; i < result.length; i++) {
      arr[start + i] = result[i];
    }
    return result;
  }

  public void splitAndMerge(int[] arr, int start, int end) {
    if (end - start <= 1) {
      return;
    }
    int mid = start + (end - start) / 2;
    this.splitAndMerge(arr, start, mid);
    this.splitAndMerge(arr, mid, end);
    this.merge(arr, start, mid, end);
  }

  public void sort(int[] arr) {
    this.splitAndMerge(arr, 0, arr.length);
  }
}

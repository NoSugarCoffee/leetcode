package sort;

public class QuickSort {

  private void swap(int[] arr, int first, int second) {
    int temp = arr[first];
    arr[first] = arr[second];
    arr[second] = temp;
  }

  private void sort_recursion(int[] arr, int start, int end) {
    if (start >= end) {
      return;
    }
    int pivot = arr[start];
    int l = start, r = end;
    while (l < r) {
      while (arr[r] >= pivot && l < r) {
        r--;
      }
      while (arr[l] <= pivot && l < r) {
        l++;
      }
      this.swap(arr, l, r);
    }
    this.swap(arr, start, l);
    this.sort_recursion(arr, start, l - 1);
    this.sort_recursion(arr, l + 1, end);
  }

  public void sort(int[] arr) {
    this.sort_recursion(arr, 0, arr.length - 1);
  }
}

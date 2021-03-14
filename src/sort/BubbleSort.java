package sort;

public class BubbleSort {

  private void swap(int[] arr, int first, int end) {
    int temp = arr[first];
    arr[first] = arr[end];
    arr[end] = temp;
  }

  void sort(int[] arr) {
    boolean swapped = false;
    // n 个数字只需要 n-1 次排序
    for (int i = 0; i < arr.length - 1; i++) {
      // 从小到大排序的话, 每次末尾都会选出最大的
      for (int j = 0; j < arr.length - i - 1; j++) {
        if (arr[j + 1] < arr[j]) {
          this.swap(arr, j, j + 1);
          swapped = true;
        }
      }
      if (!swapped) {
        break;
      }
    }
  }
}

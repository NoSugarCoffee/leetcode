---
weight: 2
title: "排序"
---
# 排序

## 冒泡排序

```java
// ../../../../../src/main/java/com/dll/sort/BubbleSort.java

package com.dll.sort;

public class BubbleSort {

  private void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public void sort(int[] arr) {
    boolean swapped = false;
    for (int i = 0; i < arr.length - 1; i++) {
      for (int j = 0; j < arr.length - i - 1; j++) {
        if (arr[j] > arr[j + 1]) {
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

```
## 选择排序
```java
// ../../../../../src/main/java/com/dll/sort/SelectionSort.java

package com.dll.sort;

public class SelectionSort {
    private void swap(int[] arr, int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }

    public void sort(int[] arr) {
        for (int i=0; i < arr.length-1; i++) {
            int minIndex = i;
            for (int j=i+1; j < arr.length; j++) {
                if(arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            this.swap(arr, i, minIndex);
        }
    }
}

```

## 快速排序

```java
// ../../../../../src/main/java/com/dll/sort/QuickSort.java

package com.dll.sort;

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

```

## 归并排序

```java
// ../../../../../src/main/java/com/dll/sort/MergeSort.java

package com.dll.sort;

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

```

## 插入排序

```java
// ../../../../../src/main/java/com/dll/sort/InsertSort.java

package com.dll.sort;

/**
 * 插入排序将数组分为两个部分:有序部分和待排序部分
 * 待排序部分中的每个元素和有序部分的每个元素做比较，并插入到合适的位置
 * 第一个元素本身就是有序的，不需要排序
 * 从第二个元素开始，每次都比较
 */
public class InsertSort {

  public void sort(int[] arr) {
    for (int i = 1; i < arr.length; i++) {
     for (int j = 0; j < i; j++) {
       if (arr[i] < arr[j]) {
         int temp = arr[i];
         for (int z = i - 1; z >= j; z--) {
           arr[z + 1] = arr[z];
         }
         arr[j] = temp;
       }
     }
    }
  }
}

```

## [215. Kth Largest Element in an Array](https://leetcode-cn.com/problems/kth-largest-element-in-an-array/)

## 两数的交换
排序过程中经常会使用到两个数值的交换，常用的，我们会使用 temp variable 作为媒介，如：

```
  t = a;
  a = b;
  b = t;
```

有没有不耗费空间，或者更快的方式呢？

1. 数值运算交换两个元素
    ```
      a = a + b   // a1 = a + b
      b = a - b   // b = a1 - b -> b = a
      a = a - b   // a = a1 - b -> a = a1 - a -> a = b
    ```

1. 异或运算交换两个元素
    ```
      a = a ^ b   // a1 = a ^ b
      b = a ^ b   // b = a1 ^ b -> a ^ b ^ b = a
      a = a ^ b   // a = a1 ^ b -> a ^ b ^ a = b
    ```



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

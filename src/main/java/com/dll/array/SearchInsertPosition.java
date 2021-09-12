package com.dll.array;

public class SearchInsertPosition {
    class Solution {
        private int binarySearch(int[] array, int target) {
            int left = 0;
            int right = array.length - 1;
            int mid = 0;
            while(left <= right) {
                mid = left + (right - left) / 2;
                if (array[mid] < target) {
                    left = mid + 1;
                } else if (array[mid] > target) {
                    right =  mid - 1;
                } else {
                    break;
                }
            }
            return array[mid] >= target ? mid : mid + 1;
        }
        public int searchInsert(int[] nums, int target) {
            return binarySearch(nums, target);
        }
    }
}

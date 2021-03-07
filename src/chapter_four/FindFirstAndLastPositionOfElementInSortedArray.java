package chapter_four;

public class FindFirstAndLastPositionOfElementInSortedArray {
  class Solution {
    public int[] searchRange(int[] nums, int target) {
      int l = 0;
      int r = nums.length - 1;
      int res1 = -1;
      while(l <= r) {
        int mid = l + (r - l) / 2;
        if (nums[mid] < target) {
          l = mid + 1;
        } else {
          r = mid - 1;
          if (nums[mid] == target) {
            res1 = mid;
          }
        }
      }
      l = 0;
      r = nums.length - 1;
      int res2 = -1;
      while(l <= r) {
        int mid = l + (r - l) / 2;
        if (nums[mid] > target) {
          r = mid - 1;
        } else {
          l = mid + 1;
          if (nums[mid] == target) {
            res2 = mid;
          }
        }
      }
      return new int[] {res1, res2};
    }
  }
}

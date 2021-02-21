package chapter_three;

public class TwoSumIIInputArrayIsSorted {
  class Solution {
    public int[] twoSum(int[] numbers, int target) {
      int ps = 0;
      int pe = numbers.length - 1;
      while( ps < pe) {
        if (numbers[ps] + numbers[pe] == target) {
          return new int[]{ps+1, pe+1};
        } else if (numbers[ps] + numbers[pe] > target){
          pe -- ;
        } else {
          ps ++;
        }
      }
      return new int[]{-1,-1};
    }
  }
}

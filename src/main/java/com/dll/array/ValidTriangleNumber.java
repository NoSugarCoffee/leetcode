package com.dll.array;

import java.util.Arrays;

public class ValidTriangleNumber {
    class Solution {

        public int triangleNumber(int[] nums) {
            int[] sortedNums = Arrays.stream(nums).sorted().toArray();
            int result = 0;
            for (int i = 0; i < sortedNums.length; i++) {
                for (int j = i + 1; j < sortedNums.length; j++) {
                    for (int z = j + 1; z < sortedNums.length; z++) {
                        if (sortedNums[i] + sortedNums[j] > sortedNums[z]) {
                            result++;
                        }
                    }
                }
            }
            return result;
        }
    }
}

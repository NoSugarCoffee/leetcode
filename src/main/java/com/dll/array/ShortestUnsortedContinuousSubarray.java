package com.dll.array;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ShortestUnsortedContinuousSubarray {
    class Solution {
        public int findUnsortedSubarray(int[] nums) {
            int[] sortedNums = Arrays.stream(nums).sorted().toArray();
            int first = IntStream.range(0, sortedNums.length)
                    .filter(index -> sortedNums[index] != nums[index])
                    .findFirst().orElse(0);
            int end = IntStream.range(0, sortedNums.length).map(index -> sortedNums.length - 1 - index)
                    .filter( index -> sortedNums[index] != nums[index])
                    .findFirst().orElse(-1);
            return end - first + 1;
        }
    }
}

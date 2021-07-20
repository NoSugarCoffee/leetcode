package com.dll.array;

import java.util.*;

public class ThreeSum {
    // 1. 双重循环 + hash 法
    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                map.put(nums[i], i);
            }
            Set<List<Integer>> set = new HashSet<>();
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    int z = -nums[i] - nums[j];
                    if (map.containsKey(z)
                            && map.get(z) != i
                            && map.get(z) != j) {
                        Integer[] array = new Integer[]{nums[i], nums[j], z};
                        Arrays.sort(array);
                        set.add(Arrays.asList(array));
                    }
                }
            }
            return new ArrayList<>(set);
        }
    }
    // 2.
}

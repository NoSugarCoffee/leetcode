package com.dll.array;

import java.util.*;

public class FourSum {
    // 三重循坏 + hash
    class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                map.put(nums[i], i);
            }
            Set<List<Integer>> set = new HashSet<>();
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    for (int k = j + 1; k < nums.length; k++) {
                        int z = target - nums[i] - nums[j] - nums[k];
                        if (map.containsKey(z)
                                && map.get(z) != i
                                && map.get(z) != j
                                && map.get(z) != k) {
                            Integer[] array = new Integer[]{nums[i], nums[j], nums[k], z};
                            Arrays.sort(array);
                            set.add(Arrays.asList(array));
                        }
                    }
                }
            }
            return new ArrayList<>(set);
        }
    }
}

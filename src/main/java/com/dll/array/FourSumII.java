package com.dll.array;

import java.util.HashMap;
import java.util.Map;

public class FourSumII {

    class Solution {
        public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
            Map<Integer, Integer> m1 = new HashMap<>();
            Map<Integer, Integer> m2 = new HashMap<>();

            for (int n1 : nums1) {
                for (int n2 : nums2) {
                    int sum = n1 + n2;
                    m1.compute(sum, (t, u) -> m1.getOrDefault(sum, 0) + 1);
                }
            }

            for (int n3 : nums3) {
                for (int n4 : nums4) {
                    int sum = n3 + n4;
                    m2.compute(sum, (t, u) -> m2.getOrDefault(sum, 0) + 1);
                }
            }
            int[] result = {0};
            m1.forEach((k1, v1) -> {
                m2.forEach((k2, v2) -> {
                    if (k1 + k2 == 0) {
                        result[0] += v1 * v2;
                    }
                });
            });
            return result[0];
        }
    }
}

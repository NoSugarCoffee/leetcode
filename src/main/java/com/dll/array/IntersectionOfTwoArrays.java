package com.dll.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntersectionOfTwoArrays {
    class Solution {
        public int[] intersection(int[] nums1, int[] nums2) {
            int[] n1 = Arrays.stream(nums1).sorted().distinct().toArray();
            int[] n2 = Arrays.stream(nums2).sorted().distinct().toArray();
            List<Integer> result = new ArrayList<>();
            int p1 = 0;
            int p2 = 0;
            while(p1 < n1.length && p2 < n2.length) {
                if (n1[p1] == n2[p2]) {
                    result.add(n1[p1]);
                    p1++;
                    p2++;
                } else if (n1[p1] > n2[p2]) {
                    p2++;
                } else {
                    p1++;
                }
            }
            return result.stream().mapToInt( v -> v).toArray();
        }
    }
}

package com.dll.array;

import java.util.*;
import java.util.stream.IntStream;

public class TheKWeakestRowsInAMatrix {
    class Solution {
        public int[] kWeakestRows(int[][] mat, int k) {
            Map<Integer, Integer> map = new HashMap<>();
            IntStream.range(0, mat.length).forEach(index -> map.put(index, Arrays.stream(mat[index]).sum()));
            return map.entrySet().stream()
                    .sorted(Comparator.comparing(Map.Entry<Integer, Integer>::getValue)
                            .thenComparing(Map.Entry::getKey))
                    .limit(k)
                    .mapToInt(Map.Entry::getKey).toArray();
        }
    }
}

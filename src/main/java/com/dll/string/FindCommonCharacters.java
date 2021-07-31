package com.dll.string;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FindCommonCharacters {
    class Solution {
        private Map<String, Integer> countWord(String word) {
            return Arrays.stream(word.split("")).
                    collect(Collectors.toMap((w) -> w, (w) -> 1, (oldValue, newValue) -> oldValue + 1));
        }

        public List<String> commonChars(String[] words) {
            List<String> result = new ArrayList<>();
            List<Map<String, Integer>> info =
                    Stream.of(words).map(this::countWord).collect(Collectors.toList());
            if (info.size() == 0) {
                return new ArrayList<>();
            }
            Map<String, Integer> firstMap = info.get(0);
            for (String c : firstMap.keySet()) {
                int commonCount = firstMap.get(c);
                for (int i = 1; i < info.size(); i++) {
                    int val = info.get(i).getOrDefault(c, 0);
                    if (val < commonCount) {
                        commonCount = val;
                    }

                }
                result.addAll(IntStream.range(0, commonCount).mapToObj(v -> c)
                        .collect(Collectors.toList()));
            }
            return result;
        }
    }
}

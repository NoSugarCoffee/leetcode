package com.dll.backtracing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsOfAPhoneNumber {
    class Solution {
        private final Map<Integer, Character[]> numAndLetters = mappingNumAndLetters();
        private final List<Character> path = new ArrayList<>();
        private final List<String> result = new ArrayList<>();

        Map<Integer, Character[]> mappingNumAndLetters() {
            Map<Integer, Character[]> map = new HashMap<>();
            char ch = 'a';
            for (int i = 2; i <= 9; i++) {
                int loop = (i == 7 || i == 9) ? 4 : 3;
                List<Character> letters = new ArrayList<>();
                while (ch <= 'z') {
                    loop--;
                    letters.add(ch);
                    ch++;
                    if (loop <= 0) {
                        map.put(i, letters.toArray(new Character[0]));
                        break;
                    }
                }

            }
            return map;
        }

        public void backTracing(String digits, int index) {
            if (path.size() >= digits.length()) {
                if (path.size() == digits.length()) {
                    StringBuilder sb = new StringBuilder();
                    path.forEach(sb::append);
                    // edge case: digits = ""
                    if (!sb.toString().isEmpty()) {
                        result.add(sb.toString());
                    }
                }
                return;
            }
            Character[] candidates = numAndLetters.get(Character.getNumericValue(digits.charAt(index)));
            for (char candidate : candidates) {
                path.add(candidate);
                backTracing(digits, index + 1);
                path.remove(path.size() - 1);
            }
        }

        public List<String> letterCombinations(String digits) {
            backTracing(digits, 0);
            return result;
        }
    }
}

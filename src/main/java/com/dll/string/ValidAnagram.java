package com.dll.string;

import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {
    class Solution {
        private Map<Character, Integer> toCharMap(String s) {
            Map<Character, Integer> map = new HashMap<>();
            char[] chars = s.toCharArray();
            for (char c : chars) {
                int val = map.getOrDefault(c, 0);
                map.put(c, val + 1);
            }
            return map;
        }

        public boolean isAnagram(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }
            return this.toCharMap(s).equals(this.toCharMap(t));
        }
    }
}

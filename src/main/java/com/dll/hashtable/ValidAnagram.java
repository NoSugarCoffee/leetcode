package com.dll.hashtable;

import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {
    class Solution {
        private Map<Character, Integer> countChars(String s) {
            Map<Character, Integer>  map = new HashMap<>();
            char[] chars = s.toCharArray();
            for (char c: chars) {
                int val = map.getOrDefault(c, 0);
                map.put(c, val + 1);
            }
            return map;
        }
        public boolean isAnagram(String s, String t) {
            return this.countChars(s).equals(this.countChars(t));
        }
    }
}

package doublePoint;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class MinimumWindowSubstring {

  class Solution {

    public Map<Character, Integer> calc_freq(String s) {
      Map<Character, Integer> freq = new HashMap<>();
      for (int i = 0; i < s.length(); i++) {
        freq.put(s.charAt(i), freq.getOrDefault(s.charAt(i), 0) + 1);
      }
      return freq;
    }

    public boolean check(Map<Character, Integer> window_freq, Map<Character, Integer> need_freq) {
      Iterator<Entry<Character, Integer>> iterator = need_freq.entrySet().iterator();
      while (iterator.hasNext()) {
        Entry<Character, Integer> next = iterator.next();
        Character key = next.getKey();
        if (!(window_freq.containsKey(key) && window_freq.get(key) >= next.getValue())) {
          return false;
        }
      }
      return true;
    }

    public String minWindow(String s, String t) {
      String ori = s;
      String need = t;
      int l = 0;
      int r = 0;
      String result = "";
      Map<Character, Integer> needFreq = calc_freq(need);
      Map<Character, Integer> window_frep = new HashMap<>();
      int endIndex = ori.length() - 1;
      while (r <= endIndex) {
        window_frep.put(ori.charAt(r), window_frep.getOrDefault(ori.charAt(r), 0) + 1);
        while (check(window_frep, needFreq) && l <= r) {
          if ("".equals(result)) {
            result = ori.substring(l, r + 1);
          } else {
            result = ori.substring(l, r + 1).length() < result.length() ? ori.substring(l, r + 1)
                : result;
          }
          window_frep.put(ori.charAt(l), window_frep.getOrDefault(ori.charAt(l), 0) - 1);
          l++;
        }
        r++;
      }
      return result;
    }
  }
}

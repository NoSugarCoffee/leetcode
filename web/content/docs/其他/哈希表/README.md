---
weight: 10
title: "哈希表"
---

# 哈希表
## 242. 有效的字母异位词
[leetcode](https://leetcode-cn.com/problems/valid-anagram/)
- 分别用 map 存储两个字符串的字符词频

```java
// ../../../../../src/main/java/com/dll/hashtable/ValidAnagram.java

package com.dll.hashtable;

import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {
    class Solution {
        private Map<Character, Integer> toCharMap(String s) {
            Map<Character, Integer>  map = new HashMap<>();
            char[] chars = s.toCharArray();
            for (char c: chars) {
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

```


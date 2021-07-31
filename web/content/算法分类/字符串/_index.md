---
weight: 2
title: "字符串"
---

## 哈希表
### 有效的字母异位词 
[242. leetcode](https://leetcode-cn.com/problems/valid-anagram/)

```java
// ../../../../src/main/java/com/dll/string/ValidAnagram.java

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

```
## 双指针
### 反转字符串 
[leetcode. 344](https://leetcode-cn.com/problems/reverse-string/)

```java
// ../../../../src/main/java/com/dll/string/ReverseString.java

package com.dll.string;

public class ReverseString {
    class Solution {
        private void swap(char[] s, int p, int q) {
            char temp = s[p];
            s[p] = s[q];
            s[q] = temp;
        }
        public void reverseString(char[] s) {
            if (s.length <= 1) {
                return;
            }
            for (int i = 0; i < s.length / 2; i++) {
                int p = i;
                int q = s.length -1 - i;
                swap(s, p, q);
            }
        }
    }
}

```

## 查找常用字符
[1002. leetcode](https://leetcode-cn.com/problems/find-common-characters/)
```java
// ../../../../src/main/java/com/dll/string/FindCommonCharacters.java

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

```

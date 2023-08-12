---
weight: 2
title: "字符串"
---

## 有效的字母异位词 
[242. leetcode](https://leetcode-cn.com/problems/valid-anagram/)

**哈希**

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

## 查找常用字符
[1002. leetcode](https://leetcode-cn.com/problems/find-common-characters/)

**哈希**
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

## 反转字符串 
[344. leetcode](https://leetcode-cn.com/problems/reverse-string/)

**双指针**

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

## 最小覆盖子串 
[76. leetcode](https://leetcode-cn.com/problems/minimum-window-substring/)

**滑动窗口**

原字符 ori（s），以及需要包含的字符串 need（t），[l,r) 代表当前窗口，r 右滑直至满足条件，此时通过 l 右滑，可能找到最优解，重复直到结束。
    
```java
// ../../../../src/main/java/com/dll/string/MinimumWindowSubstring.java

package com.dll.string;

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

```


## 学生出勤记录 I
[551. leetcode](https://leetcode-cn.com/problems/student-attendance-record-i/)

```java
// ../../../../src/main/java/com/dll/string/StudentAttendanceRecordI.java

package com.dll.string;

public class StudentAttendanceRecordI {
    class Solution {
        private char charAt(String s, int index) {
            char res = '\0';
            try {
                res = s.charAt(index);
            } catch (IndexOutOfBoundsException ignored) {
            }
            return res;
        }

        public boolean checkRecord(String s) {
            int absentCount = 0;
            boolean continuous3DaysLate = false;
            for (int i = 0; i < s.length(); i++) {
                if (charAt(s, i) == 'A') {
                    absentCount += 1;
                } else if (
                        charAt(s, i) == 'L' &&
                                charAt(s, i + 1) == 'L' &&
                                charAt(s, i + 2) == 'L') {
                    continuous3DaysLate = true;
                }
                if (absentCount >= 2 || continuous3DaysLate) {
                    return false;
                }
            }
            return true;
        }
    }
}

```

## 反转字符串中的元音字母
[345. leetcode](https://leetcode-cn.com/problems/reverse-vowels-of-a-string/)

```java
// ../../../../src/main/java/com/dll/string/ReverseVowelsOfAString.java

package com.dll.string;

import java.util.Arrays;
import java.util.List;

public class ReverseVowelsOfAString {
    class Solution {
        private boolean isVowel(Character c) {
            List<Character> vowels = Arrays.asList('a', 'e', 'i', 'o', 'u');
            return vowels.contains(c);
        }

        private void swap(char[] chars, int l, int r) {
            char temp = chars[l];
            chars[l] = chars[r];
            chars[r] = temp;
        }

        public String reverseVowels(String s) {
            char[] chars = s.toCharArray();
            int leftIndex = 0;
            int rightIndex = chars.length - 1;
            while (leftIndex < rightIndex) {
                while (leftIndex < rightIndex && !isVowel(Character.toLowerCase(chars[leftIndex]))) {
                    leftIndex++;
                }
                while (leftIndex < rightIndex && !isVowel(Character.toLowerCase(chars[rightIndex]))) {
                    rightIndex--;
                }
                swap(chars, leftIndex, rightIndex);
                leftIndex++;
                rightIndex--;
            }
            return String.valueOf(chars);
        }
    }
}

```

## 压缩字符串
[443. leetcode](https://leetcode-cn.com/problems/string-compression/)

```java
// ../../../../src/main/java/com/dll/string/StringCompression.java

package com.dll.string;

import java.util.*;

public class StringCompression {
    class Solution {
        public int[] splitNum(int num) {
            Deque<Integer> result = new ArrayDeque<>();
            while (num != 0) {
                result.push(num % 10);
                num /= 10;
            }
            return result.stream().mapToInt(i -> i).toArray();
        }

        public int compress(char[] chars) {
            if (chars.length <= 1) {
                return chars.length;
            }
            int writeIndex = 0;
            char pre = chars[0];
            int count = 1;

            for (int i = 1; i < chars.length; i++) {
                if (chars[i] == pre) {
                    count++;
                } else {
                    writeIndex = updateChars(chars, writeIndex, pre, count);
                    count = 1;
                }
                pre = chars[i];
            }
            writeIndex = updateChars(chars, writeIndex, pre, count);
            return writeIndex;
        }

        private int updateChars(char[] chars, int writeIndex, char val, int count) {
            chars[writeIndex] = val;
            writeIndex++;
            if (count != 1) {
                int[] nums = splitNum(count);
                int l = 0;
                while (l < nums.length) {
                    chars[writeIndex++] = Character.forDigit(nums[l], 10);
                    l++;
                }
            }
            return writeIndex;
        }
    }
}

```

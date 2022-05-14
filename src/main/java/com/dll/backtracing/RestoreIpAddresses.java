package com.dll.backtracing;

import java.util.ArrayList;
import java.util.List;

public class RestoreIpAddresses {
    class Solution {
        private final List<String> path = new ArrayList<>();
        private final List<String> result = new ArrayList<>();
        private int stringLength = 0;
        private int visitLength = 0;

        private void backTracing(String s) {
            if (path.size() > 4) {
                return;
            } else if (path.size() == 4 && visitLength == stringLength) {
                result.add(String.join(".", path));
                return;
            }
            for (int splitIndex = 1; splitIndex <= s.length(); splitIndex++) {
                String left = s.substring(0, splitIndex);
                String right = s.substring(splitIndex);
                if (isIllegal(left)) {
                    return;
                }
                path.add(left);
                visitLength += left.length();
                backTracing(right);
                path.remove(path.size() - 1);
                visitLength -= left.length();
            }
        }

        private boolean isIllegal(String text) {
            return text.startsWith("0") && text.length() >= 2
                    || Integer.parseInt(text) > 255;
        }

        public List<String> restoreIpAddresses(String s) {
            if (s.length() < 4 || s.length() > 12) {
                return new ArrayList<>();
            }
            stringLength = s.length();
            backTracing(s);
            return result;
        }
    }
}

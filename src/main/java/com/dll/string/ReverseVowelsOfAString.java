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

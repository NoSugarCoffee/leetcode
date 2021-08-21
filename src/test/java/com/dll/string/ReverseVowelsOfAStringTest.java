package com.dll.string;

import org.junit.Assert;
import org.junit.Test;

public class ReverseVowelsOfAStringTest {
    @Test
    public void test() {
        Assert.assertEquals("holle", new ReverseVowelsOfAString().new Solution().reverseVowels("hello"));
        Assert.assertEquals("leotcede", new ReverseVowelsOfAString().new Solution().reverseVowels("leetcode"));
    }
}
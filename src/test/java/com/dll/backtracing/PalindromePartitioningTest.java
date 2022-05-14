package com.dll.backtracing;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PalindromePartitioningTest {
    @Test
    public void testIsPalindrome() {
        PalindromePartitioning.Solution solution = new PalindromePartitioning().new Solution();
        Assert.assertTrue(solution.isPalindrome("a"));
        Assert.assertFalse(solution.isPalindrome("ab"));
        Assert.assertFalse(solution.isPalindrome("bbab"));
    }

    @Test
    public void testPartition() {
        PalindromePartitioning.Solution solution = new PalindromePartitioning().new Solution();
        List<List<String>> result= solution.partition("a");
        Assert.assertEquals(1, result.size());
    }
}

package com.dll.array;

import org.junit.Assert;
import org.junit.Test;

public class MergeSortedArrayTest {

    @Test
    public void test() {
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        new MergeSortedArray().new Solution().merge(nums1, 3, new int[]{2, 5, 6}, 3);
        Assert.assertArrayEquals(new int[]{1, 2, 2, 3, 5, 6}, nums1);
    }
}
package com.dll.array;


import org.junit.Assert;
import org.junit.Test;

public class CircularArrayLoopTest {
    @Test
    public void test() {
        Assert.assertFalse(new CircularArrayLoop().new Solution().circularArrayLoop(new int[]{-2, 1, -1, -2, -2}));
        Assert.assertFalse(new CircularArrayLoop().new Solution().circularArrayLoop(new int[]{5, -1, 1, 2, 2}));

    }
}
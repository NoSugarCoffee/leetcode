package com.dll.array;

import org.junit.Assert;import org.junit.Test;import static org.junit.Assert.*;

public class TrappingRainWater42Test {
    @Test
    public void test() {
        TrappingRainWater42.Solution solution = new TrappingRainWater42().new Solution();
        Assert.assertEquals(6, solution.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }
}

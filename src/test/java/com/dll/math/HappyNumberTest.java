package com.dll.math;

import org.junit.Assert;
import org.junit.Test;

public class HappyNumberTest {
    @Test
    public void test() {
        Assert.assertEquals(82, new HappyNumber().new Solution().squareSumPerPosition(19));
        Assert.assertEquals(68, new HappyNumber().new Solution().squareSumPerPosition(82));
        Assert.assertEquals(1, new HappyNumber().new Solution().squareSumPerPosition(100));
        Assert.assertTrue(new HappyNumber().new Solution().isHappy(19));
    }
}

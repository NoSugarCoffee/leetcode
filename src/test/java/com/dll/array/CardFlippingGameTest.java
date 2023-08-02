package com.dll.array;

import org.junit.Assert;import org.junit.Test;
public class CardFlippingGameTest {
    @Test
    public void test() {
        CardFlippingGame.Solution solution = new CardFlippingGame().new Solution();
        Assert.assertEquals(2, solution.flipgame(new int[]{1,2,4,4,7}, new int[]{1,3,4,1,3}));
        Assert.assertEquals(1, solution.flipgame(new int[]{1,1}, new int[]{2,2}));
    }
}

package com.dll.graph;

import org.junit.Test;

public class NetworkDelayTimeTest {
    @Test
    public void test() {
        new NetworkDelayTime().new Solution().networkDelayTime(new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2);
        new NetworkDelayTime().new Solution().networkDelayTime(new int[][]{{1, 2, 1}}, 2, 2);
    }
}


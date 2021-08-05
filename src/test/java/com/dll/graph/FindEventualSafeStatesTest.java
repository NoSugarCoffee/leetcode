package com.dll.graph;

import org.junit.Assert;
import org.junit.Test;

public class FindEventualSafeStatesTest {
    @Test
    public void test() {
        int[][] graph = new int[][]{{1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}};
        Assert.assertArrayEquals(new int[]{2, 4, 5, 6},
                new FindEventualSafeStates().new Solution()
                        .eventualSafeNodes(graph).stream().mapToInt(v -> v).toArray());

        graph = new int[][]{{}, {0, 2, 3, 4}, {3}, {4}, {}};
        Assert.assertArrayEquals(new int[]{0, 1, 2, 3, 4},
                new FindEventualSafeStates().new Solution()
                        .eventualSafeNodes(graph).stream().mapToInt(v -> v).toArray());

    }
}

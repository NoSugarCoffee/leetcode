---
weight: 10
title: "图"
---
## 最短路径
### 网络延迟时间
[743. leetcode](https://leetcode-cn.com/problems/network-delay-time/) 

由题意知需要求源结点（k）到其余结点（vi）的最短路径中的最大值，
即 max(min(k, v1), min(k, v2) ... min(k, vn))，
参考 Dijkstra 算法可以解决，需要借助三个辅助数组，
set[] 标记当前已知的最短路径顶点集合，dist[] 当前已知的最短路径，path[] 当前最短路径顶点的前置顶点 
```java
// ../../../../src/main/java/com/dll/graph/NetworkDelayTime.java

package com.dll.graph;

import java.util.Arrays;

public class NetworkDelayTime {
    class Solution {
        // 1 :对应结点已经得到最短路径，0 :没有
        private int[] set;
        // Int.MAX / 2: 不可达, 其余：可达
        private int[] dist;
        private int[] path;
        private int[][] edges;

        private int findShortestInDist(int[] dist, int[] set) {
            int minIndex = -1;
            int min = Integer.MAX_VALUE / 2;

            for (int i = 1; i < dist.length; i++) {
                if (set[i] != 1 && dist[i] < min) {
                    min = dist[i];
                    minIndex = i;
                }
            }
            return minIndex;
        }

        // 源结点是否都全部找到到其他结点的最短路径
        private boolean isFindAllShortestPath(int[] set) {
            return !Arrays.stream(set).filter(v -> v != 1).findAny().isPresent();
        }

        private int findLongestDist(int[] dist, int[] set) {
            int longest = -1;
            for (int i = 1; i < dist.length; i++) {
                if (set[i] == 1 && dist[i] > longest) {
                    longest = dist[i];
                }
            }
            return longest;
        }

        private void dijkstraInit(int[][] times, int n, int k) {
            int[][] edges = new int[n + 1][n + 1];
            for (int i = 0; i < edges.length; i++) {
                Arrays.fill(edges[i], Integer.MAX_VALUE / 2);
            }
            for (int i = 0; i < times.length; i++) {
                edges[times[i][0]][times[i][1]] = times[i][2];
            }
            int[] set = new int[n + 1];
            int[] dist = new int[n + 1];
            int[] path = new int[n + 1];

            Arrays.fill(dist, Integer.MAX_VALUE / 2);
            Arrays.fill(path, -1);
            dist[k] = 0;
            set[0] = 1;

            this.edges = edges;
            this.set = set;
            this.dist = dist;
            this.path = path;
        }

        public int networkDelayTime(int[][] times, int n, int k) {
            dijkstraInit(times, n, k);
            for (int i = 0; i < n; i++) {
                int minIndex = findShortestInDist(dist, set);
                System.out.println(minIndex);
                if (minIndex == -1) {
                    break;
                }
                set[minIndex] = 1;
                for (int j = 1; j <= n; j++) {
                    if (set[j] == 0) {
                        if (dist[minIndex] + edges[minIndex][j] < dist[j]) {
                            dist[j] = dist[minIndex] + edges[minIndex][j];
                            path[j] = minIndex;
                        }
                    }
                }
            }
            return isFindAllShortestPath(set) && findLongestDist(dist, set) != 0 ? findLongestDist(dist, set) : -1;
        }
    }
}

```

### 找到最终的安全状态
[802. leetcode](https://leetcode-cn.com/problems/find-eventual-safe-states/)

深度遍历图的各个结点，并记录下遍历的路径，遇到重复（呈环）则表示深度遍历路径上的每个结点都不是「安全」结点
- 其中 `graph[ori] = new int[]{}` 很关键，可以减少下次遍历到该结点的深度
- unsafe 用于记录当前环内的结点，环内的结点不可能是「安全」的

```java
// ../../../../src/main/java/com/dll/graph/FindEventualSafeStates.java

package com.dll.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindEventualSafeStates {
    class Solution {
        private List<Integer> safe = new ArrayList<>();
        private Set<Integer> unSafe = new HashSet<>();
        private List<Integer> path = new ArrayList<>();

        public List<Integer> eventualSafeNodes(int[][] graph) {
            for (int i = 0; i < graph.length; i++) {
                if (unSafe.contains(i)) {
                    continue;
                }
                if (isSafe(graph, i)) {
                    safe.add(i);
                }
            }
            return safe;
        }

        private boolean isSafe(int[][] graph, int ori) {
            if (path.contains(ori)) {
                unSafe.addAll(path);
                return false;
            }
            path.add(ori);
            for (int node : graph[ori]) {
                if (!isSafe(graph, node)) {
                    path.remove(path.size() - 1);
                    return false;
                }
            }
            graph[ori] = new int[]{};
            path.remove(path.size() - 1);
            return true;
        }
    }
}

```
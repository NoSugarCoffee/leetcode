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

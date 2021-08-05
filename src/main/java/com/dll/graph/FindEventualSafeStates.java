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

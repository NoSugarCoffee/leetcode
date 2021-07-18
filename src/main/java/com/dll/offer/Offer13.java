package com.dll.offer;

public class Offer13 {
    class Solution {
        int mLen;
        int nLen;
        int k;
        int[][] visited;

        private int calc(int num) {
            int sum = 0;
            while(num != 0) {
                sum += num % 10;
                num /= 10;
            }
            return sum;
        }

        public int dfs(int i, int j) {
            if ( i >= mLen || j >= nLen) {
                return 0;
            }
            if (visited[i][j] == 1) {
                return 0;
            }
            visited[i][j] = 1;
            if (calc(i) + calc(j) > this.k) {
                return 0;
            }
            return  1 + this.dfs(i + 1, j) + this.dfs(i, j + 1);
        }

        private void init(int m, int n, int k) {
            this.mLen = m;
            this.nLen = n;
            this.k = k;
            this.visited = new int[m][n];
        }

        public int movingCount(int m, int n, int k) {
            this.init(m, n, k);
            return this.dfs(0, 0);
        }
    }
}

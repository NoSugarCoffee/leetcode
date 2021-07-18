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

        public int dfs(int m, int n, int k) {
            if ( m >= mLen || n >= nLen) {
                return 0;
            }
            if (visited[m][n] == 1) {
                return 0;
            }
            visited[m][n] = 1;
            if (calc(m) + calc(n) > k) {
                return 0;
            }
            return  1 + this.dfs(m + 1, n, k) + this.dfs(m, n + 1, k);
        }

        private void init(int m, int n, int k) {
            this.mLen = m;
            this.nLen = n;
            this.k = k;
            this.visited = new int[m][n];
        }

        public int movingCount(int m, int n, int k) {
            this.init(m, n, k);
            return this.dfs(m, n, k);
        }
    }
}

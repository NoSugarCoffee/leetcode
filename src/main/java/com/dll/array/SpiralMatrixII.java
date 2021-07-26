package com.dll.array;

public class SpiralMatrixII {
    class Solution {

        public int[][] generateMatrix(int n) {
            int[][] matrix = new int[n][n];
            int l = 0;
            int r = n - 1;
            int t = 0;
            int b = n - 1;
            int inc = 1;
            while (inc <= n * n) {
                for (int i = l; i <= r; i++) {
                    matrix[t][i] = inc++;
                }
                t++;
                for (int i = t; i <= b; i++) {
                    matrix[i][r] = inc++;
                }
                r--;
                for (int i = r; i >= l; i--) {
                    matrix[b][i] = inc++;
                }
                b--;
                for (int i = b; i >= t; i--) {
                    matrix[i][l] = inc++;
                }
                l++;
            }
            return matrix;
        }
    }
}

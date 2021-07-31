package com.dll.math;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {
    class Solution {
        int squareSumPerPosition(int number) {
            int result = 0;
            while (number != 0) {
                result += Math.pow(number % 10, 2);
                number /= 10;
            }
            return result;
        }

        public boolean isHappy(int n) {
            Set<Integer> visited = new HashSet<>();
            int next = n;
            while (!visited.contains(next)) {
                visited.add(next);
                next = squareSumPerPosition(next);
                if (next == 1) {
                    return true;
                }
            }
            return false;
        }
    }
}

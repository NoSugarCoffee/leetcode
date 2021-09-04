package com.dll.array;

import java.util.function.Predicate;


public class FirstBadVersion {
    class VersionControl {
        boolean isBadVersion(int val) {
            return false;
        }
    }

    class Solution extends VersionControl {
        public int searchEarlisetErrVersion(int left, int right, Predicate<Integer> isBadFunc) {
            int earlisetErrVersion = -1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (isBadFunc.test(mid)) {
                    earlisetErrVersion = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return earlisetErrVersion;
        }

        public int firstBadVersion(int n) {
            return this.searchEarlisetErrVersion(1, n, this::isBadVersion);
        }
    }
}

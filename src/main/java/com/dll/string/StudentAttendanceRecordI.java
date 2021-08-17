package com.dll.string;

public class StudentAttendanceRecordI {
    class Solution {
        private char charAt(String s, int index) {
            char res = '\0';
            try {
                res = s.charAt(index);
            } catch (IndexOutOfBoundsException ignored) {
            }
            return res;
        }

        public boolean checkRecord(String s) {
            int absentCount = 0;
            boolean continuous3DaysLate = false;
            for (int i = 0; i < s.length(); i++) {
                if (charAt(s, i) == 'A') {
                    absentCount += 1;
                } else if (
                        charAt(s, i) == 'L' &&
                                charAt(s, i + 1) == 'L' &&
                                charAt(s, i + 2) == 'L') {
                    continuous3DaysLate = true;
                }
                if (absentCount >= 2 || continuous3DaysLate) {
                    return false;
                }
            }
            return true;
        }
    }
}

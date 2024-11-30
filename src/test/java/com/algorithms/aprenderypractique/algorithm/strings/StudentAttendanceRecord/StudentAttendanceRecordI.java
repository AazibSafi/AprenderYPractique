package com.algorithms.aprenderypractique.algorithm.strings.StudentAttendanceRecord;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *      https://leetcode.com/problems/student-attendance-record-i
 */
public class StudentAttendanceRecordI extends BaseTest {

    @Test
    public void solution() {
        Assert.assertTrue(checkRecord("PPALLP"));
        Assert.assertFalse(checkRecord("PPALLL"));
        Assert.assertTrue(checkRecord("LALL"));
        Assert.assertTrue(checkRecord("LPLPLPLPLPL"));
    }

//  O(n)/O(1)
    public boolean checkRecord(String s) {
        int absents = 0, late = 0;

        for (char ch : s.toCharArray()) {
            if(ch != 'L')
                late = 0;

            if(ch == 'L') {
                late++;
                if(late > 2)   return false;
            }
            else if(ch == 'A') {
                absents++;
                if(absents >= 2)    return false;
            }
        }
        return true;
    }

}

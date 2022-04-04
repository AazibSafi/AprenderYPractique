package com.algorithms.aprenderypractique.algorithm.strings;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/*
    https://leetcode.com/problems/string-to-integer-atoi/
 */
public class StringToInteger_ATOI extends BaseTest {

    @Test
    public void test() {
//        Assert.assertEquals(42, myAtoi("42"));
//        Assert.assertEquals(-42, myAtoi("-42"));
//        Assert.assertEquals(937, myAtoi("00937"));
//        Assert.assertEquals(0, myAtoi("Hello"));
//        Assert.assertEquals(776, myAtoi("776 Hola"));
//        Assert.assertEquals(0, myAtoi(".. 776 Hola"));
//        Assert.assertEquals(-345, myAtoi("-345 xyz"));
//        Assert.assertEquals(-34, myAtoi("-34+5 xyz"));
//        Assert.assertEquals(34, myAtoi("  34+5 xyz"));
        Assert.assertEquals(Integer.MIN_VALUE, myAtoi("-91283472332"));
    }

    int myAtoi(String str) {
        int num = 0;
        boolean isNegative = false;

        for(int i=0; i<str.length(); i++) {
            char c = str.charAt(i);

            if(!Character.isDigit(c)) {
                if(isSign(c) && (i==0 || num==0))    isNegative = c == '-';
                else if(c!=' ' || num!=0)   break;
            }
            else    num = (num * 10)    // here is the problem if the integer becomes larger than Integer.Max_value after multiply by 10
                    + (c - '0');
        }

        num = isNegative ? -num : num;
        return clamp(num, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    boolean isSign(char c) {
        return c=='+' || c=='-';
    }

    public int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

}

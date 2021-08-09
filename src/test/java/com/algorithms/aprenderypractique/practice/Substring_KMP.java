package com.algorithms.aprenderypractique.practice;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class Substring_KMP extends BaseTest {

    @Test
    public void test() {
        String str = "abcdfgcdeyz";
        String ptr = "cde";
        Assert.assertEquals(6,findSubString(str,ptr));

        str = "abcdfgcd";
        ptr = "cde";
        Assert.assertEquals(-1,findSubString(str,ptr));

        str = "abcdfgyzcde";
        ptr = "cde";
        Assert.assertEquals(8,findSubString(str,ptr));

        str = "abcdcdcdegyzcde";
        ptr = "cdcde";
        Assert.assertEquals(-1,findSubString(str,ptr));
    }

    int findSubString(String str, String subStr) {
        int strCounter, subStrCounter, index;
        for(strCounter = 0;strCounter<str.length();strCounter++) {
            index = -1;
            subStrCounter = 0;
            while(subStrCounter<subStr.length() && strCounter<str.length()
                && str.charAt(strCounter) == subStr.charAt(subStrCounter)) {
                if(index == -1) {
                    index = strCounter;
                }
                subStrCounter++;
                strCounter++;
            }
            if(subStrCounter == subStr.length()) {
                return index;
            }
        }
        return -1;
    }

}

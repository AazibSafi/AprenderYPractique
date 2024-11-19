package com.algorithms.aprenderypractique.algorithm.strings.Math;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/add-strings/
 *  https://www.youtube.com/watch?v=_Qp-CTzat50
 */
public class AddStrings extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals("134", addStrings("11","123"));
        Assert.assertEquals("533", addStrings("456","77"));
        Assert.assertEquals("0", addStrings("0","0"));
        Assert.assertEquals("10", addStrings("1","9"));
    }

/*
    Time: O(n*m)
    Space: O(n+1)
 */
    public String addStrings(String num1, String num2) {
        StringBuilder builder = new StringBuilder();

        int i = num1.length()-1, j = num2.length()-1;
        int carry = 0, sum;

        while(i >= 0 || j >= 0) {
            sum = carry;

            if(i >= 0)  sum += (num1.charAt(i--) - '0');
            if(j >= 0)  sum += (num2.charAt(j--) - '0');

            builder.append(sum % 10);
            carry = sum / 10;
        }

        if(carry != 0)      builder.append(carry);
        return builder.reverse().toString();
    }

}

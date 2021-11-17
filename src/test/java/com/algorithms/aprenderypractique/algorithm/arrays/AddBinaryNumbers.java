package com.algorithms.aprenderypractique.algorithm.arrays;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/add-binary/submissions/
 */
public class AddBinaryNumbers extends BaseTest {

    @Test
    public void test() {
        String str = "11", ptr = "1";
        Assert.assertEquals("100",addBinary(str,ptr));

        str = "1010"; ptr = "1011";
        Assert.assertEquals("10101",addBinary(str,ptr));
    }

    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int i=a.length()-1,  j=b.length()-1;

        while(i>=0 || j>=0) {
            int sum = carry;
            if(i>=0) sum += a.charAt(i) - '0';
            if(j>=0) sum += b.charAt(j) - '0';

            sb.insert(0, sum % 2);
            carry = sum / 2;

            i--;  j--;
        }

        if(carry != 0)  sb.insert(0, carry);

        return sb.toString();
    }

}

package com.algorithms.aprenderypractique.Algorithms.Arrays.MathOperations;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *      https://leetcode.com/problems/valid-number/
 *      https://www.youtube.com/watch?v=LNXzVgSzQzI&ab_channel=CodingDecoded
 */
public class ValidNumber extends BaseTest {

    @Test
    public void test() {
        Assert.assertTrue(isNumber("0089"));
        Assert.assertTrue(isNumber("-0.1"));
        Assert.assertTrue(isNumber("2E10"));
        Assert.assertTrue(isNumber("+66.87e-1"));
        Assert.assertTrue(isNumber("0"));
        Assert.assertTrue(isNumber(".1"));
        Assert.assertTrue(isNumber("3."));

        Assert.assertFalse(isNumber("e"));
        Assert.assertFalse(isNumber("."));
        Assert.assertFalse(isNumber("abc"));
        Assert.assertFalse(isNumber("e3"));
        Assert.assertFalse(isNumber("99e2.5"));
        Assert.assertFalse(isNumber("-+3"));
        Assert.assertFalse(isNumber("95a54e53"));
    }

//  Time: O(n)
    public boolean isNumber(String s) {
        if(s == null || s.trim().length() == 0)     return false;

        boolean numberSeen = false;
        boolean decimalSeen = false;
        boolean eSeen = false;

        s = s.trim();

        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '.':
                    if(decimalSeen || eSeen)   return false;
                    decimalSeen = true;
                    break;
                case 'E':
                case 'e':
                    if(eSeen || !numberSeen)   return false;    // e10
                    eSeen = true;
                    numberSeen = false;     //123e78
                    break;
                case '+':
                case '-':
                    if(i!=0 && s.charAt(i-1)!='e')  return false;
                    numberSeen = false;
                    break;
                default:
                    if(Character.isDigit(c))   numberSeen = true;
                    else                        return false;
                    break;
            }
        }

        return numberSeen;      // "."
    }

}

package com.algorithms.aprenderypractique.Algorithms.Conversions;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/convert-a-number-to-hexadecimal/
 *  https://www.youtube.com/watch?v=v17gv46J0wQ
 *  https://programmerall.com/article/51461884890/
 */
public class DecimalToHexa extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals("1a", toHex(26));
        Assert.assertEquals("0", toHex(0));
        Assert.assertEquals("ffffffff", toHex(-1));
    }

    public String toHex(int num) {
        if(num == 0)    return "0";
        StringBuilder sb = new StringBuilder();

        while(num != 0) {
            int digit = num & 0xf;
            sb.insert(0, digit<10 ? (char)(digit+'0') : (char)(digit-10+'a'));
            num >>>= 4;
        }

        return sb.toString();
    }

//  Not working in 2's compliment - negative numbers
    public String toHex2(int num) {
        if(num < 0)     num += Math.pow(2,32);

        StringBuilder hexa = new StringBuilder();
        int rem;

        while(num > 15) {
            rem = num % 16;
            num = num / 16;
            hexa.insert(0, hexAlpha(rem));
        }

        return hexa.insert(0, hexAlpha(num)).toString();
    }

    char hexAlpha(int x) {
        switch(x) {
            case 10:    return 'a';
            case 11:    return 'b';
            case 12:    return 'c';
            case 13:    return 'd';
            case 14:    return 'e';
            case 15:    return 'f';
            default:    return (char) (x+'0');
        }
    }

}

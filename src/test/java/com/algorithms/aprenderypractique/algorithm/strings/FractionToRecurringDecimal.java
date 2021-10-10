package com.algorithms.aprenderypractique.algorithm.strings;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 *  https://leetcode.com/problems/fraction-to-recurring-decimal/
 *  https://www.youtube.com/watch?v=2cRS9dNa780
 */
public class FractionToRecurringDecimal extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals("0.5", fractionToDecimal(1,2));
        Assert.assertEquals("2", fractionToDecimal(2,1));
        Assert.assertEquals("0.(6)", fractionToDecimal(2,3));
        Assert.assertEquals("0.(012)", fractionToDecimal(4,333));
        Assert.assertEquals("0.2", fractionToDecimal(1,5));
        Assert.assertEquals("-6.25", fractionToDecimal(-50,8));
        Assert.assertEquals("0.0000000004656612873077392578125", fractionToDecimal(-1,-2147483648));
    }

    public String fractionToDecimal(int numerator, int denominator) {
        if(numerator == 0) return "0";

        StringBuilder ans = new StringBuilder();
        if( (numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0))
            ans.append('-');

        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);

        long rem = num % den;
        long qoutient = num / den;
        ans.append(qoutient);

        if(rem == 0)  return ans.toString();

        ans.append(".");

        Map<Long, Integer> map = new HashMap<>();

        while(rem != 0) {
            if (map.containsKey(rem)) {
                int len = map.get(rem);
                ans.insert(len, '(');
                ans.append(')');
                break;
            }
            else {
                map.put(rem, ans.length());
                rem *= 10;
                qoutient = rem / den;
                rem = rem % den;
                ans.append(qoutient);
            }
        }

        return ans.toString();
    }

}

package com.algorithms.aprenderypractique.Algorithms.Strings;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 *  https://leetcode.com/problems/roman-to-integer
 *
 *  https://www.youtube.com/watch?v=tsmrUi5M1JU
 */
public class RomanToInt extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(3, romanToInt("III"));
        Assert.assertEquals(58, romanToInt("LVIII"));
        Assert.assertEquals(1994, romanToInt("MCMXCIV"));
    }

    public int romanToInt(String s) {
        Map<Character, Integer> roman = Map.of('I', 1, 'V', 5, 'X', 10, 'L', 50, 'C', 100, 'D', 500, 'M', 1000);
        int num = 0;
        for(int i=0; i<s.length()-1; i++) {
            if(roman.get(s.charAt(i)) < roman.get(s.charAt(i+1)))
                num -= roman.get(s.charAt(i));
            else
                num += roman.get(s.charAt(i));
        }

        num += roman.get(s.charAt(s.length()-1));
        return num;
    }

}

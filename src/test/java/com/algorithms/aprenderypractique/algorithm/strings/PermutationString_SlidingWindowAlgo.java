package com.algorithms.aprenderypractique.algorithm.strings;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.CommonHelper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 *  https://leetcode.com/problems/permutation-in-string/
 *  @see AnagramString_SlidingWindowAlgo
 */
public class PermutationString_SlidingWindowAlgo extends BaseTest {

    @Test
    public void test() {
        String s1 = "eidbaooo", s2 = "ab";
        Assert.assertTrue(checkInclusion(s1, s2));

        s1 = "eidboaoo"; s2 = "ab";
        Assert.assertFalse(checkInclusion(s1, s2));

        s1 = "aa"; s2 = "aaa";      //  //  Edge Case: if length of the s2 is greater than the s1
        Assert.assertFalse(checkInclusion(s1, s2));
    }

//    Time: O(N)
    public boolean checkInclusion(String s1, String s2) {
        if(StringUtils.isBlank(s1) || StringUtils.isBlank(s2) || s2.length() > s1.length())
            return false;

        Map<Character, Integer> s1Map = new HashMap<>();
        Map<Character, Integer> s2Map = CommonHelper.fillTableWithOccurrences(s2);

        int left=0, right=0;

        while(right<s2.length()-1) {
            char c = s1.charAt(right++);
            if(s2Map.containsKey(c)) {
                s1Map.put(c, s1Map.getOrDefault(c,0) + 1);
            }
        }

        while(right<s1.length()) {
            char rightChar = s1.charAt(right++);
            if(s2Map.containsKey(rightChar)) {
                s1Map.put(rightChar, s1Map.getOrDefault(rightChar,0)+1);
            }

            if(s1Map.equals(s2Map))      return true;

            char leftChar = s1.charAt(left++);
            if(s1Map.containsKey(leftChar)) {
                s1Map.put(leftChar, s1Map.get(leftChar)-1);

                if(s1Map.get(leftChar) <= 0)
                    s1Map.remove(leftChar);
            }
        }

        return false;
    }

}

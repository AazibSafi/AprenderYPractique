package com.algorithms.aprenderypractique.algorithm.strings.SlidingWindowAlgorithm;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Minimum Window Substring
 * Sliding Window Algorithm
 * Find a substring that contains all the character from T string at-least once in the str String
 *
 * https://leetcode.com/problems/minimum-window-substring/
 * https://www.youtube.com/watch?v=nMaKzLWceFg
 */
public class MinimumWindowSubstring extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals("BANC", minWindow("ADOBECODEBANC","ABC"));
        Assert.assertEquals("ABC", minWindow("AAABC","ABC"));
        Assert.assertEquals("dcbef", minWindow("dcbefebce","fd"));
        Assert.assertEquals("zabcdcabf", minWindow("zabcdcabf","abcdfz"));
        Assert.assertEquals("", minWindow("a","bb"));       // Edge Case: When T has more length
        Assert.assertEquals("", minWindow("aaa","bb"));     // When no substring is found
        Assert.assertEquals("b", minWindow("aaab","b"));
        Assert.assertEquals("a", minWindow("a","a"));
        Assert.assertEquals("", minWindow("","bb"));
        Assert.assertEquals("", minWindow("aa",""));
    }

/*
    Time: O(n + m), where n is the length of s and m is the length of t. Each character in s and t is processed at most twice (once in each while loop).
    Space: O(m), where m is the length of t. The HashMap hm stores at most m characters.
 */
    public String minWindow(String str, String T) {
        int m = str.length();
        int n = T.length();
        if(m == 0 || n == 0 || m < n)       return "";
    
        Map<Character,Integer> table = new HashMap<>();
        for(Character c : T.toCharArray()) {
            table.put(c, table.getOrDefault(c,0)+1);
        }

        int[] min = new int[]{0, Integer.MAX_VALUE};   // {startIndex, minLength} of substring window

        int i = 0, count = 0;
        for(int j=0; j<str.length(); j++) {
            char c = str.charAt(j);
    
            if(table.containsKey(c)) {
                table.put(c, table.getOrDefault(c,0) - 1);
                if(table.get(c) >= 0)
                    count++;
            }
    
            while(count == n) {
                if(j-i+1 < min[1]) {
                    min[0] = i; min[1] = (j-i+1);
                }
    
                char t = str.charAt(i++);
                if(table.containsKey(t)) {
                    table.put(t, table.getOrDefault(t,0) + 1);
                    if(table.get(t) > 0)
                        count--;
                }
            }
        }
    
        if(min[1] == Integer.MAX_VALUE) return "";
        return str.substring(min[0], min[0]+min[1]);
    }

}

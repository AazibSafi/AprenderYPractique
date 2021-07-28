package com.algorithms.aprenderypractique.algorithm.strings;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.CommonHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Minimum Window Substring
 * Sliding Window Algorithm
 * Find a substring that contains all the character from T string at-least once in the str String
 *
 * https://www.youtube.com/watch?v=nMaKzLWceFg
 */
public class MinimumWindowSubstringPrac extends BaseTest {

    @Test
    public void test() {
        String str = "ADOBECODEBANC";
        String T = "ABC";
        Assert.assertEquals("BANC",findMinimumWindowSubstring(str,T));

        str = "dcbefebce";
        T = "fd";
        Assert.assertEquals("dcbef",findMinimumWindowSubstring(str,T));
    }

    public Map fillMapWithOccurences(String T) {
        Map<Character,Integer> map = new HashMap<>();
        for(char c : T.toCharArray()) {
            map.put(c , map.getOrDefault(c,0) +1);
        }
        return map;
    }

    public String findMinimumWindowSubstring(String str, String T) {
        Map<Character,Integer> map = fillMapWithOccurences(T);
        int iMin = Integer.MAX_VALUE;
        int jMin = Integer.MAX_VALUE;
        int count = 0;

        int left = 0, right=0;

        while(right<str.length()) {
            while(count < T.length() && right<str.length()) {
                char c = str.charAt(right++);
                if(map.containsKey(c)) {
                    map.put(c, map.get(c) - 1);

                    if(map.get(c) >= 0)
                        count++;
                }
            }

            if(right - left < jMin - iMin) {
                iMin = left;
                jMin = right;
            }

            while(count == T.length() && left<=right) {
                char c = str.charAt(left++);
                if(map.containsKey(c)) {
                    map.put(c, map.get(c) + 1);

                    if(map.get(c) > 0)
                        count--;
                }
            }
        }

        if(iMin == -1 && jMin == -1) return "";
        else return str.substring(iMin,jMin);
    }

}

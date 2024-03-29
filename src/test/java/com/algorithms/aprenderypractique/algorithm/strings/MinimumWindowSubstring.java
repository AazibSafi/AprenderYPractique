package com.algorithms.aprenderypractique.algorithm.strings;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.CommonHelper;
import org.junit.Assert;
import org.junit.Test;

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
        String str = "ADOBECODEBANC" , T = "ABC";
        Assert.assertEquals("BANC", minimumWindowSubstring(str,T));

        str = "AAABC";  T = "ABC";
        Assert.assertEquals("ABC", minimumWindowSubstring(str,T));

        str = "dcbefebce";  T = "fd";
        Assert.assertEquals("dcbef", minimumWindowSubstring(str,T));

        str = "zabcdcabf";  T = "abcdfz";
        Assert.assertEquals("zabcdcabf", minimumWindowSubstring(str,T));

        str = "a";    T = "bb";   // Edge Case: When T has more length
        Assert.assertEquals("", minimumWindowSubstring(str,T));

        str = "aaa";   T = "bb";     // When no substring is found
        Assert.assertEquals("", minimumWindowSubstring(str,T));

        str = "aaab";  T = "b";
        Assert.assertEquals("b", minimumWindowSubstring(str,T));

        str = "a";  T = "a";
        Assert.assertEquals("a", minimumWindowSubstring(str,T));

        str = "";  T = "bb";
        Assert.assertEquals("", minimumWindowSubstring(str,T));

        str = "aa";  T = "";
        Assert.assertEquals("", minimumWindowSubstring(str,T));
    }

    public String minimumWindowSubstring(String str, String T) {
        int m = str.length();
        int n = T.length();

        if(m == 0 || n == 0 || m < n)       return "";

        Map<Character,Integer> table = CommonHelper.fillTableWithOccurrences(T);

        int count = 0;
        int[] minLengthResult = new int[]{0, Integer.MAX_VALUE}; // {startIndex and minLength} of substring window
        int leftPointer=0;

        for(int rightPointer=0; rightPointer<m; rightPointer++) {

/*
    if the current pointer item is found in the table, decrease its count as it has been used.
    increase the count as the current window has the required item
 */
            char c = str.charAt(rightPointer);

            if (table.containsKey(c)) {
                table.put(c, table.get(c) - 1);

                if (table.get(c) >= 0) {
                    count++;
                }
            }

/*
    When count = T length
    Means current substring window contains all the characters from T

    Now it's time to make current window substring in-eligible
    by removing 1 required character from the substring
 */
            while (count == n) {
                saveMinLengthResult(minLengthResult, leftPointer, rightPointer);

                c = str.charAt(leftPointer);
                if (table.containsKey(c)) {
                    table.put(c, table.get(c) + 1);

                    if (table.get(c) > 0) {
                        count--;
                    }
                }

                leftPointer++;
            }
        }

        return getSubstring(str, minLengthResult);
    }

//      If no such substring is found, return empty string
    String getSubstring(String str, int[] minLengthResult) {
        if(minLengthResult[1] == Integer.MAX_VALUE)     return "";
        return str.substring(minLengthResult[0], minLengthResult[0] + minLengthResult[1]);
    }

    public void saveMinLengthResult(int[] minLengthResult, int start, int end) {
        int newLength = end - start + 1;
        if(newLength < minLengthResult[1]) {       //   if new Length is smaller than the previously stored length
            minLengthResult[0] = start;
            minLengthResult[1] = newLength;
        }
    }

}

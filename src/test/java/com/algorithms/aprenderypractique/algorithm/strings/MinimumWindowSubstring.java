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
 * https://www.youtube.com/watch?v=nMaKzLWceFg
 */
public class MinimumWindowSubstring extends BaseTest {

    @Test
    public void test() {
        String str = "ADOBECODEBANC";
        String T = "ABC";
        Assert.assertEquals("BANC",findMinimumWindowSubstring(str,T));

        str = "AAABC";
        T = "ABC";
        Assert.assertEquals("ABC",findMinimumWindowSubstring(str,T));

        str = "dcbefebce";
        T = "fd";
        Assert.assertEquals("dcbef",findMinimumWindowSubstring(str,T));

        str = "bfbeadbcbcbfeaaeefcddcccbbbfaaafdbebedddf";
        T = "cbccfafebccdccebdd";
        Assert.assertEquals("bfbeadbcbcbfeaaeefcddcccbbbfaaafdbebedddf",findMinimumWindowSubstring(str,T));
    }

    public String findMinimumWindowSubstring(String str, String T) {

        Map<Character,Integer> table = CommonHelper.fillTableWithOccurrences(T);

        int count = 0;
        int[] minLengthResult = new int[]{-1,0}; // {minLength and startIndex} of substring window
        int rightPointer=0, leftPointer=0;

        while(rightPointer<str.length()) {

/*
    Move the right pointer until count = T length
    Means current substring window contains all the characters from T
    if the current pointer item is found in the table, decrease its count as it has been used.
    increase the count as we the current window has the required item
 */
            while(count != T.length() && rightPointer<str.length()) {
                char c = str.charAt(rightPointer++);

                if(table.containsKey(c)) {
                    table.put(c,table.get(c) - 1);

                    if(table.get(c) >=0) {
                        count++;
                    }
                }
            }

            int currWindowEnd = rightPointer-1;

/*
    save current window's length and start index if it is minimum than previous
 */
            saveMinLengthResult(minLengthResult,leftPointer,currWindowEnd);

/*
    Move the left pointer until we remove an eligible character from the window
    if count decreases means we have removed an eligible item from the window and the window is missing that item now.
    if the current pointer item is found in the table, increase its count as it is again required in the window because it was removed.
 */
            while(count==T.length() && leftPointer<currWindowEnd) {

                saveMinLengthResult(minLengthResult,leftPointer,currWindowEnd);

                char c = str.charAt(leftPointer++);

                if(table.containsKey(c)) {
                    table.put(c,table.get(c) + 1);

                    if(table.get(c) > 0) {
                        count--;
                    }
                }
            }

        }
        return str.substring(minLengthResult[1], minLengthResult[1] + minLengthResult[0]);
    }

/*
    if the new Length
 */
    public void saveMinLengthResult(int[] minLengthResult, int start, int end) {
        int newLength = end-start+1;
        if(minLengthResult[0] == -1 ||          // if first time assignment
                newLength < minLengthResult[0]) {       // if new Length is smaller than the previously stored length
            minLengthResult[0] = newLength;
            minLengthResult[1] = start;
        }
    }

}

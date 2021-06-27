package com.algorithms.aprenderypractique.algorithm;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/*
 * Sliding Window Algorithm
 * Longest Substring Without Repeating Characters
 * https://www.youtube.com/watch?v=4i6-9IzQHwo&ab_channel=MichaelMuinos
 */
public class LengthOfLongestSubstring_SlidingWindow extends BaseTest {

    @Test
    public void test() {
        String str = "PWWKEW";
        System.out.println(length_of_LongestSubString(str));
    }

    // O(2N) --> O(N)
    public int length_of_LongestSubString(String str) {

        if(StringUtils.isBlank(str)) {
            return 0;
        }

        Set<Character> substringChars = new HashSet<>();

        int j=0;
        for(int i=0;i<str.length();i++) {
            while(substringChars.contains(str.charAt(i))) {
                substringChars.remove(str.charAt(j));
                j++;
            }
            substringChars.add(str.charAt(i));
        }
        return substringChars.size();
    }

}

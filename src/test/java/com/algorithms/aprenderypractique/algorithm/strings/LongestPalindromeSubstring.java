package com.algorithms.aprenderypractique.algorithm.strings;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Length of Longest Palindrome Substring
 * O(N 3)
 *
 * https://hackernoon.com/manachers-algorithm-explained-longest-palindromic-substring-22cb27a5e96f
 */
public class LongestPalindromeSubstring extends BaseTest {

    @Test
    public void test() {
        String str = "BABCBAB";
        System.out.println(length_of_Longest_palindrome_substring(str,0,str.length()-1));
    }

    class SubstringIndex {
        int start, end;

        public SubstringIndex(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

//    To avoid repeated calculation of already calculated lengths
    Map<SubstringIndex,Integer> subStringLengthCache = new HashMap<>();

//  Time complexity: O(N 3)
    public int length_of_Longest_palindrome_substring(String str, int start, int end) {
        if(start>end) {
            return 0;
        }
        if(start==end) {
            return 1;
        }

        Integer cacheValue = getFromCache(subStringLengthCache,start,end);
        if(cacheValue != null) {
            return cacheValue;
        }

        int subStringLength;

        if(str.charAt(start)==str.charAt(end)) {
            subStringLength = length_of_Longest_palindrome_substring(str,start+1,end-1) +2;
        }
        else {
            subStringLength = Math.max( length_of_Longest_palindrome_substring(str,start,end-1),
                    length_of_Longest_palindrome_substring(str,start+1,end) );
        }

        subStringLengthCache.put(new SubstringIndex(start,end),subStringLength);
        return subStringLength;
    }

    private Integer getFromCache(Map<SubstringIndex,Integer> subStringLengthCache, int start, int end) {
        for(Map.Entry<SubstringIndex,Integer> cache : subStringLengthCache.entrySet()) {
            if(cache.getKey().start == start && cache.getKey().end == end) {
                return cache.getValue();
            }
        }
        return null;
    }

}
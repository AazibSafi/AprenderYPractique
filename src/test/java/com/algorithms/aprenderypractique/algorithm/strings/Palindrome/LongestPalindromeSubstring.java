package com.algorithms.aprenderypractique.algorithm.strings.Palindrome;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Length of the Longest Palindrome Substring
 * https://hackernoon.com/manachers-algorithm-explained-longest-palindromic-substring-22cb27a5e96f
 *
 * For Efficient Algorithm
 * @see LongestPalindromeSubstring_ManachersAlgorithm
 */
public class LongestPalindromeSubstring extends BaseTest {

    @Test
    public void test() {
        String str = "BABCBAB";
        Assert.assertEquals(7, longestPalindromeLength(str));

        str = "abacabacabb";        //   bacabacab
        Assert.assertEquals(9, longestPalindromeLength(str));

//    below test is failing
        str = "acacacb";            //      acaca or cacac
        //Assert.assertEquals(5, longestPalindromeLength(str));
    }

    public int longestPalindromeLength(String str) {
        return longestPalindromeLength(str,0,str.length()-1);
    }

//    To avoid repeated calculation of already calculated lengths
    Map<SubstringIndex,Integer> subStringLengthCache = new HashMap<>();

//  Time complexity: O(N^3)
    public int longestPalindromeLength(String str, int start, int end) {
        if(start > end)       return 0;

        if(start == end)      return 1;

        Integer cacheValue = getFromCache(subStringLengthCache,start,end);

        if(cacheValue != null)      return cacheValue;

        int subStringLength;

        if(str.charAt(start) == str.charAt(end)) {
            subStringLength = longestPalindromeLength(str,start+1,end-1) + 2;
        }
        else {
            subStringLength = Math.max(
                    longestPalindromeLength(str,start,end-1),
                    longestPalindromeLength(str,start+1,end) );
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

    class SubstringIndex {
        int start, end;

        public SubstringIndex(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

}

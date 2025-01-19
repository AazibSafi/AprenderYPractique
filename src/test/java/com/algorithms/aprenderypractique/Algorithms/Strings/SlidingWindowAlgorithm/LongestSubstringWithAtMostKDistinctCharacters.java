package com.algorithms.aprenderypractique.Algorithms.Strings.SlidingWindowAlgorithm;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 *      https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters
 *      https://leetcode.ca/2016-11-04-340-Longest-Substring-with-At-Most-K-Distinct-Characters
 *      https://leetcode.ca/2016-05-07-159-Longest-Substring-with-At-Most-Two-Distinct-Characters
 */
public class LongestSubstringWithAtMostKDistinctCharacters extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals(3, lengthOfLongestSubstringKDistinct("eceba", 2));
        Assert.assertEquals(5, lengthOfLongestSubstringKDistinct("ccaabbb", 2));
        Assert.assertEquals(2, lengthOfLongestSubstringKDistinct("aaRS", 1));
    }

/*
    Time: O(n)
    Space: O(k) => For HashMap
 */
    public int lengthOfLongestSubstringKDistinct(String str, int k) {
        Map<Character, Integer> map = new HashMap<>();

        int max = 0, left = 0;

        for(int right=0; right<str.length(); right++) {
            char c = str.charAt(right);
            map.put(c, map.getOrDefault(c,0) + 1);

            while(map.size() > k) {
                char t = str.charAt(left++);
                map.put(t, map.getOrDefault(t,0) - 1);
                if(map.get(t) == 0) map.remove(t);
            }

            max = Math.max(max, right - left + 1);
        }
        return max;
    }

}

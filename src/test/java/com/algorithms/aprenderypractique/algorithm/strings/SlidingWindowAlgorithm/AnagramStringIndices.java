package com.algorithms.aprenderypractique.algorithm.strings.SlidingWindowAlgorithm;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.CommonHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 *     Find All Anagrams in a String | Sliding window
 *     Result: Set of First indices of all anagrams
 *     https://www.youtube.com/watch?v=fYgU6Bi2fRg
 *     https://leetcode.com/problems/find-all-anagrams-in-a-string
 */
public class AnagramStringIndices extends BaseTest {

    @Test
    public void solution() {
        Assert.assertTrue(CommonHelper.isEquals(Arrays.asList(0,5,6), findAnagrams("abcdebacb","cab")));
        Assert.assertTrue(CommonHelper.isEquals(Arrays.asList(0,2,3,5,10,11,12,13,17), findAnagrams("babcabbacaabcbabcacbb","abbc")));
        Assert.assertTrue(CommonHelper.isEquals(Arrays.asList(0,1,2), findAnagrams("abab","ab")));

        //  Edge Case: if length of the ptr is greater than the str
        Assert.assertTrue(CommonHelper.isEquals(new ArrayList<>(), findAnagrams("aaaaa","aaaaaaa")));
    }

/*
    Time: O(N)
    Space: O(1)
    This Logic only work if the Constraints is:  str and ptr consist of lowercase English letters.
 */
    public List<Integer> findAnagrams(String str, String ptr) {
        int n = str.length(), m = ptr.length();
        if(m > n) return new ArrayList<>();    // Edge Case: if length of the ptr is greater than the str

        List<Integer> outputIndices = new ArrayList<>();
        int[] anagramWindow = new int[26];
        int[] ptrFrequency = new int[26];

        for(int i=0; i<m; i++) {
            anagramWindow[str.charAt(i) - 'a']++;   // Constraints:  str and ptr consist of lowercase English letters.
            ptrFrequency[ptr.charAt(i) - 'a']++;
        }

        if(Arrays.equals(anagramWindow, ptrFrequency)) outputIndices.add(0);

        for(int i=0, j=m; j<n; i++, j++) {
            anagramWindow[str.charAt(i) - 'a']--;
            anagramWindow[str.charAt(j) - 'a']++;

            if(Arrays.equals(anagramWindow, ptrFrequency))
                outputIndices.add(i+1);
        }
        return outputIndices;
    }

/*
    Time: O(N)
    Space: O(M)  length of ptr
 */
    public List<Integer> findAnagrams2(String str, String ptr) {
        int n = str.length(), m = ptr.length();
        if(m > n) return new ArrayList<>();    // Edge Case: if length of the ptr is greater than the str

        Map<Character,Integer> anagramWindow = new HashMap<>();
        Map<Character,Integer> ptrOccurrences = new HashMap<>();    // All occurrences of characters in ptr
        List<Integer> outputIndices = new ArrayList<>();              // save the resultant indices

        for(int i=0; i<m; i++) {
            char strChar = str.charAt(i);
            anagramWindow.put(strChar, anagramWindow.getOrDefault(strChar, 0) + 1);

            char ptrChar = ptr.charAt(i);
            ptrOccurrences.put(ptrChar, ptrOccurrences.getOrDefault(ptrChar,0) + 1);
        }

        if(anagramWindow.equals(ptrOccurrences)) {
            outputIndices.add(0);
        }

        for(int i=0, j=m; j<n; i++, j++) {
            char right = str.charAt(j);
            anagramWindow.put(right, anagramWindow.getOrDefault(right,0)+1);

            char left = str.charAt(i);
            anagramWindow.put(left, anagramWindow.get(left)-1);
            if(anagramWindow.get(left) == 0) {
                anagramWindow.remove(left);
            }

            if(anagramWindow.equals(ptrOccurrences)) {
                outputIndices.add(i+1);
            }
        }
        return outputIndices;
    }

}

package com.algorithms.aprenderypractique.algorithm.strings;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *     Find All Anagrams in a String | Sliding window
 *     Result: Set of First indices of all anagrams
 *     https://www.youtube.com/watch?v=fYgU6Bi2fRg
 *     https://leetcode.com/problems/find-all-anagrams-in-a-string/
 */
public class AnagramString_SlidingWindowAlgo extends BaseTest {

    @Test
    public void solution() {
        String str = "abcdebacb";
        String ptr = "cab";
        Set<Integer> result = findAllAnagrams(str,ptr);
        Assert.assertEquals(new HashSet<>(Arrays.asList(0,5,6)),result);

        str = "babcabbacaabcbabcacbb";
        ptr = "abbc";
        result = findAllAnagrams(str,ptr);
        Assert.assertEquals(new HashSet<>(Arrays.asList(0,2,3,5,10,11,12,13,17)),result);

        str = "abab";
        ptr = "ab";
        result = findAllAnagrams(str,ptr);
        Assert.assertEquals(new HashSet<>(Arrays.asList(0,1,2)),result);

//  Edge Case: if length of the ptr is greater than the str
        str = "aaaaa";
        ptr = "aaaaaaa";
        result = findAllAnagrams(str,ptr);
        Assert.assertEquals(new HashSet<>(),result);
    }

//    O(N)
    public Set<Integer> findAllAnagrams(String str, String ptr) {

        if(StringUtils.isBlank(str) || StringUtils.isBlank(ptr) || ptr.length() > str.length())    // Edge Case: if length of the ptr is greater than the str
            return new HashSet<>();

        Map<Character,Integer> anagramWindow = new HashMap<>();
        Map<Character,Integer> ptrOccurrences = new HashMap<>();    // All occurrences of characters in ptr

        Set<Integer> anagramIndices = new HashSet<>();      // save the resultant indices

/*
    Filling the ptr Occurrences
    Filling anagram Window - Occurrences of first M characters of Str
    M -> Ptr length
 */
        for(int i=0; i<ptr.length(); i++) {
            char ptrChar = ptr.charAt(i);
            ptrOccurrences.put(ptrChar,ptrOccurrences.getOrDefault(ptrChar,0)+1);

            if(i != ptr.length()-1) {       // Do not add the last character of PTR in Map
                char strChar = str.charAt(i);
                anagramWindow.put(strChar, anagramWindow.getOrDefault(strChar, 0) + 1);
            }
        }

        for(int i=0,j=ptr.length()-1; j<str.length(); i++,j++) {

            char endChar = str.charAt(j);
            anagramWindow.put(endChar,anagramWindow.getOrDefault(endChar,0)+1);

            if(anagramWindow.equals(ptrOccurrences)) {
                anagramIndices.add(i);
            }

            char startChar = str.charAt(i);
            anagramWindow.put(startChar, anagramWindow.get(startChar)-1);

            if(anagramWindow.get(startChar) == 0) {
                anagramWindow.remove(startChar);
            }
        }
        return anagramIndices;
    }

}

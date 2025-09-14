package com.algorithms.aprenderypractique.Algorithms.Strings;

import com.algorithms.aprenderypractique.BaseTest;

import java.util.*;

/**
 *  https://leetcode.com/problems/group-anagrams
 */
public class GroupAnagrams extends BaseTest {

    /*
        Time: O(N*MLogM)
        Space: O(N*M)

        N: number of words
        M: Max length of words
    */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for(String word : strs) {
            char[] array = word.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
        }

        return map.values().stream().toList();
    }

}

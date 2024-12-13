package com.algorithms.aprenderypractique.Algorithms.Strings.Anagram;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 *      Given a sequence of words, print all anagrams together
 *      Group Anagrams Problem
 *     https://www.geeksforgeeks.org/given-a-sequence-of-words-print-all-anagrams-together/
 */
public class GroupAnagrams extends BaseTest {

    @Test
    public void solution() {
        List<String> list = Arrays.asList("cat","dog","ogd","god","atc");   // {“cat”, “tac”, “act”, ‘”dog”, “god”}
        List<List<String>> result = groupAnagram(list);
        print2DList(result);

        list = Arrays.asList("ate","nat","eat","tan","tea","bat");
        result = groupAnagram(list);
        System.out.println(); print2DList(result);

        list = Arrays.asList("CARS", "REPAID", "DUES", "NOSE", "SIGNED", "LANE", "PAIRED", "ARCS",
                "GRAB", "USED", "ONES", "BRAG", "SUED", "LEAN", "SCAR", "DESIGN");
        result = groupAnagram(list);
        System.out.println(); print2DList(result);
    }

/*
    Time: O(N*M)
    Space: O(N*M)
    N-Words and maximum of M characters
 */
    static List<List<String>> groupAnagram(List<String> list) {
        Map<Map<Character, Integer>, List<String>> map = new HashMap<>();

        for (String str : list) {
            Map<Character, Integer> wordFrequency = countFrequency(str);

            if (map.containsKey(wordFrequency))
                map.get(wordFrequency).add(str);
            else {
                List<String> tempList = new ArrayList<>();
                tempList.add(str);
                map.put(wordFrequency, tempList);
            }
        }

        List<List<String>> result = new ArrayList<>();
        for (Map<Character, Integer> temp : map.keySet())
            result.add(map.get(temp));
        return result;
    }

    static Map<Character, Integer> countFrequency(String str) {
        Map<Character, Integer> tempMap = new HashMap<>();
        for(char c : str.toCharArray()) {
            tempMap.put(c, tempMap.getOrDefault(c,0)+1);
        }
        return tempMap;
    }

    public static void print2DList(List<List<String>> list) {
        list.forEach( nestedList -> {
            Object str = nestedList.stream().map(String::valueOf).collect(Collectors.joining(","));
            System.out.println("[" + str + "]");
        });
    }

}

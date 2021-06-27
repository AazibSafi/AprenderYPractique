package com.algorithms.aprenderypractique.algorithm;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.PrintHelper;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *     Find All Anagrams in a String | Sliding window
 *     Result: Set of First indices of all anagrams
 *     https://www.youtube.com/watch?v=fYgU6Bi2fRg
 */
public class AnagramString_SlidingWindowAlgo extends BaseTest {

    @Test
    public void solution() {
        String str = "abcdebacb";
        String ptr = "cab";

        PrintHelper.printSet(findAllAnagrams(str,ptr));
    }

//    O(N)
    public Set findAllAnagrams(String str, String ptr) {
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

            if(i != ptr.length()-1) {
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

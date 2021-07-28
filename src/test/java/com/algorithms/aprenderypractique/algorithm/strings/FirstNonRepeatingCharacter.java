package com.algorithms.aprenderypractique.algorithm.strings;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * https://www.youtube.com/watch?v=5co5Gvp_-S0&ab_channel=NickWhite
 */
public class FirstNonRepeatingCharacter extends BaseTest {

    @Test
    public void solution() {

//      Through Map
        Assert.assertEquals(new Character('b'),firstNonRepeatingCharacterByMap("aaabcccdeeef"));
        Assert.assertEquals(new Character('c'),firstNonRepeatingCharacterByMap("abcbad"));
        Assert.assertEquals(new Character('_'),firstNonRepeatingCharacterByMap("abcabcabc"));

//      Through Int Array - ASCII
        Assert.assertEquals('b',firstNonRepeatingCharacterByIntArray("aaabcccdeeef"));
        Assert.assertEquals('c',firstNonRepeatingCharacterByIntArray("abcbad"));
        Assert.assertEquals('_',firstNonRepeatingCharacterByIntArray("abcabcabc"));

//      Through Index
        Assert.assertEquals('b',firstNonRepeatingCharacterByIndex("aaabcccdeeef"));
        Assert.assertEquals('c',firstNonRepeatingCharacterByIndex("abcbad"));
        Assert.assertEquals('_',firstNonRepeatingCharacterByIndex("abcabcabc"));
    }

/*    Time Complexity
        Best Case O(1)
        Worst Case O(N)

      More Optimized, No space Complexity
*/
    public char firstNonRepeatingCharacterByIndex(String str) {
        for(char c : str.toCharArray()) {
            if( str.indexOf(c) == str.lastIndexOf(c) ) {
                return c;
            }
        }
        return '_';
    }

/*    Time Complexity:  O(N) + O(N)
      Space: O(26)
*/
    public char firstNonRepeatingCharacterByIntArray(String str) {
        int[] char_count = new int[26];

        for(char c : str.toCharArray()) {
            char_count[c - 'a']++;
        }

        for(char c : str.toCharArray()) {
            if( char_count[c - 'a'] == 1 )
                return c;
        }

        return '_';
    }

/*    Time Complexity:  O(N) + O(M)
        M -> Map size

      Space: O(N)
*/
    public Character firstNonRepeatingCharacterByMap(String str) {
        Map<Character,Integer> countMap = new LinkedHashMap<>();

        for(Character c : str.toCharArray()) {
            countMap.put(c,countMap.getOrDefault(c,0)+1);
        }

        for(Map.Entry<Character,Integer> mapEntry : countMap.entrySet()) {
            if(mapEntry.getValue() == 1) {
                return mapEntry.getKey();
            }
        }

        return '_';
    }

}

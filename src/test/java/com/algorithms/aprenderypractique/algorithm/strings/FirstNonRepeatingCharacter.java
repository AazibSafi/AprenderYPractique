package com.algorithms.aprenderypractique.algorithm.strings;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * https://www.youtube.com/watch?v=5co5Gvp_-S0&ab_channel=NickWhite
 */
public class FirstNonRepeatingCharacter extends BaseTest {

    @Test
    public void solution() {

        System.out.println("------ Through Map");
        System.out.println(firstNonRepeatingCharacterByMap("aaabcccdeeef"));
        System.out.println(firstNonRepeatingCharacterByMap("abcbad"));
        System.out.println(firstNonRepeatingCharacterByMap("abcabcabc"));

        System.out.println("------ Through Int Array - ASCII");
        System.out.println(firstNonRepeatingCharacterByIntArray("aaabcccdeeef"));
        System.out.println(firstNonRepeatingCharacterByIntArray("abcbad"));
        System.out.println(firstNonRepeatingCharacterByIntArray("abcabcabc"));

        System.out.println("------ Through Index");
        System.out.println(firstNonRepeatingCharacterByIndex("aaabcccdeeef"));
        System.out.println(firstNonRepeatingCharacterByIndex("abcbad"));
        System.out.println(firstNonRepeatingCharacterByIndex("abcabcabc"));
    }

/*    Time Complexity
        Best Case O(1)
        Worst Case O(N)

      More Optimized, No space Complexity
*/
    public Character firstNonRepeatingCharacterByIndex(String str) {

        for(Character c : str.toCharArray()) {
            if( str.indexOf(c) == str.lastIndexOf(c) ) {
                return c;
            }
        }
        return '_';
    }

/*    Time Complexity:  O(N) + O(N)
      Space: O(26)
*/
    public Character firstNonRepeatingCharacterByIntArray(String str) {
        int[] char_count = new int[26];

        for(Character c : str.toCharArray()) {
            char_count[c - 'a']++;
        }

        for(Character c : str.toCharArray()) {
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

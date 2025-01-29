package com.algorithms.aprenderypractique.Algorithms.Trie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 *      https://leetcode.com/problems/counting-words-with-a-given-prefix

     Let n be the total number of input words
     l be the maximum length of any word
     m be the length of the prefix string
 */
public class CountingWordsWithAGivenPrefix {

    @ParameterizedTest
    @MethodSource("testCases")
    void testPrefixCount(String[] words, String prefix, int expected) {
        CountingWordsWithAGivenPrefix currClass = new CountingWordsWithAGivenPrefix();
        Assertions.assertEquals(expected, currClass.prefixCount1(words, prefix));   // Approach#1
        Assertions.assertEquals(expected, currClass.prefixCount2(words, prefix));   // Approach#2
        Assertions.assertEquals(expected, currClass.prefixCount(words, prefix));    // // Approach#3
    }

    static Stream<Arguments> testCases() {
        return Stream.of(
                // Basic test cases
                Arguments.of(new String[]{"abc", "abcgl", "cdf", "abcd", "lmn", "cdhiuo"}, "abc", 3),
                Arguments.of(new String[]{"pay", "attention", "practice", "attend"}, "at", 2),
                Arguments.of(new String[]{"leetcode", "win", "loops", "success"}, "code", 0),
                Arguments.of(new String[]{"faeu", "fauq", "fanc", "fap", "favkigbbsk", "faex", "fag", "faltrf", "fabeckg", "faem", "fahh", "nyyqmdv", "faaei", "fah", "fayr", "fazon", "fairpv", "fanz", "fap", "fanfxo", "fadzmrtjv", "famf", "faom"}, "fa", 22),
                // Additional test cases
                Arguments.of(new String[]{"apple"}, "app", 1), // Single word matches prefix
                Arguments.of(new String[]{"banana", "cherry", "date"}, "app", 0), // No words match prefix
                Arguments.of(new String[]{"app", "apple", "application", "appetizer"}, "app", 4), // All words match prefix
                Arguments.of(new String[]{"App", "apple", "APPle"}, "app", 1), // Case sensitivity
                Arguments.of(new String[]{}, "prefix", 0), // Empty word list
                Arguments.of(new String[]{"short", "list", "of", "words"}, "thisisaverylongprefix", 0), // Prefix longer than any word
                Arguments.of(new String[]{"cat", "catalog", "catch", "dog"}, "cat", 3), // Partial overlaps
                Arguments.of(new String[]{"endingfix", "ending", "prefixed"}, "fix", 0), // Prefix at the end
                Arguments.of(new String[]{"repeat", "repeat", "repeat"}, "rep", 3), // Duplicate words
                Arguments.of(new String[]{"@start", "@starting", "middle", "end@"}, "@st", 2), // Special characters
                Arguments.of(new String[]{"123abc", "1234", "12345", "5678"}, "123", 3), // Numeric prefixes
                Arguments.of(new String[]{"abc123", "123abc", "a1b2c3", "abcdef"}, "abc", 2), // Mixed characters
                // Edge cases
                Arguments.of(new String[]{}, "", 0), // Empty prefix and list
                Arguments.of(new String[]{"abc", "abc", "abc"}, "abc", 3) // Words containing only the prefix
        );
    }

/*
    Approach#1: Brute Force
    Time: O(n.m)
    Space: O(1)
*/
    public int prefixCount1(String[] words, String pref) {
        int count=0;
        for(String word : words) {      // O(n)
            if(hasPrefix(word, pref))   // O(m)
                count++;
        }
        return count;
    }

    public boolean hasPrefix(String word, String pref) {
        int i=0;
        while(i<word.length() && i<pref.length()
                && word.charAt(i) == pref.charAt(i)) {
            i++;
        }
        return i == pref.length();
    }

/*
    Approach#2: Using Builtin Method
    Time: O(n.m)
    Space: O(1)
*/
    public int prefixCount2(String[] words, String pref) {
        return (int) Arrays.stream(words)       // O(n)
                .filter(word -> word.startsWith(pref))  // O(m)
                .count();
    }


/*
    Approach#3: Trie        [Efficient solution]
    Time: O(n.l + m)
    Space: O(n.l) -> Trie space
*/
    public int prefixCount(String[] words, String pref) {
        DictionaryWithDuplicates dict = new DictionaryWithDuplicates();
        for(String word : words) {      // O(n)
            dict.add(word);             // O(l)
        }
        return dict.findPrefixWordsCount(pref); // O(m)
    }
}

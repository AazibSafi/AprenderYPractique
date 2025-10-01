package com.algorithms.aprenderypractique.Algorithms.Strings.WordBreak;

import com.algorithms.aprenderypractique.Algorithms.Datastructure.Trie;
import com.algorithms.aprenderypractique.Algorithms.Trie.Dictionary;
import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  https://leetcode.com/problems/word-break
 *  https://www.youtube.com/watch?v=LPs6Qo5qlJM
 */
public class WordBreak extends BaseTest {

    @Test
    public void solution() {
        Assert.assertTrue(wordBreak("leetcode", Arrays.asList("leet","code")));
        Assert.assertTrue(wordBreak("applepenapple", Arrays.asList("apple","pen")));
        Assert.assertFalse(wordBreak("catsandog", Arrays.asList("cats","dog","sand","and","cat")));
    }

/*
    Approach#1: Recursive
    Naive approach - Not optimized
*/
    public boolean wordBreak1(String s, List<String> wordDict) {
        Map<String, Boolean> cache = new HashMap<>();
        return wordBreak1(s, wordDict, cache);
    }

    public boolean wordBreak1(String s, List<String> wordDict, Map<String, Boolean> cache) {
        if(wordDict.contains(s))    return true;
        if(cache.containsKey(s))    return cache.get(s);

        for(int i=0; i<s.length(); i++) {
            if( wordBreak1(s.substring(0,i), wordDict) && wordBreak1(s.substring(i), wordDict) ) {
                cache.put(s, true);
                return true;
            }
        }
        cache.put(s, false);
        return false;
    }

/*
    Appraoch#2: Bottom-Up Dynamic Programming
    Time: O(n⋅m⋅k)
    Space: O(n)
    n => length of s
    m => length of wordDict
    k => average length of the words in wordDict
*/
    public boolean wordBreak2(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()];

        for(int i=0; i<s.length(); i++) {
            for(String word : wordDict) {
                int wLen = word.length();

                // Handle out of bounds case
                if (i < wLen - 1)
                    continue;

                // Check if it is a first word || previous word was also found
                if (i == wLen - 1 || dp[i - wLen]) {
                    if (s.substring(i - wLen + 1, i + 1).equals(word)) {    // Word matching ending at index i of substring s
                        dp[i] = true;
                        break;
                    }
                }
            }
        }
        return dp[dp.length-1];
    }

/*
    Appraoch#3: Trie Optimization
    Time: O(n^2 + m⋅k)
    Space: O(n + m.k) => The dp array takes O(n) space. The trie can have up to m⋅k nodes in it.
    n => length of s
    m => length of wordDict
    k => average length of the words in wordDict
*/
    public boolean wordBreak(String s, List<String> wordDict) {
        Dictionary dict = new Dictionary();
        dict.insert(wordDict);              // O(m.n)

        boolean[] dp = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {  // O(n)
            if (i == 0 || dp[i - 1]) {
                Trie curr = dict.getRoot();
                for (int j = i; j < s.length(); j++) {  // O(n)
                    char c = s.charAt(j);

                    if (!curr.children.containsKey(c))
                        break;  // No words exist

                    curr = curr.children.get(c);

                    if (curr.isWord)
                        dp[j] = true;
                }
            }
        }

        return dp[s.length() - 1];
    }

}

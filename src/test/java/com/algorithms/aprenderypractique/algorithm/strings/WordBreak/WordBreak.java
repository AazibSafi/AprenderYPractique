package com.algorithms.aprenderypractique.algorithm.strings.WordBreak;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  https://leetcode.com/problems/word-break/
 *  https://www.youtube.com/watch?v=LPs6Qo5qlJM
 */
public class WordBreak extends BaseTest {

    @Test
    public void solution() {
        Assert.assertTrue(wordBreak("leetcode", Arrays.asList("leet","code")));
        Assert.assertTrue(wordBreak("applepenapple", Arrays.asList("apple","pen")));
        Assert.assertFalse(wordBreak("catsandog", Arrays.asList("cats","dog","sand","and","cat")));
    }

    Map<String, Boolean> cache = new HashMap<>();
    public boolean wordBreak(String s, List<String> wordDict) {
        if(wordDict.contains(s))    return true;

        if(cache.containsKey(s))    return cache.get(s);

        for(int i=0; i<s.length(); i++) {
            if(wordBreak(s.substring(0,i), wordDict) && wordBreak(s.substring(i), wordDict)) {
                cache.put(s, true);
                return true;
            }
        }
        cache.put(s, false);
        return false;
    }

}

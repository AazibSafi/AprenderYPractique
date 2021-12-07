package com.algorithms.aprenderypractique.algorithm.strings.WordBreak;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.CommonHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *      https://leetcode.com/problems/word-break-ii/
 *      https://www.youtube.com/watch?v=jQJyWcRPEpE
 */
public class WordBreak_II extends BaseTest {

    @Test
    public void solution() {
        List<String> wordDict = Arrays.asList("cat","cats","and","sand","dog");
        List<String> output = Arrays.asList("cats and dog","cat sand dog");
        Assert.assertTrue(CommonHelper.isEquals(output, wordBreak("catsanddog", wordDict)));
        //Assert.assertArrayEquals(output, wordBreak("catsanddog", wordDict));
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();

        if(wordDict.contains(s))        result.add(s);

        for(int i=1; i<=s.length(); i++) {
            String left = s.substring(0, i);
            if(wordDict.contains(left)) {
                List<String> subList = wordBreak(s.substring(i), wordDict);
                for (String str : subList) {
                    result.add(left + " " + str);
                }
            }
        }

        return result;
    }

}

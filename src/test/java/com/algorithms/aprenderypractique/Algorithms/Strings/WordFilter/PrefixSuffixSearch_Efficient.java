package com.algorithms.aprenderypractique.Algorithms.Strings.WordFilter;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 *  https://leetcode.com/problems/prefix-and-suffix-search/
 *  Logic: https://www.youtube.com/watch?v=X630KoSDkeQ
 *  Code: https://www.youtube.com/watch?v=3JVlE66WxW0
 *
 *   Time: O(n*l*l)
 *    n -- number of words
 *    l -- length of a word
 *
 *   This Solution is efficient if there are multiple request of calling search function
 */
public class PrefixSuffixSearch_Efficient extends BaseTest {

    @Test
    public void solution() {
        wordFilter(new String[]{"abcd"});
        Assert.assertEquals(0 , search("a", "d") );

        wordFilter(new String[]{"apple"});
        Assert.assertEquals(0 , search("a", "e") );

        wordFilter(new String[]{"cabaabaaaa","ccbcababac","bacaabccba","bcbbcbacaa","abcaccbcaa","accabaccaa","cabcbbbcca","ababccabcb","caccbbcbab","bccbacbcba"});
        Assert.assertEquals(9 , search("bccbacbcba","a"));
        Assert.assertEquals(4 , search("ab","abcaccbcaa"));
        Assert.assertEquals(5 , search("a","aa"));
        Assert.assertEquals(0 , search("cabaaba","abaaaa"));
        Assert.assertEquals(8 , search("cacc","accbbcbab"));
        Assert.assertEquals(1 , search("ccbcab","bac"));
        Assert.assertEquals(2 , search("bac","cba"));
        Assert.assertEquals(5 , search("ac","accabaccaa"));
        Assert.assertEquals(3 , search("bcbb","aa"));
        Assert.assertEquals(1 , search("ccbca","cbcababac"));
    }

    Map<String, Integer> prefixTable;

    public void wordFilter(String[] words) {
        prefixTable = new HashMap<>();
        int index = 0;
        for(String word : words) {
            for(int i=0; i<=word.length(); i++) {
                for(int j=0; j<=word.length(); j++) {
                    String prefix_suffix = word.substring(0,i)+"#"+word.substring(j);
                    prefixTable.put(prefix_suffix, index);
                }
            }
            index++;
        }
    }

    public int search(String prefix, String suffix) {
        return prefixTable.getOrDefault(prefix+"#"+suffix, -1);
    }

}

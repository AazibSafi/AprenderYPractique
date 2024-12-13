package com.algorithms.aprenderypractique.Algorithms.Strings.WordFilter;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/prefix-and-suffix-search/
 *  https://www.youtube.com/watch?v=X630KoSDkeQ
 */
public class PrefixSuffixSearch extends BaseTest {

    String[] words;

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

    public void wordFilter(String[] words) {
        this.words = words;
    }

/*
    Time: O(n * l)
    n -- number of words
    l -- length of a word

    This Solution is not efficient
    Time Limit Exceeded
 */
    public int search(String prefix, String suffix) {
        for(int i=words.length-1; i>=0; i--) {
            if(words[i].startsWith(prefix) && words[i].endsWith(suffix))
                return i;
        }
        return -1;
    }

}

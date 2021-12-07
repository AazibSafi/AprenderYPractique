package com.algorithms.aprenderypractique.algorithm.Trie;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class DictionaryTest extends BaseTest {

    @Test
    public void testInsert() {
        Dictionary dictionary = new Dictionary();
        String[] words = new String[]{"abc", "abcgl", "cdf", "abcd", "lmn", "cdhiuo"};
        dictionary.insert(words);
        dictionary.printTrie();
    }

    @Test
    public void testSearch() {
        Dictionary dictionary = new Dictionary();
        String[] words = new String[]{"abc", "abcgl", "cdf", "abcd", "lmn", "cdhiuo"};
        dictionary.insert(words);

        Assert.assertTrue(dictionary.search("abc"));
        Assert.assertTrue(dictionary.search("cdf"));
        Assert.assertTrue(dictionary.startsWith("cd"));
    }

    @Test
    public void testGetWordsWithPrefix() {
        Dictionary dictionary = new Dictionary();
        String[] dict = new String[]{"abc", "abcgl", "cdf", "abcd", "lmn", "cdhiuo"};
        dictionary.insert(dict);

        List<String> words = dictionary.getWordsWithPrefix("cd");
        System.out.println("Prefix Words: " + words.stream().collect(Collectors.joining(", ","[","]")));
    }

    @Test
    public void testPrefixExistInWord() {
        Dictionary dictionary = new Dictionary();
        String[] words = new String[]{"abc", "abcgl", "abcd"};
        dictionary.insert(words);

        Assert.assertEquals("abc",dictionary.findShortestPrefix("abcklm"));
        Assert.assertEquals("abc",dictionary.findShortestPrefix("abcdklm"));


        dictionary.insert("cdf");
        dictionary.insert("cdfg");
        Assert.assertEquals("cdf",dictionary.findShortestPrefix("cdfpol"));
        Assert.assertEquals("cdf",dictionary.findShortestPrefix("cdfgxz"));
    }

}

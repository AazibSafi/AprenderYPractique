package com.algorithms.aprenderypractique.Algorithms.Trie;

import com.algorithms.aprenderypractique.Algorithms.Datastructure.Trie;
import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
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
        System.out.println("Prefix Words with cd: " + words.stream().collect(Collectors.joining(", ","[","]")));

        words = dictionary.getWordsWithPrefix("ab");
        System.out.println("Prefix Words with ab: " + words.stream().collect(Collectors.joining(", ","[","]")));

        words = dictionary.getWordsWithPrefix("");      // Edge Case
        System.out.println("Prefix Words with EMPTY: " + words.stream().collect(Collectors.joining(", ","[","]")));

        // Duplicate word of "fap"
        dict = new String[]{"faeu","fauq","fanc","fap","favkigbbsk","faex","fag","faltrf","fabeckg","faem","fahh","nyyqmdv","faaei","fah","fayr","fazon","fairpv","fanz","fap","fanfxo","fadzmrtjv","famf","faom"};
        dictionary.insert(dict);
        words = dictionary.getWordsWithPrefix("fa");
        System.out.println("Prefix Words with fa: " + words.stream().collect(Collectors.joining(", ","[","]")));
        System.out.println("Prefix Words Size with fa: " + words.size());   // 22
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

package com.algorithms.aprenderypractique.Algorithms.Trie;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 *  https://leetcode.com/problems/replace-words
 */
public class ReplaceWords extends BaseTest {

    Dictionary dictionaryData = new Dictionary();

    @Test
    public void solution() {
        List<String> dictionary = Arrays.asList("cat","bat","rat");
        String sentence = "the cattle was rattled by the battery";
        Assert.assertEquals("the cat was rat by the bat" , replaceWords(dictionary, sentence) );
    }

/*
    Time: O(d.w + s.w)
    Space: O(d.w + s.w)
    d => number of words in the dictionary
    s => number of words in the sentence
    w => the average length of each word
 */
    public String replaceWords(List<String> dictionary, String sentence) {
        dictionaryData.insert(dictionary);  // O(d⋅w)

        String[] words = sentence.split(" ");

        for(int i=0; i<words.length; i++) {     // O(s⋅w)
            String shortestRoot = dictionaryData.findShortestPrefix(words[i]);
            if(!shortestRoot.isEmpty())
                words[i] = shortestRoot;
        }

        return String.join(" ",words);
    }

}

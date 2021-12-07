package com.algorithms.aprenderypractique.algorithm.Trie;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 *  https://leetcode.com/problems/replace-words/
 */
public class ReplaceWords_Successor extends BaseTest {

    Dictionary dictionaryData = new Dictionary();

    @Test
    public void solution() {
        List<String> dictionary = Arrays.asList("cat","bat","rat");
        String sentence = "the cattle was rattled by the battery";
        Assert.assertEquals("the cat was rat by the bat" , replaceWords(dictionary, sentence) );
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        dictionaryData.insert(dictionary);

        String[] words = sentence.split(" ");

        for(int i=0; i<words.length; i++) {
            String successor = dictionaryData.findShortestPrefix(words[i]);
            if(!successor.isEmpty())
                words[i] = successor;
        }

        return String.join(" ",words);
    }

}

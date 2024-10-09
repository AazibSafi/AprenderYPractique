package com.algorithms.aprenderypractique.algorithm.strings;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 *      https://leetcode.com/problems/reverse-words-in-a-string
 *      https://www.educative.io/reverse-words-in-a-sentence
 */
public class ReverseWordsInAString extends BaseTest {

    @Test
    public void test() {
        Assert.assertEquals("world hello", reverseWords("hello world"));
        Assert.assertEquals("world hello", reverseWords("  hello world  "));
        Assert.assertEquals("blue is sky the", reverseWords("the sky is blue"));
        Assert.assertEquals("example good a", reverseWords("a good   example"));
    }

    public String reverseWords(String s) {
        List<String> list = new ArrayList<>();
        String[] words = s.trim().split("\\s+");

        for(int i=words.length-1; i>=0; i--)
            list.add(words[i]);

        return String.join(" ", list);
    }

}

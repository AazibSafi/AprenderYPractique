package com.algorithms.aprenderypractique.Algorithms.Trie;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/design-add-and-search-words-data-structure
 */
public class Add_SearchWords extends BaseTest {

    @Test
    public void solution() {
        addWord("bad");
        addWord("dad");
        addWord("mad");
        addWord("pad");
        Assert.assertTrue( search("dad") );
        Assert.assertTrue( search("d..") );
        Assert.assertTrue( search(".ad") );
        Assert.assertFalse( search(".a.") );
    }

    private final Dictionary dictionary;

    public Add_SearchWords() {
        dictionary = new Dictionary();
    }

    public void addWord(String word) {
        dictionary.insert(word);
    }

    public boolean search(String word) {
        return dictionary.searchWithDot(word);
    }

}

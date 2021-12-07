package com.algorithms.aprenderypractique.algorithm.Trie;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.algorithm.datastructure.Trie;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 *  https://leetcode.com/problems/design-add-and-search-words-data-structure/
 */
public class Add_SearchWords extends BaseTest {

    Dictionary dictionary = new Dictionary();

    @Test
    public void solution() {
        WordDictionary obj = new WordDictionary();
        obj.addWord("word");
        Assert.assertTrue( obj.search("word") );
    }

    class WordDictionary {

        public WordDictionary() {}

        public void addWord(String word) {
            dictionary.insert(word);
        }

        public boolean search(String word) {
            Trie curr = dictionary.getTrie();
            return search(curr, word);
        }

        public boolean search(Trie curr, String word) {
            for(int i=0; i<word.length(); i++) {
                char ch = word.charAt(i);
                if(ch == '.') {
                    for(Map.Entry<Character, Trie> child : curr.children.entrySet()) {
                        if(search(child.getValue(), word.substring(i+1)))
                            return true;
                    }
                    return false;
                }
                else {
                    if (!curr.children.containsKey(ch)) return false;
                    curr = curr.children.get(ch);
                }
            }

            return curr.isWord;         // Word Found
        }
    }

}

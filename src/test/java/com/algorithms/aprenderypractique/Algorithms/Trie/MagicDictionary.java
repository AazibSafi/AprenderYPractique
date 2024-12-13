package com.algorithms.aprenderypractique.Algorithms.Trie;

/**
 *      https://leetcode.com/problems/implement-magic-dictionary/
 *      Code Ref: https://protegejj.gitbook.io/algorithm-practice/leetcode/trie/676-implement-magic-dictionary
 */
public class MagicDictionary {

    Dictionary dictionaryTrie = new Dictionary();

    public void buildDict(String[] dictionary) {
        for(String word : dictionary) {
            dictionaryTrie.insert(word);
        }
    }

/*
    This Solution has Time Limit Exceeded
 */
    public boolean search(String word) {
        StringBuilder str = new StringBuilder(word);
        for(int i=0; i<str.length(); i++) {
            char ch = str.charAt(i);
            for(char letter='a'; letter<='z'; letter++) {       // Iterating all 26 Alphabets
                if(letter == ch)
                    continue;

                str.insert(i, letter);
                if(dictionaryTrie.search(str.toString()))
                    return true;
            }
            str.insert(i, ch);
        }
        return false;
    }

}

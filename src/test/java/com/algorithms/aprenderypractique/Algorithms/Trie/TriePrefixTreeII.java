package com.algorithms.aprenderypractique.Algorithms.Trie;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *  https://leetcode.com/problems/implement-trie-ii-prefix-tree
 *
 *  prerequisite: https://leetcode.com/problems/implement-trie-prefix-tree
 *      @see Dictionary
 *
 *  @see CountingWordsWithAGivenPrefix
 *
 *      Time complexity: O(N)
 *      The time complexity for each method is O(N) since we iterate over the characters of the word.
 *
 *      Space complexity: O(N⋅M)
 *      Each character can create a new node in the trie when the insert method is called.
 *      For a word of length N we will create at most N TrieNodes.
 *      If there are M words inserted into the trie we will have to create at most N⋅M TrieNodes.
 */
public class TriePrefixTreeII {
    PrefixTree tree;

    TriePrefixTreeII() {    tree = new PrefixTree();  }

    public void insert(String word) {
        PrefixTree root = tree;
        for(char ch : word.toCharArray()) {
            if(!root.children.containsKey(ch))
                root.children.put(ch, new PrefixTree());

            root = root.children.get(ch);
            root.wordPassingCount++;
        }
        root.wordEndingCount++;
        root.isWord = true;
    }

    public int countWordsEqualTo(String word) {
        PrefixTree root = tree;
        for(char ch : word.toCharArray()) {
            if(!root.children.containsKey(ch))
                return 0;
            root = root.children.get(ch);
        }
        return root.wordEndingCount;
    }

    public int countWordsStartingWith(String prefix) {
        PrefixTree root = tree;
        for(char ch : prefix.toCharArray()) {
            if(!root.children.containsKey(ch))
                return 0;
            root = root.children.get(ch);
        }
        return root.wordPassingCount;
    }

    public void erase(String word) {
        PrefixTree root = tree;
        for(char ch : word.toCharArray()) {
            root = root.children.get(ch);
            root.wordPassingCount--;
        }
        root.wordEndingCount--;
    }
}

class PrefixTree {
    Map<Character, PrefixTree> children;
    boolean isWord;
    int wordPassingCount;   // Same as Prefix Count
    int wordEndingCount;
    public PrefixTree() {
        children = new HashMap<>();
    }
}
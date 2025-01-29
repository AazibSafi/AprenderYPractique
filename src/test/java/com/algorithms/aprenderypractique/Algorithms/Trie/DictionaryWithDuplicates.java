package com.algorithms.aprenderypractique.Algorithms.Trie;

import java.util.HashMap;
import java.util.Map;

/**
 *  Dictionary with Duplicates allowed.
 *  E.g; Contacts application. Same name can be in the contacts book
 */
public class DictionaryWithDuplicates {    // E.g; Contacts Book
    private Trie trie;

    public DictionaryWithDuplicates() { trie = new Trie(); }

    public void add(String word) {
        Trie root = trie;

        for(char letter : word.toCharArray()) {
            if(!root.children.containsKey(letter))
                root.children.put(letter, new Trie());

            root = root.children.get(letter);
            root.count++;
        }
        root.isWord = true;
    }

    public int findPrefixWordsCount(String prefix) {
        Trie root = trie;

        for(char ch : prefix.toCharArray()) {
            if(!root.children.containsKey(ch))
                return 0;       // Prefix not found
            root = root.children.get(ch);
        }
        return root.count;  // Return count at last node
    }
}

class Trie {
    Map<Character, Trie> children;
    boolean isWord;
    int count;          // Same as Prefix Count -> Number of strings having prefix till this node
    public Trie() {
        children = new HashMap<>();
        isWord = false;
        count = 0;
    }
}

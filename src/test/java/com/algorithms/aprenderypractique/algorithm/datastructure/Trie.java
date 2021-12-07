package com.algorithms.aprenderypractique.algorithm.datastructure;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *  Trie Data Structure
 */
public class Trie {

    public Map<Character, Trie> children;       // we can also use char[26]
    public boolean isWord;

    public Trie() {
        this.children = new LinkedHashMap<>();
        this.isWord = false;
    }

}

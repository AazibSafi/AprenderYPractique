package com.algorithms.aprenderypractique.practice;

import java.util.LinkedHashMap;
import java.util.Map;

public class Trie {

    Map<Character,Trie> children;
    boolean endOfWord;

    public Trie() {
        this.children = new LinkedHashMap<>();
        this.endOfWord = true;
    }
}

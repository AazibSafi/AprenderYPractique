package com.algorithms.aprenderypractique.algorithm.Trie;

import com.algorithms.aprenderypractique.algorithm.datastructure.Trie;
import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  Trie Data Structure
 *  Dictionary Implementation
 */
public class Dictionary {

    Trie trie;

    public Dictionary() {
        trie = new Trie();
    }

/*
    Inserts list of words in a dictionary
    Time: O(l + n)
    n -- no of words
    l -- max length of a word
 */
    public void insert(String[] dictionary) {
        for(String word : dictionary)
            insert(word);
    }

    public void insert(List<String> dictionary) {
        dictionary.forEach(this::insert);
    }

/*
    Inserts a given word in a dictionary
    Time: O(n) -- length of word
 */
    public void insert(String word) {
        Trie curr = trie;

        for(int i=0; i<word.length(); i++) {
            char ch = word.charAt(i);
            if (!curr.children.containsKey(ch)) {
                curr.children.put(ch, new Trie());
            }
            curr = curr.children.get(ch);
        }

        curr.isWord = true;         // End of Word
    }

/*
    Search a given word in the dictionary
 */
    public boolean search(String word) {
        Trie curr = trie;

        for(int i=0; i<word.length(); i++) {
            char ch = word.charAt(i);
            if (!curr.children.containsKey(ch))         return false;
            curr = curr.children.get(ch);
        }

        return curr.isWord;         // Word Found
    }

/*
    Check if a given word contains a substring prefix that is present in dictionary AS A COMPLETE WORLD
    Returns the shortest prefix if exist
 */
    public String findShortestPrefix(String word) {
        StringBuilder prefix = new StringBuilder();
        Trie curr = trie;

        for(int i=0; i<word.length(); i++) {
            char ch = word.charAt(i);
            if (!curr.children.containsKey(ch))         return Strings.EMPTY;

            prefix.append(ch);
            curr = curr.children.get(ch);

            if(curr.isWord)     return prefix.toString();
        }

        return curr.isWord ? prefix.toString() : Strings.EMPTY;
    }

/*
    Returns true if the dictionary contains any word with a given prefix
 */
    public boolean startsWith(String prefix) {
        Trie curr = trie;

        for(int i=0; i<prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if (!curr.children.containsKey(ch))         return false;
            curr = curr.children.get(ch);
        }

        return true;         // Prefix Found
    }

/*
    Return all words from a dictionary that contains a given prefix
 */
    public List<String> getWordsWithPrefix(String prefix) {
        Trie head = trie;

        for(int i=0; i<prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if (!head.children.containsKey(ch))
                return new ArrayList<>();       // Prefix not found
            head = head.children.get(ch);
        }

        List<String> words = new ArrayList<>();
        dfs(head, prefix, words);
        return words;
    }

    public void printTrie() {
        List<String> collectWords = new ArrayList<>();
        dfs(trie, "", collectWords);
        System.out.println("Dictionary Words: " + collectWords.stream().collect(Collectors.joining(", ","[","]")) );
    }

    public void dfs(Trie head, String prefix, List<String> collectWords) {
        if(head.isWord)
            collectWords.add(prefix);       // This can be directly System.print to display the word - No need for List

        head.children.forEach((key, value) -> dfs(value, prefix + key.toString(), collectWords));
    }

    public Trie getTrie() {
        return trie;
    }

}

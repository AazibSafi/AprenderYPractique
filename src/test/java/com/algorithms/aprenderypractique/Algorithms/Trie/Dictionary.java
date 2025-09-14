package com.algorithms.aprenderypractique.Algorithms.Trie;

import com.algorithms.aprenderypractique.Algorithms.Datastructure.Trie;
import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  Trie Data Structure
 *  Dictionary Implementation
 *
 *  https://leetcode.com/problems/implement-trie-prefix-tree
 */
public class Dictionary {

    private final Trie trie;

    public Dictionary() {
        trie = new Trie();
    }

    public Trie getRoot() {
        return trie;
    }

/**
    Inserts list of words in a dictionary
    Time: O(l.n)
    n -- no of words
    l -- max length of a word
 */
    public void insert(String[] dictionary) {
        Arrays.stream(dictionary).forEach(this::insert);
    }

    public void insert(List<String> dictionary) {
        dictionary.forEach(this::insert);
    }

/**
    Inserts a given word in a dictionary
    Time: O(n) => Each operation involves examining or creating a node until the end of the word..
    Space: O(n) => In the worst case, each newly inserted key might require adding m new nodes, resulting in O(m) space usage.
    n is the length of the word/key
 */
    public void insert(String word) {
        Trie curr = trie;
        for(char ch : word.toCharArray()) {
            curr = curr.children.computeIfAbsent(ch, k -> new Trie());

            if (!curr.children.containsKey(ch)) {
                curr.children.put(ch, new Trie());
            }
            curr = curr.children.get(ch);
        }

        curr.isWord = true;         // End of Word
    }

/**
    Search a given word in the dictionary
    Time: O(n) => Each step involves searching for the next character of the key, requiring m operations in the worst case.
    Space: O(1)
 */
    public Trie searchPrefix(String word) {
        Trie curr = trie;

        for(char ch : word.toCharArray()) {
            if (!curr.children.containsKey(ch))     return null;
            curr = curr.children.get(ch);
        }

        return curr;
    }

    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isWord;         // Word Found
    }

    public boolean startsWith(String prefix) {
        Trie node = searchPrefix(prefix);
        return node != null;         // Prefix Found - Not necessary to be the end of a word
    }

/*
    Check if a given word contains a substring prefix that is present in dictionary AS A COMPLETE WORD
    Returns the shortest prefix or shortest root if exist
 */
    public String findShortestPrefix(String word) {
        StringBuilder prefix = new StringBuilder();
        Trie curr = trie;

        for(char ch : word.toCharArray()) {
            if (!curr.children.containsKey(ch))         return Strings.EMPTY;

            prefix.append(ch);
            curr = curr.children.get(ch);

            if(curr.isWord)     return prefix.toString();
        }

        return prefix.toString();
    }

/*
    Return all words from a dictionary that contains a given prefix
 */
    public List<String> getWordsWithPrefix(String prefix) {
        if(prefix.isEmpty())
            return new ArrayList<>();       // At-least one character should be there in prefix

        Trie head = trie;

        for(char ch : prefix.toCharArray()) {
            if (!head.children.containsKey(ch))
                return new ArrayList<>();       // Prefix not found
            head = head.children.get(ch);
        }

        List<String> words = new ArrayList<>();
        dfs(head, prefix, words);
        return words;
    }
    
    public boolean searchWithDot(String word) {
        Trie root = trie;
        return searchWithDot(root, word);
    }

/**
    Time: O(M)
    Space: O(1)
 */
    public boolean searchWithDot(Trie curr, String word) {
        for(int i=0; i<word.length(); i++) {
            char ch = word.charAt(i);
            if(ch == '.') {
                for(Trie child : curr.children.values()) {
                    if(searchWithDot(child, word.substring(i+1)))
                        return true;
                }
                return false;
            }
            else {
                if (!curr.children.containsKey(ch))
                    return false;
                curr = curr.children.get(ch);
            }
        }

        return curr.isWord;         // Word Found
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

}

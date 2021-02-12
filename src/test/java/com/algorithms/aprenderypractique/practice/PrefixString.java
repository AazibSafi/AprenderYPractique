package com.algorithms.aprenderypractique.practice;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

import java.util.Map;

public class PrefixString extends BaseTest {

    @Test
    public void test() {
        Trie trie = new Trie();

        addChildren(trie,"Hello");
        addChildren(trie,"Hely");
        addChildren(trie,"Hel");
        addChildren(trie,"He");
        addChildren(trie,"abc");
        addChildren(trie,"adf");
        addChildren(trie,"HelSinki");
        addChildren(trie,"Helm");
//        System.out.println(search(trie,"Hel"));
//        System.out.println(search(trie,"Hhaha"));
//        System.out.println(search(trie,"He"));
//        System.out.println(search(trie,"kaka"));
//        System.out.println(search(trie,"Helo"));
//        System.out.println(search(trie,"HelSinki"));
//        System.out.println(search(trie,"Het"));
        System.out.println("Printing Trie");
        //printTrei(trie);
        System.out.println("Yoyo");

        printPrefixItems(trie,"Hel");
    }

    public void addChildren(Trie trie, String word) {
        if(word.isEmpty()) {
            trie.endOfWord = true;
            return;
        }

        if(trie.children.get(word.charAt(0)) == null) {
            trie.children.put(word.charAt(0),new Trie());
            trie.endOfWord = false;
        }

        addChildren(trie.children.get(word.charAt(0)), word.substring(1));
    }

    public boolean search(Trie trie, String word) {
        if(word.length()==0 && trie.endOfWord) {
            return true;
        }

        if(trie.children.get(word.charAt(0)) == null) {
            return false;
        }

        return search(trie.children.get(word.charAt(0)),word.substring(1));
    }

    // O(K) + O(N)
    public void printPrefixItems(Trie trie, String prefix) {
        for(int i=0;i<prefix.length();i++) {
            if (trie.children.get(prefix.charAt(i)) == null) {
                return;
            }
            trie = trie.children.get(prefix.charAt(i));
        }
        printTrei_DFS(trie,prefix);
    }

    public void printTrei_DFS(Trie trie, String word) {
        if (trie.endOfWord) {
            System.out.println(word);
        }
        for(Map.Entry<Character, Trie> children : trie.children.entrySet()) {
            String prefix = word + children.getKey().toString();
            printTrei_DFS(children.getValue(),prefix);
        }
    }

    public void printTrei(Trie trie) {
        printTrei_DFS(trie,"");
    }

}

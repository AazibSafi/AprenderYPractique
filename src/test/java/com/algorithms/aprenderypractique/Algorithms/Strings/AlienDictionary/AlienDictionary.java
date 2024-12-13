package com.algorithms.aprenderypractique.Algorithms.Strings.AlienDictionary;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 *  https://leetcode.com/problems/alien-dictionary
 *  https://www.youtube.com/watch?v=6kTZYvNNyps
 *
 *  -- Hard
 *
 * @see com.algorithms.aprenderypractique.Algorithms.Graph.CourseSchedule.CourseSchedule_II_KahnsAlgo
 */
public class AlienDictionary extends BaseTest {

    @Test
    public void test() {
        String[] words = new String[]{"wrt", "wrf", "er", "ett", "rftt"};
        Assert.assertEquals("wertf", findOrder(words));

        words = new String[]{"z", "x"};
        Assert.assertEquals("zx", findOrder(words));

        words = new String[]{"z", "x", "z"};            // The order is invalid
        Assert.assertEquals("", findOrder(words));

        words = new String[]{"baa", "abcd", "abca", "cab", "cad"};
        Assert.assertEquals("bdac", findOrder(words));

        words = new String[]{"caa", "aaa", "aab"};
        Assert.assertEquals("cab", findOrder(words));
    }

/*
    Time complexity: O(n * l) -> l = avg length of words. n = number of words in the given array
    Space complexity: O(c) -> c = number of unique characters.
 */
    String findOrder(String[] words) {
        int[] inDegree = new int[26];
        Map<Character, Set<Character>> graph = new HashMap<>();     // OutDegrees
        buildGraph(words, inDegree, graph);
        return KahnsAlgorithms(inDegree, graph);
    }

    String KahnsAlgorithms(int[] inDegree, Map<Character, Set<Character>> graph) {
        Queue<Character> zeroDegree = new ArrayDeque<>();
        graph.forEach((ch, adjSet) -> {
            if(inDegree[ch-'a'] == 0)
                zeroDegree.add(ch);
        });

        StringBuilder outputOrder = new StringBuilder();

        while(!zeroDegree.isEmpty()) {
            char alphabet = zeroDegree.poll();
            outputOrder.append(alphabet);

            if(graph.get(alphabet) == null || graph.get(alphabet).isEmpty())
                continue;

            for(char adj : graph.get(alphabet)) {
                inDegree[adj-'a']--;
                if(inDegree[adj-'a'] == 0)
                    zeroDegree.add(adj);
            }
        }

        for(int degree : inDegree) {
            if(degree != 0)               // Graph contains a cycle
                return "";
        }

        return outputOrder.toString();
        //return outputOrder.length() == graph.size() ? outputOrder.toString() : "";
    }

    void buildGraph(String[] words, int[] inDegree, Map<Character, Set<Character>> graph) {
        for(int w=0; w<words.length-1; w++) {
            String word1 = words[w];
            String word2 = words[w+1];
            int len = Math.min(word1.length(), word2.length());

            for(int i=0; i<len; i++) {
                if(word1.charAt(i) != word2.charAt(i)) {
                    char out = word1.charAt(i);
                    char in = word2.charAt(i);

                    Set<Character> adj = graph.getOrDefault(out, new HashSet<>());
                    adj.add(in);
                    graph.put(out, adj);

                    inDegree[in - 'a']++;
                    break;
                }
            }
        }
    }
}

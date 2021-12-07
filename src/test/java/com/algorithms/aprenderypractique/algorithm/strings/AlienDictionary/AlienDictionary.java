package com.algorithms.aprenderypractique.algorithm.strings.AlienDictionary;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 *  https://leetcode.com/problems/alien-dictionary/
 *  https://www.youtube.com/watch?v=6kTZYvNNyps
 *
 *  -- Hard
 *
 * @see com.algorithms.aprenderypractique.algorithm.graph.CourseSchedule.CourseSchedule_II_KahnsAlgo
 */
public class AlienDictionary extends BaseTest {

    @Test
    public void test() {
        String[] words = new String[]{"wrt", "wrf", "er", "ett", "rftt"};
        Assert.assertEquals("wertf", findOrder(words));
    }

//  Some confusion in the solution code
//  Logic is clear bcz it is same as CourseSchedule_II_KahnsAlgo
    String findOrder(String[] words) {
        int[] inDegree = new int[26];
        Map<Character, Set<Character>> graph = new HashMap<>();     // OutDegrees
        buildGraph(words, inDegree, graph);
        return KahnsAlgorithms(words, inDegree, graph);
    }

    String KahnsAlgorithms(String[] words, int[] inDegree, Map<Character, Set<Character>> graph) {
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
            int i=0;
            while(i<len) {
                if(word1.charAt(i) != word2.charAt(i)) {
                    char out = word1.charAt(i);
                    char in = word2.charAt(i);
                    Set<Character> adj = graph.getOrDefault(out, new HashSet<>());
                    adj.add(in);
                    graph.put(out, adj);

                    inDegree[in-'a']++;
                    break;
                }
                i++;
            }
        }
    }
}

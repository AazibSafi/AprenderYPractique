package com.algorithms.aprenderypractique.Algorithms.Graph;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 *  https://leetcode.com/problems/possible-bipartition/
 *  https://www.youtube.com/watch?v=-SpTh4AEZrk
 */
public class PossibleBipartition extends BaseTest {

    @Test
    public void solution() {
        int[][] graph = new int[][]{{1,2},{1,3},{2,4}};
        Assert.assertTrue(possibleBipartition(4, graph));

        graph = new int[][]{{1,2},{1,3},{2,3}};
        Assert.assertFalse(possibleBipartition(3, graph));

        graph = new int[][]{{1,2},{2,3},{3,4},{4,5},{1,5}};
        Assert.assertFalse(possibleBipartition(5, graph));
    }

    public boolean possibleBipartition(int n, int[][] dislikes) {
        ArrayList<Integer>[] graph = buildGraph(n+1, dislikes);     // n+1 - because the person node starts with 1, So we will ignore the 0th index in whole solution

        int[] colorApplied = new int[n+1];
        for(int person=1; person<graph.length; person++) {
            if(colorApplied[person] == 0 && !fillColor(colorApplied, graph, person, 1)) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Integer>[] buildGraph(int n, int[][] dislikes) {
        ArrayList<Integer>[] graph = new ArrayList[n];

        for(int i=0; i<graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int[] personPair: dislikes) {
            graph[personPair[0]].add(personPair[1]);
            graph[personPair[1]].add(personPair[0]);
        }

        return graph;
    }

/**
 * @see GraphBipartite same logic of applyColor - only ArrayList<Integer>[] graph, is different
 *  We can also use Union Find Strategy
 */
    public boolean fillColor(int[] colorApplied, ArrayList<Integer>[] graph, int person, int color) {
        if(colorApplied[person] != 0)     return colorApplied[person] == color;

        colorApplied[person] = color;

        for(int adjacent : graph[person]) {
            if(!fillColor(colorApplied, graph, adjacent, -color)) {
                return false;
            }
        }
        return true;
    }

}

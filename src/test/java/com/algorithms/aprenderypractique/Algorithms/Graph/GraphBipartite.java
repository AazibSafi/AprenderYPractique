package com.algorithms.aprenderypractique.Algorithms.Graph;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.Algorithms.Datastructure.DisjointSet;
import org.junit.Assert;
import org.junit.Test;

/**
 *  https://leetcode.com/problems/is-graph-bipartite/
 *  https://www.youtube.com/watch?v=-SpTh4AEZrk
 *
 *  TODO: https://leetcode.ca/2016-08-17-261-Graph-Valid-Tree/
 */
public class GraphBipartite extends BaseTest {

    @Test
    public void solution() {
        int[][] graph = new int[][]{{1,3},{0,2},{1,3},{0,2}};
        Assert.assertTrue(isBipartite(graph));

        graph = new int[][]{{1,2,3},{0,2},{0,1,3},{0,2}};
        Assert.assertFalse(isBipartite(graph));

        graph = new int[][]{{},{2,4,6},{1,4,8,9},{7,8},{1,2,8,9},{6,9},{1,5,7,8,9},{3,6,9},{2,3,4,6,9},{2,4,5,6,7,8}};
        Assert.assertFalse(isBipartite(graph));
    }

/*
    0 - NO COLOR
    1 - RED
   -1 - BLUE
 */
/*
    Time: O(V+E)
    Space: O(V)
    DFS
 */
    public boolean isBipartite2(int[][] graph) {
        int[] colorApplied = new int[graph.length];
        for(int node=0; node<graph.length; node++) {
            if(colorApplied[node] == 0 && !applyColor(colorApplied, graph, node, 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean applyColor(int[] colorApplied, int[][] graph, int node, int color) {
        if(colorApplied[node] != 0)     // if the node is already colored
            return colorApplied[node] == color;

        colorApplied[node] = color;
        for(int adjacent : graph[node]) {
            if(!applyColor(colorApplied, graph, adjacent, -color))
                return false;
        }
        return true;
    }

///////////

/*
    Time: O(V + E)
    Space: O(V)

    Union Find Technique
    Efficient Solution
 */
    public boolean isBipartite(int[][] graph) {
        DisjointSet forest = new DisjointSet(graph.length);

        for(int node=0; node<graph.length; node++) {
            int nodeParent = forest.find(node);
            for(int adjacent : graph[node]) {
                if(nodeParent == forest.find(adjacent))
                    return false;
                forest.union(adjacent, graph[node][0]);
            }
        }

        return true;
    }

}

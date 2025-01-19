package com.algorithms.aprenderypractique.Algorithms.Graph;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *      https://leetcode.com/problems/all-paths-from-source-to-target
 *
 */
public class AllPathsFromSourceToTarget extends BaseTest {
/*
    Approach: Backtracking
    Time: O(2^N ⋅ N)
    Space: O(N)
*/
    List<List<Integer>> results;

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        this.results = new ArrayList<List<Integer>>();
        // adopt the LinkedList for fast access to the tail element.
        LinkedList<Integer> path = new LinkedList<Integer>();
        path.addLast(0);

        // kick of the backtracking, starting from the source (node 0)
        backTracking(graph, 0, path);
        return results;
    }

    public void backTracking(int[][] graph, int currNode, LinkedList<Integer> path) {
        if(currNode == graph.length - 1) {
            // Note: one should make a deep copy of the path
            this.results.add(new ArrayList<Integer>(path));
            return;
        }

        // explore the neighbor nodes one after another
        for(int vertex : graph[currNode]) {
            // mark the choice, before backtracking.
            path.addLast(vertex);
            backTracking(graph, vertex, path);
            // remove the previous choice, to try the next choice
            path.removeLast();
        }
    }

}

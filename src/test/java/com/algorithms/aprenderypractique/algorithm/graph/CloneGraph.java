package com.algorithms.aprenderypractique.algorithm.graph;

import com.algorithms.aprenderypractique.BaseTest;
import com.algorithms.aprenderypractique.algorithm.datastructure.GraphNode;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 *  https://leetcode.com/problems/clone-graph
 *  https://www.youtube.com/watch?v=e5tNvT1iUXs&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW
 *
 *  Time Complexity: O(N)
 *  Space Complexity: O(N)
 */
public class CloneGraph extends BaseTest {

    @Test
    public void solution() {

    }

    public GraphNode cloneGraph(GraphNode node) {
        if(node == null)        return null;
        Map<Integer, GraphNode> map = new HashMap<>();
        return cloneGraph(node, map);
    }

    public GraphNode cloneGraph(GraphNode node, Map<Integer, GraphNode> map) {
        if(map.containsKey(node.val))   return map.get(node.val);

        GraphNode copy = new GraphNode(node.val);
        map.put(copy.val, copy);

        for(GraphNode neighbor : node.neighbors)
            copy.neighbors.add(cloneGraph(neighbor, map));

        return copy;
    }

}

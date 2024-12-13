package com.algorithms.aprenderypractique.Algorithms.Datastructure;

import java.util.ArrayList;
import java.util.List;

/**
 *  Graph Data Structure
 */
public class GraphNode {
    public int val;
    public List<GraphNode> neighbors;

    public GraphNode() {
        val = 0;
        neighbors = new ArrayList<>();
    }

    public GraphNode(int _val) {
        val = _val;
        neighbors = new ArrayList<>();
    }

    public GraphNode(int _val, ArrayList<GraphNode> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }

}

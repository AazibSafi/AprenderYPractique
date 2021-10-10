package com.algorithms.aprenderypractique.algorithm;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  https://leetcode.com/problems/clone-graph
 *  https://www.youtube.com/watch?v=e5tNvT1iUXs&list=PLtQWXpf5JNGJagakc_kBtOH5-gd8btjEW&index=11
 *
 *  Time Complexity: O(N)
 *  Space Complexity: O(N)
 */
public class CloneGraph extends BaseTest {

    @Test
    public void solution() {

    }

    public Node cloneGraph(Node node) {
        if(node == null)        return null;
        Map<Integer,Node> map = new HashMap<>();
        return cloneGraph(node, map);
    }

    public Node cloneGraph(Node node, Map<Integer,Node> map) {
        if(map.containsKey(node.val))   return map.get(node.val);

        Node copy = new Node(node.val);
        map.put(copy.val, copy);

        for(Node neighbor : node.neighbors)
            copy.neighbors.add(cloneGraph(neighbor,map));

        return copy;
    }

    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

}

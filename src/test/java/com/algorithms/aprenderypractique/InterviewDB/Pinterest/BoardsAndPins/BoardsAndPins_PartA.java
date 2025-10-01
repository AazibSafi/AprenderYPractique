package com.algorithms.aprenderypractique.InterviewDB.Pinterest.BoardsAndPins;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * @see com.algorithms.aprenderypractique.Algorithms.Graph.BusRoutes
 *
 *  https://chatgpt.com/g/g-p-688bea0aa90881918d1f5ff6f5c89ce7-pinterest/c/68c05c44-35e8-8323-b5b8-3b911d700c04
 *
 *  Pinterest Problem
 */
public class BoardsAndPins_PartA {

    @Test
    public void test() {
        Map<String, List<String>> boardMap = new HashMap<>();
        boardMap.put("boardA", Arrays.asList("pin1", "pin2"));
        boardMap.put("boardB", Arrays.asList("pin3", "pin2"));
        boardMap.put("boardC", Arrays.asList("pin4", "pin3"));
        boardMap.put("boardD", Arrays.asList("pin5", "pin6"));

        Assert.assertTrue(isGroupMembership(boardMap, "pin1", "pin2")); // true
        Assert.assertFalse(isGroupMembership(boardMap, "pin5", "pin4")); // false
        Assert.assertTrue(isGroupMembership(boardMap, "pin1", "pin4")); // true (pin1–pin2–pin3–pin4)
    }

    public boolean isGroupMembership(Map<String, List<String>> boardsMap, String startPin, String endPin) {
        if(startPin.equals(endPin))  return true;

        Map<String, List<String>> graph = createGraph(boardsMap);
        if(!graph.containsKey(startPin) || !graph.containsKey(endPin))
            return false;

        Set<String> visited = new HashSet<>();

        // This can be solved by BFS or DFS
        return dfs(graph, startPin, endPin, visited);
//         return bfs(graph, startPin, endPin, visited);
    }

    public boolean dfs(Map<String, List<String>> graph, String startPin, String endPin, Set<String> visited) {
        if(startPin.equals(endPin))  return true;

        visited.add(startPin);

        for(String adjPin : graph.get(startPin)) {
            if(!visited.contains(adjPin)
                    && dfs(graph, adjPin, endPin, visited))
                return true;
        }
        return false;
    }

    public boolean bfs(Map<String, List<String>> graph, String startPin, String endPin, Set<String> visited) {
        Queue<String> queue = new ArrayDeque<>();
        queue.add(startPin);

        while(!queue.isEmpty()) {
            String pin = queue.poll();

            if(pin.equals(endPin))
                return true;

            visited.add(startPin);

            for(String adjPin : graph.get(pin)) {
                if(!visited.contains(adjPin)) {
                    queue.add(adjPin);
                }
            }
        }
        return false;
    }

    // Build an undirected graph where each pin is a node.
    // For every board, connect adjacent pins in its sequence (e.g., [p1, p2, p3] ⇒ edges (p1–p2), (p2–p3)).
    public Map<String, List<String>> createGraph(Map<String, List<String>> boardsMap) {
        Map<String, List<String>> graph = new HashMap<>();
        for(List<String> pins : boardsMap.values()) {
            for(int i=0; i+1<pins.size(); i++) {
                String a = pins.get(i), b = pins.get(i + 1);
                graph.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
                graph.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
            }
            // Ensure isolated single-pin boards or the last pin in the list still register as nodes
            graph.computeIfAbsent(pins.getLast(), k->new ArrayList<>());
        }
        return graph;
    }

}

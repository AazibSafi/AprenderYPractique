package com.algorithms.aprenderypractique.InterviewDB.Pinterest.BoardsAndPins;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * @see com.algorithms.aprenderypractique.Algorithms.Graph.BusRoutes
 *
 *  https://chatgpt.com/g/g-p-688bea0aa90881918d1f5ff6f5c89ce7-pinterest/c/68c05c44-35e8-8323-b5b8-3b911d700c04
 *
 *  Pinterest.md Problem
 */
public class BoardsAndPins_PartB {

    @Test
    public void test() {
        Map<String, List<String>> boardMap = new HashMap<>();
        boardMap.put("boardA", Arrays.asList("pin1", "pin2"));
        boardMap.put("boardB", Arrays.asList("pin3", "pin2"));
        boardMap.put("boardC", Arrays.asList("pin4", "pin3"));
        boardMap.put("boardD", Arrays.asList("pin5", "pin6"));

        Assert.assertEquals(3, connectionScore(boardMap, "pin1", "pin4")); // 3  (pin1-2-3-4)
        Assert.assertEquals(0, connectionScore(boardMap, "pin5", "pin4")); // 0  (no path)
        Assert.assertEquals(0, connectionScore(boardMap, "pin2", "pin2")); // 0  (same pin)
    }

    public int connectionScore(Map<String, List<String>> boardsMap, String startPin, String endPin) {
        if(startPin.equals(endPin))  return 0;

        Map<String, List<String>> graph = createGraph(boardsMap);
        if(!graph.containsKey(startPin) || !graph.containsKey(endPin))
            return 0;

        Set<String> visited = new HashSet<>();

        // This problem can only solve by BFS (no DFS) since we have to reach the target in min steps.
        return bfs(graph, startPin, endPin, visited);
    }

    public int bfs(Map<String, List<String>> graph, String startPin, String endPin, Set<String> visited) {
        Queue<String> queue = new ArrayDeque<>();
        queue.add(startPin);

        int minScore = 0;

        while(!queue.isEmpty()) {
            int levelSize = queue.size();

            while(levelSize-- > 0) {
                String pin = queue.poll();

                if (pin.equals(endPin))
                    return minScore;

                visited.add(startPin);

                for (String adjPin : graph.get(pin)) {
                    if (!visited.contains(adjPin)) {
                        queue.add(adjPin);
                    }
                }
            }
            minScore++;
        }
        return 0;
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

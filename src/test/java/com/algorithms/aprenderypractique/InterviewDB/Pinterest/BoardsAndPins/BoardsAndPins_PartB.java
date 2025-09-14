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
public class BoardsAndPins_PartB {

    // Build adjacency list
    // Build an undirected graph where each pin is a node.
    // For every board, connect adjacent pins in its sequence (e.g., [p1, p2, p3] ⇒ edges (p1–p2), (p2–p3)).
    public Map<String, Set<String>> createGraph(Map<String, List<String>> boardMap) {
        Map<String, Set<String>> graph = new HashMap<>();
        for(List<String> pins : boardMap.values()) {
            for(int i=0; i+1<pins.size(); i++) {
                String a = pins.get(i), b = pins.get(i + 1);
                graph.computeIfAbsent(a, k -> new HashSet<>()).add(b);
                graph.computeIfAbsent(b, k -> new HashSet<>()).add(a);
            }
            // Ensure isolated single-pin boards still register as nodes
            if (pins.size() == 1) {
                graph.computeIfAbsent(pins.getFirst(), k -> new HashSet<>());
            }
        }
        return graph;
    }

    public int connectionScore(Map<String, List<String>> boardMap, String start, String end) {
        if(start.equals(end))   return 0;

        Map<String, Set<String>> graph = createGraph(boardMap);
        if(!graph.containsKey(start) || !graph.containsKey(end))
            return 0;

        // BFS
        Queue<String> queue = new ArrayDeque<>();
        queue.add(start);
        Set<String> seen = new HashSet<>();
        seen.add(start);

        int steps=0;
        while(!queue.isEmpty()) {
            int size = queue.size();

            while(size-- > 0) {
                String pin = queue.poll();

                if (pin.equals(end))
                    return steps;

                for (String adjPin : graph.get(pin)) {
                    if (seen.add(adjPin)) {
                        queue.add(adjPin);
                    }
                }
            }
            steps++;
        }
        return 0;
    }

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

}

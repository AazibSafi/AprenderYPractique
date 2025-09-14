package com.algorithms.aprenderypractique.InterviewDB.Pinterest.BoardsAndPins;

import java.util.*;

/**
 * @see com.algorithms.aprenderypractique.Algorithms.Graph.BusRoutes
 *
 * Tried with BusRoute Approach, but this is wrong logic and more complicated
 * https://chatgpt.com/g/g-p-688bea0aa90881918d1f5ff6f5c89ce7-pinterest/c/68c05c44-35e8-8323-b5b8-3b911d700c04
 *
 *  Pinterest Problem
 *  Wrong Implementation - slightly different than bus routes
 */
public class BoardsAndPins {

    int isGroupMembership(Map<String, List<String>> boardMap, String sourcePin, String targetPin) {
        if(sourcePin.equals(targetPin))   return 0;

        boardMap.values().forEach(Collections::sort);

        // {board -> List<board>}
        Map<String, List<String>> graph = createGraph(boardMap);

        Queue<String> queue = new ArrayDeque<>();
        addBoardsContainingSourcePin(queue, boardMap, sourcePin);

        Set<String> visited = new HashSet<>();

        int score = 1;

        //BFS
        while(!queue.isEmpty()) {
            int size = queue.size();
            score++;

            while(size-- > 0) {
                String board = queue.poll();

                if(containsPin(boardMap.get(board), targetPin))
                    return score;

                // enqueue adjacent board if not visited
                for(String adjBoard : graph.get(board)) {
                   if(!visited.contains(adjBoard)) {
                       visited.add(adjBoard);
                       queue.add(adjBoard);
                   }
                }
            }
            score++;
        }
        return 0;
    }

    void addBoardsContainingSourcePin(Queue<String> queue, Map<String, List<String>> boardMap, String sourcePin) {
        for(Map.Entry<String, List<String>> boardEntry : boardMap.entrySet()) {
            if(containsPin(boardEntry.getValue(), sourcePin)) {
                queue.add(boardEntry.getKey());
            }
        }
    }

    boolean containsPin(List<String> board, String targetPin) {
        return board.stream().anyMatch(pin -> pin.equals(targetPin));
    }


    Map<String, List<String>> createGraph(Map<String, List<String>> boardMap) {
        Map<String, List<String>> graph = new HashMap<>();
        List<String> boardNames = new ArrayList<>(boardMap.keySet());

        boardNames.forEach(board -> graph.put(board, new ArrayList<>()));

        for(int i=0; i<boardNames.size(); i++) {
            for(int j=i+1; j<boardNames.size(); j++) {
                String board1 = boardNames.get(i);
                String board2 = boardNames.get(j);
                if(haveCommonPin(boardMap.get(board1), boardMap.get(board2))) {
                    graph.get(board1).add(board2);
                    graph.get(board2).add(board1);
                }
            }
        }
        return graph;
    }

    boolean haveCommonPin(List<String> board1, List<String> board2) {
        int i=0, j=0;
        while(i<board1.size() && j<board2.size()) {
            if(board1.get(i).equals(board2.get(j)))
                return true;

            if(board1.get(i).compareTo(board2.get(j)) < 0) {
                i++;
            }
            else {
                j++;
            }
        }
        return false;
    }

}

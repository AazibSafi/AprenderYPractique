package com.algorithms.aprenderypractique.algorithm.graph;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.IntStream;

/**
 *      https://leetcode.com/problems/detonate-the-maximum-bombs
 *      https://www.youtube.com/watch?v=8NPbAvVXKR4
 *      https://www.youtube.com/watch?v=TpmSRsvWitU
 */
public class DetonateTheMaximumBombs extends BaseTest {

    @Test
    public void solution() {
        int[][] bombs = new int[][]{{2,1,3},{6,1,4}};
        Assert.assertEquals(2, maximumDetonation(bombs));

        bombs = new int[][]{{1,1,5},{10,10,5}};
        Assert.assertEquals(1, maximumDetonation(bombs));

        bombs = new int[][]{{1,2,3},{2,3,1},{3,4,2},{4,5,3},{5,6,4}};
        Assert.assertEquals(5, maximumDetonation(bombs));

        bombs = new int[][]{{1,1,100000},{100000,100000,1}};
        Assert.assertEquals(1, maximumDetonation(bombs));

        bombs = new int[][]{{56,80,2},{55,9,10},{32,75,2},{87,89,1},{61,94,3},{43,82,9},{17,100,6},{50,6,7},{9,66,7},{98,3,6},{67,50,2},{79,39,5},{92,60,10},{49,9,9},{42,32,10}};
        Assert.assertEquals(3, maximumDetonation(bombs));
    }

/*
    Time: O(n^2)
    Space: O(n^2) -> Graph
 */
    public int maximumDetonation(int[][] bombs) {
        Map<Integer, List<Integer>> neighbors = buildGraph(bombs);  // <Bomb, List<Neighbor Bomb>>
        return IntStream.range(0, bombs.length)
                //.map(b -> detonateBombBFS(b, neighbors))
                .map(b -> detonateBombDFS(b, neighbors, new HashSet<>()))
                .max().getAsInt();
    }

    public int detonateBombDFS(int bomb, Map<Integer, List<Integer>> neighbors, Set<Integer> visited) {
        if(visited.contains(bomb))   return 0;
        visited.add(bomb);

        neighbors.get(bomb).forEach(neighbor -> detonateBombDFS(neighbor, neighbors, visited));

        return visited.size();  // Total Bombs detonated
    }

//  Time: O(n + m)  OR  O(e + v)
    public int detonateBombBFS(int i, Map<Integer, List<Integer>> neighbors) {
        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        visited.add(i);
        queue.add(i);

        while(!queue.isEmpty()) {
            int bomb = queue.poll();
            for(int neighbor : neighbors.get(bomb)) {
                if(!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return visited.size();  // Total Bombs detonated
    }

//   Time: O(n^2)
    public Map<Integer, List<Integer>> buildGraph(int[][] bombs) {
        Map<Integer, List<Integer>> neighbors = new HashMap<>();

        for(int i=0; i<bombs.length; i++) {
            List<Integer> neighborBombs = new ArrayList<>();
            int x = bombs[i][0], y = bombs[i][1], r = bombs[i][2];

            for(int j=0; j<bombs.length; j++) {
                if(i != j) {
                    if(distance(x, y, bombs[j][0], bombs[j][1]) <= r) {
                        neighborBombs.add(j);
                    }
                }
            }
            neighbors.put(i, neighborBombs);
        }
        return neighbors;
    }

    //  Euclidean distance
    double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
    }

}

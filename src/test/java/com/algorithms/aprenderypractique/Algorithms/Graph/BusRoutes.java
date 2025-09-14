package com.algorithms.aprenderypractique.Algorithms.Graph;

import java.util.*;
import java.util.stream.IntStream;

/**
 *  https://leetcode.com/problems/bus-routes
 */
public class BusRoutes {

    public int numBusesToDestination(int[][] routes, int source, int target) {
        if(source == target)    return 0;

        // Sort the bus Stops within each Route
        Arrays.stream(routes).forEach(Arrays::sort);

        // Connect Routes as Nodes with having common bus stop
        List<List<Integer>> graph = createGraph(routes);

        Queue<Integer> queue = new ArrayDeque<>();

        // Find all routes with source bus stop
        addRouteContainingSource(queue, routes, source);

        Set<Integer> visited = new HashSet<>();

        int buses = 1;  // atleast 1 bus must take to travel

        // Apply BFS
        while(!queue.isEmpty()) {
            int size = queue.size();

            while(size-- > 0) {
                int currRoute = queue.poll();

                if(containsStop(routes[currRoute], target)) {
                    return buses;
                }

                for(int adjRoute : graph.get(currRoute)) {
                    if(!visited.contains(adjRoute)) {
                        visited.add(adjRoute);
                        queue.add(adjRoute);
                    }
                }
            }
            buses++;
        }
        return -1;  // No path found to travel from source to target
    }

    Queue<Integer> addRouteContainingSource(Queue<Integer> queue, int[][] routes, int source) {
        for(int i=0; i<routes.length; i++) {
            if(containsStop(routes[i], source))
                queue.add(i);
        }
        return queue;
    }

    boolean containsStop(int[] route, int target) {
        return IntStream.of(route).anyMatch(stop -> stop==target);
    }

    List<List<Integer>> createGraph(int[][] routes) {
        List<List<Integer>> graph = new ArrayList<>();
        Arrays.stream(routes).forEach(route -> graph.add(new ArrayList<>()));

        for(int i=0; i<routes.length; i++) {
            for(int j=i+1; j<routes.length; j++) {
                if(haveCommonStop(routes[i], routes[j])) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
        return graph;
    }

    // Since bus stops within routes are already sorted
    boolean haveCommonStop(int[] route1, int[] route2) {
        int i=0, j=0;

        while(i<route1.length && j<route2.length) {
            if(route1[i] == route2[j])
                return true;

            if(route1[i] < route2[j])
                i++;
            else
                j++;
        }
        return false;
    }

}

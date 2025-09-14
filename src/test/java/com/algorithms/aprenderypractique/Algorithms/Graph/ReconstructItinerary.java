package com.algorithms.aprenderypractique.Algorithms.Graph;

import java.util.*;

/**
 *  https://leetcode.com/problems/reconstruct-itinerary
 *
 *  https://leetcode.com/problems/reconstruct-itinerary/solutions/78768/short-ruby-python-java-c/?envType=company&envId=pinterest&favoriteSlug=pinterest-three-months
 *  https://leetcode.com/problems/reconstruct-itinerary/solutions/4041944/95-76-dfs-recursive-iterative/?envType=company&envId=pinterest&favoriteSlug=pinterest-three-months
 */
public class ReconstructItinerary {

    /*
        Time: O(NlogN) due to sorting the tickets.
        Space: O(N), for storing the graph and the itinerary.
    */
    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for(List<String> ticket : tickets) {
            graph.computeIfAbsent(ticket.getFirst(), k -> new PriorityQueue<>()).add(ticket.getLast());
        }

        List<String> itinerary = new ArrayList<>();

        dfs(graph, "JFK", itinerary);
        return itinerary;
    }

    void dfs(Map<String, PriorityQueue<String>> graph, String src, List<String> itinerary) {
        PriorityQueue<String> nextDestinations = graph.get(src);
        while(nextDestinations!=null && !nextDestinations.isEmpty()) {
            dfs(graph, nextDestinations.poll(), itinerary);
        }

        itinerary.addFirst(src);
    }

}

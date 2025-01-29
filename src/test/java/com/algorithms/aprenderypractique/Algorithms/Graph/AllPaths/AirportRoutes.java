package com.algorithms.aprenderypractique.Algorithms.Graph.AllPaths;

import com.algorithms.aprenderypractique.BaseTest;
import org.assertj.core.util.Strings;
import org.junit.Test;

import java.util.*;

/**
 *      https://github.com/Leader-board/OA-and-Interviews/blob/main/Application%20experiences/2021-22/Bloomberg/Software%20Engineer.md#the-onsite-part-1
 *      https://leetcode.com/discuss/interview-question/1069774/bloomberg-onsite-london-non-leetcode-question
 *
 *      Similar Type Question: https://leetcode.com/problems/all-paths-from-source-to-target
 */
public class AirportRoutes extends BaseTest {

    @Test
    public void testFindAllRoutesWithLetters() {
        AirportRoutes airportRoutes = new AirportRoutes();
        airportRoutes.addRoute("A", "B");
        airportRoutes.addRoute("B", "C");
        airportRoutes.addRoute("B", "D");
        airportRoutes.addRoute("C", "E");
        airportRoutes.addRoute("D", "E");
        Set<List<String>> allRoutes = airportRoutes.allPossibleRoutes("A", "E");
        System.out.println("Route From A to E");
        printRoutes(allRoutes);


        airportRoutes = new AirportRoutes();
        airportRoutes.addRoute("A", "B");
        airportRoutes.addRoute("A", "B");
        airportRoutes.addRoute("A", "P");
        airportRoutes.addRoute("P", "E");
        airportRoutes.addRoute("B", "C");
        airportRoutes.addRoute("B", "D");
        airportRoutes.addRoute("C", "G");
        airportRoutes.addRoute("C", "A");
        airportRoutes.addRoute("G", "E");
        airportRoutes.addRoute("C", "E");
        airportRoutes.addRoute("D", "E");
        airportRoutes.addRoute("E", "P");
        allRoutes = airportRoutes.allPossibleRoutes("A", "E");
        System.out.println("Route From A to E");
        airportRoutes.printRoutes(allRoutes);


        airportRoutes = new AirportRoutes();
        airportRoutes.addRoute("A", "B");
        allRoutes = airportRoutes.allPossibleRoutes("C", "E");
        System.out.println("Route From C to E");
        airportRoutes.printRoutes(allRoutes);   // No Route
    }

    Map<String, List<String>> routes;
    public AirportRoutes() {
        routes = new HashMap<>();
    }

    public void addRoute(String source, String destination) {
        routes.computeIfAbsent(source, k-> new ArrayList<>()).add(destination);
    }

    public void printRoutes(Set<List<String>> allRoutes) {
        allRoutes.stream()
                .map(route -> Strings.join(route).with("->"))
                .forEach(System.out::println);
    }

    public Set<List<String>> allPossibleRoutes(String source, String destination) {
        Set<List<String>> allRoutes = new HashSet<>();
        if(!routes.containsKey(source)) return allRoutes;

        LinkedList<String> path = new LinkedList<>();
        dfs(source, destination, path, allRoutes);
        return allRoutes;
    }

    public void dfs(String source, String destination, LinkedList<String> path, Set<List<String>> allRoutes) {
        path.addLast(source);

        if(source.equals(destination)) {
            allRoutes.add(new ArrayList<>(path));
        }
        else {
            for (String transitSource : routes.get(source)) {
                if (!path.contains(transitSource))
                    dfs(transitSource, destination, path, allRoutes);
            }
        }

        path.removeLast();
    }

}

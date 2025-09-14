package com.algorithms.aprenderypractique.Algorithms.Strings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

/**
 *  https://leetcode.com/problems/destination-city
 */
public class DestinationCity {

    @ParameterizedTest(name = "HashSet Approach: Expected ''{1}''")
    @MethodSource("destinationCityProvider")
    void testDestCity_HashSet(List<List<String>> paths, String expected) {
        Assertions.assertEquals(expected, destCity_HashSet(paths));
    }

    @ParameterizedTest(name = "HashMap Approach: Expected ''{1}''")
    @MethodSource("destinationCityProvider")
    void testDestCity_HashMap(List<List<String>> paths, String expected) {
        Assertions.assertEquals(expected, destCity_HashMap(paths));
    }

    static Stream<Arguments> destinationCityProvider() {
        return Stream.of(
                Arguments.of(List.of(List.of("London", "New York"), List.of("New York", "Lima"), List.of("Lima", "Sao Paulo")), "Sao Paulo"),
                Arguments.of(List.of(List.of("B", "C"), List.of("D", "B"), List.of("C", "A")), "A"),
                Arguments.of(List.of(List.of("A", "Z")), "Z")
        );
    }

    /*
        Approach: HashSet
        Time: O(n)
        Space: O(n)
    */
    public String destCity_HashSet(List<List<String>> paths) {
        Set<String> set = new HashSet<>();
        for(List<String> path : paths) {
            set.add(path.get(0));
        }

        for(List<String> path : paths) {
            if(!set.contains(path.get(1))) {
                return path.get(1);
            }
        }

        return "";
    }

    /*
        Approach: HashMap
        Time: O(n)
        Space: O(n)
    */
    public String destCity_HashMap(List<List<String>> paths) {
        Map<String, String> map = new HashMap<>();
        for(List<String> path : paths) {
            map.put(path.get(0), path.get(1));
        }

        String sourceCity = paths.getFirst().getFirst();

        while(map.containsKey(sourceCity)) {
            sourceCity = map.get(sourceCity);
        }
        return sourceCity;
    }

}

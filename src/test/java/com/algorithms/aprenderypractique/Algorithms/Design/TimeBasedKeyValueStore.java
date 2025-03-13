package com.algorithms.aprenderypractique.Algorithms.Design;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.data.util.Pair;

import java.util.*;
import java.util.stream.Stream;

/**
 *      https://leetcode.com/problems/time-based-key-value-store
 */
public class TimeBasedKeyValueStore {

    @ParameterizedTest
    @MethodSource("timeMapImplementations")
    public void testTimeMap(TimeMapInterface timeMap) {
        // Test Case 1: Basic set and get
        timeMap.set("foo", "bar", 1);
        Assertions.assertEquals("bar", timeMap.get("foo", 1)); // Should return "bar"

        // Test Case 2: Getting value before the first timestamp
        Assertions.assertEquals("", timeMap.get("foo", 0)); // Should return "" since no values set before timestamp 1

        // Test Case 3: Getting value at a later timestamp
        Assertions.assertEquals("bar", timeMap.get("foo", 2)); // Should return "bar"

        // Test Case 4: Overwriting value at a later timestamp
        timeMap.set("foo", "bar2", 4);
        Assertions.assertEquals("bar", timeMap.get("foo", 3)); // Should return "bar" since the latest value at or before 3 is "bar"
        Assertions.assertEquals("bar2", timeMap.get("foo", 4)); // Should return "bar2"
        Assertions.assertEquals("bar2", timeMap.get("foo", 5)); // Should return "bar2"

        // Test Case 5: Multiple keys
        timeMap.set("baz", "qux", 2);
        Assertions.assertEquals("qux", timeMap.get("baz", 2)); // Should return "qux"
        Assertions.assertEquals("qux", timeMap.get("baz", 3)); // Should return "qux"
        Assertions.assertEquals("", timeMap.get("baz", 1)); // Should return "" since no values set before timestamp 2

        // Test Case 6: Retrieving values from an empty key
        Assertions.assertEquals("", timeMap.get("nonexistent", 1)); // Should return ""
    }

    private static Stream<Arguments> timeMapImplementations() {
        return Stream.of(
                Arguments.of(new TimeMap()),    // Approach#3: Hashmap + Array + Binary Search
                Arguments.of(new TimeMap1()),   // Approach#2: Hashmap + Sorted Map + Binary Search
                Arguments.of(new TimeMap2())    // Approach#1: Hashmap + Linear Search
        );
    }

    /**
     * Common interface for all TimeMap implementations
     */
    interface TimeMapInterface {
        void set(String key, String value, int timestamp);
        String get(String key, int timestamp);
    }

    /**
     *  S is the number of set function calls
     *  G is the number of get function calls
     *  L is average length of key and value strings.
     *  M at-most elements
     */

/*
    Approach#3: Hashmap + Array + Binary Search   [Acceptable] [Efficient]
        Since the timestamp is strictly increasing, the Array is already sorted
            Time                Space
    set:    O(S.L)              O(S.L)
    get:    O(G.L.logM)         constant
*/
    static class TimeMap implements TimeMapInterface {
        //Map(key, Array(Pair(timestamp, value)))
        Map<String, List<Pair<Integer, String>>> storage;

        public TimeMap() {
            storage = new HashMap<>();
        }

        // Time: O(S.L) -> For each S calls, it takes L time to hash the string
        public void set(String key, String value, int timestamp) {
            storage.computeIfAbsent(key, k->new LinkedList<>()).add(Pair.of(timestamp, value));
        }

        // For G calls, it takes O(L⋅logM)
        public String get(String key, int timestamp) {
            if(!storage.containsKey(key))
                return "";

            List<Pair<Integer, String>> timedValue = storage.get(key);
            if(timestamp < timedValue.get(0).getFirst())
                return "";

            // Find time equal to or less than timestamp using binary-search on Strictly Increasing Array.
            int right = BinarySearch_EqualOrLess(timedValue, timestamp);
            return right == -1 ? "" : timedValue.get(right).getSecond();
        }

        int BinarySearch_EqualOrLess(List<Pair<Integer, String>> list, int target) {
            int left = 0, right = list.size()-1;
            int ans = -1;
            while(left <= right) {
                int mid = left + (right-left)/2;
                int midKey = list.get(mid).getFirst();    // timestamp

                if(target == midKey)   return mid;

                if(target > midKey) {
                    ans = left;
                    left = mid+1;
                }
                else
                    right = mid - 1;
            }
            return ans;
        }
    }

/*
    Approach#2: Hashmap + Sorted Map + Binary Search    [Acceptable]
            Time                Space
    set:    O(S.L.logM)         O(S.L)
    get:    O(G.L.logM)         constant
*/
    static class TimeMap2 implements TimeMapInterface {
        // Map(key, SortedMap(timestamp, value))
        Map<String, TreeMap<Integer, String>> storage;

        public TimeMap2() {
            storage = new HashMap<>();
        }

        // For each S calls, it takes O(L⋅logM) time as the internal implementation of sorted maps is some kind of balanced binary tree
        public void set(String key, String value, int timestamp) {
            storage.computeIfAbsent(key, k->new TreeMap<>()).put(timestamp, value);
        }

        // For each G calls, it takes O(L⋅logM)
        public String get(String key, int timestamp) {
            if(!storage.containsKey(key))
                return "";

            TreeMap<Integer, String> timedValue = storage.get(key);

            // Find time equal to or less than timestamp using binary-search on SortedMap.
            Integer timestamp_prev = timedValue.floorKey(timestamp);
            return timestamp_prev == null ? "" : timedValue.get(timestamp_prev);
        }
    }

/*
    Approach#1: Hashmap + Linear Search  [TLE]
            Time                  Space
    set:    O(S.L)                O(M.L)
    get:    O(G.timestamp⋅L)      constant
*/
    static class TimeMap1 implements TimeMapInterface {
        // Map(key, HashMap(timestamp, value))
        Map<String, HashMap<Integer, String>> storage;

        public TimeMap1() {
            storage = new HashMap<>();
        }

        /*
            Time: O(S.L) -> For each S calls, it takes L time to hash the string
            Space: O(M.L) -> S calls may store M uniques values of length L
        */
        public void set(String key, String value, int timestamp) {
            storage.computeIfAbsent(key, k->new HashMap<>()).put(timestamp, value);
        }

        // Time: O(G.timestamp⋅L)
        //  for each G calls, it takes O(timestamp) time and again to hash the string it takes O(L).
        public String get(String key, int timestamp) {
            if(!storage.containsKey(key))
                return "";

            Map<Integer, String> timedValue = storage.get(key);
            for(int time=timestamp; time>=1; time--) {
                if(timedValue.containsKey(time))
                    return timedValue.get(time);
            }

            // Otherwise no time <= timestamp was stored in key's bucket.
            return "";
        }
    }

}

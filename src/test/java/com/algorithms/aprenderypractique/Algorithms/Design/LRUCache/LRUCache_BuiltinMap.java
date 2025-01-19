package com.algorithms.aprenderypractique.Algorithms.Design.LRUCache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *      https://leetcode.com/problems/lru-cache
 *      https://leetcode.com/problems/lru-cache/solutions/45939/laziest-implementation-java-s-linkedhashmap-takes-care-of-everything
 *      https://www.youtube.com/watch?v=7ABFKPK2hD4
 *
 *      Efficient solution to use but this is not expected in interview to use built-in  Data Structure
 */
public class LRUCache_BuiltinMap extends LinkedHashMap<Integer, Integer> {

    int capacity;

    public LRUCache_BuiltinMap(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }

}
